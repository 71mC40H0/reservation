package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "해당 회원을 찾을 수 없습니다."),
    ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "이미 인증된 회원입니다."),
    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST, "잘못된 인증 시도입니다."),
    EXPIRE_CODE(HttpStatus.BAD_REQUEST, "인증 시간이 만료되었습니다."),

    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디 또는 패스워드가 잘못되었습니다."),

    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "해당 식당이 없습니다."),
    INVALID_RESTAURANT(HttpStatus.BAD_REQUEST, "해당 점장의 식당이 아닙니다."),
    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "해당 예약이 없습니다."),

    ALREADY_APPROVED_RESERVATION(HttpStatus.BAD_REQUEST, "이미 승인된 예약입니다."),
    ALREADY_DENIED_RESERVATION(HttpStatus.BAD_REQUEST, "이미 거절된 예약입니다."),
    ALREADY_FINISHED_RESERVATION(HttpStatus.BAD_REQUEST, "이미 완료된 예약입니다."),
    NOT_APPROVED_RESERVATION(HttpStatus.BAD_REQUEST, "승인이 되지 않은 예약입니다."),
    EXPIRED_RESERVATION(HttpStatus.BAD_REQUEST, "예약 확인 시간이 지난 예약입니다."),

    NOT_FOUND_KIOSK(HttpStatus.BAD_REQUEST, "해당 키오스크를 찾을 수 없습니다."),
    INVALID_KIOSK(HttpStatus.BAD_REQUEST, "해당 식당의 키오스크가 아닙니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "인증번호가 잘못되었습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
