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

    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "해당 레스토랑이 없습니다."),
    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "해당 예약이 없습니다."),
    ALREADY_APPROVED_RESERVATION(HttpStatus.BAD_REQUEST, "이미 승인된 예약입니다."),
    ALREADY_DENIED_RESERVATION(HttpStatus.BAD_REQUEST, "이미 거절된 예약입니다."),
    NOT_VALID_RESTAURANT(HttpStatus.BAD_REQUEST, "해당 점장의 레스토랑이 아닙니다.")

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
