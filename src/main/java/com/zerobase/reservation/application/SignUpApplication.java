package com.zerobase.reservation.application;

import com.zerobase.reservation.client.MailgunClient;
import com.zerobase.reservation.client.mailgun.SendEmailForm;
import com.zerobase.reservation.domain.form.SignUpForm;
import com.zerobase.reservation.domain.model.Customer;
import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.service.customer.SignUpCustomerService;
import com.zerobase.reservation.service.manager.SignUpManagerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpManagerService signUpManagerService;
    private final SignUpCustomerService signUpCustomerService;

    @Value(value = "${mailgun.api.sendEmail}")
    private String sendEmail;

    public void managerVerify(String email, String code) {
        signUpManagerService.verifyEmail(email, code);
    }

    public String managerSignUp(SignUpForm form) {
        if (signUpManagerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Manager manager = signUpManagerService.signUp(form);

            String code = getRandomCode();
            SendEmailForm sendEmailForm = SendEmailForm.builder()
                    .from(sendEmail)
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(manager.getEmail(), manager.getName(), "manager", code))
                    .build();
            mailgunClient.sendEmail(sendEmailForm);
            signUpManagerService.changeManagerValidateEmail(manager.getId(), code);
            return "회원 가입에 성공하었습니다. 파트너 회원 가입을 위해서 메일 인증을 하시기 바랍니다";
        }
    }

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Customer customer = signUpCustomerService.signUp(form);

            String code = getRandomCode();
            SendEmailForm sendEmailForm = SendEmailForm.builder()
                    .from(sendEmail)
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(customer.getEmail(), customer.getName(), "customer", code))
                    .build();
            mailgunClient.sendEmail(sendEmailForm);
            signUpCustomerService.changeCustomerValidateEmail(customer.getId(), code);
            return "회원 가입에 성공하었습니다. 메일 인증을 하시기 바랍니다";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder builder = new StringBuilder();

        return builder.append("Hello ").append(name)
                .append("! Please Click Link for verification\n\n")
                .append("http://localhost:8080/signUp/")
                .append(type)
                .append("/verify?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }
}
