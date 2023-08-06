package io.ukids.generalmeetingmanagementsystem.common.response;

import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    private final Boolean isSuccess;
    private final int status;
    private final String message;


    public static ApiResponse of(HttpStatusCode statusCodes) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), null);
    }

    public static ApiResponse of(HttpStatusCode statusCodes, String message) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), message);
    }

    public static ApiResponse of(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getHttpStatus(), errorCode.getMessage());
    }
}
