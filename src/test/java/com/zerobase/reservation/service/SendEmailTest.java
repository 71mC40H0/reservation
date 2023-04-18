package com.zerobase.reservation.service;


import com.zerobase.reservation.client.MailgunClient;
import com.zerobase.reservation.client.mailgun.SendEmailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SendEmailTest {

    @Autowired
    private MailgunClient mailgunClient;

    @Test
    void testSendEmail() {
        //given
        SendEmailForm form = SendEmailForm.builder()
                .from("chk7119@gmail.com")
                .to("mozamp2020@gmail.com")
                .subject("Subject of Test Email")
                .text("Text of Test Email")
                .build();

        //when
        String response = mailgunClient.sendEmail(form).getBody();

        //then
        assertTrue(Objects.requireNonNull(response).contains("Queued. Thank you."));
    }
}
