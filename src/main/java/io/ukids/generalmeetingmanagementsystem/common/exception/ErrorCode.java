package io.ukids.generalmeetingmanagementsystem.common.exception;

import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Global Exception
    BAD_REQUEST_ERROR(HttpStatusCode.BAD_REQUEST.getStatus(), "잘못된 요청입니다."),
    INVALID_HTTP_MESSAGE_BODY(HttpStatusCode.BAD_REQUEST.getStatus(),"HTTP 요청 바디의 형식이 잘못되었습니다."),
    UNSUPPORTED_HTTP_METHOD(HttpStatusCode.METHOD_NOT_ALLOWED.getStatus(),"지원하지 않는 HTTP 메서드입니다."),
    SERVER_ERROR(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatus(),"서버 내부에서 알 수 없는 오류가 발생했습니다."),

    // Business Exception
    NOT_AUTHENTICATED(HttpStatusCode.UNAUTHORIZED.getStatus(), "등록된 인증 정보가 없습니다."),

    MEMBER_NOT_FOUND(HttpStatusCode.BAD_REQUEST.getStatus(), "유저를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 가입된 유저입니다."),
    MEMBER_ALREADY_ACTIVATE(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 활성화된 유저입니다.."),
    MEMBER_ALREADY_BLOCK(HttpStatusCode.BAD_REQUEST.getStatus(), "활성화 되지 않은 유저입니다."),

    MEETING_NOT_FOUND(HttpStatusCode.BAD_REQUEST.getStatus(), "회의를 찾을 수 없습니다."),
    MEETING_ALREADY_START(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 시작된 회의입니다."),
    MEETING_ALREADY_END(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 종료된 회의입니다."),

    AGENDA_ALREADY_VOTED(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 투표한 안건입니다."),
    AGENDA_ALREADY_STARTED(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 시작된 안건입니다."),
    AGENDA_ALREADY_ENDED(HttpStatusCode.BAD_REQUEST.getStatus(), "이미 종료된 안건입니다."),
    AGENDA_NOT_FOUND(HttpStatusCode.BAD_REQUEST.getStatus(), "안건을 찾을 수 없습니다."),
    AGENDA_NOT_MATCHES_TO_MEETING(HttpStatusCode.BAD_REQUEST.getStatus(), "안건과 총회가 일치하지 않습니다.")
    ;

    private final int httpStatus;
    private final String message;
}
