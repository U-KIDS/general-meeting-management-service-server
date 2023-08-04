package io.ukids.generalmeetingmanagementsystem.common.response;

import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class ApiResponse<T> {

    private final String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    private final Boolean isSuccess;
    private final int status;
    private final T data;
    private final String message;


    public static <T> ApiResponse<T> of(HttpStatusCode statusCodes) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), null, null);
    }

    public static <T> ApiResponse<T> of(HttpStatusCode statusCodes, T data) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), data, null);
    }

    public static <T> ApiResponse<T> of(HttpStatusCode statusCodes, String message) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), null, message);
    }

    public static <T> ApiResponse<T> of(HttpStatusCode statusCodes, T data, String message) {
        return new ApiResponse<>(statusCodes.getIsSuccess(), statusCodes.getStatus(), data, message);
    }

    public static <T> ApiResponse<T> of(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getHttpStatus(), null, errorCode.getMessage());
    }
}
