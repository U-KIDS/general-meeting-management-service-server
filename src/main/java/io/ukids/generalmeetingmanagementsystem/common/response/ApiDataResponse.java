package io.ukids.generalmeetingmanagementsystem.common.response;

import lombok.ToString;

@ToString
public class ApiDataResponse<T> extends ApiResponse{

    T data;

    public ApiDataResponse(HttpStatusCode httpStatusCode, T data, String message) {
        super(httpStatusCode.getIsSuccess(), httpStatusCode.getStatus(), message);
    }

    public static <T> ApiDataResponse<T> of(HttpStatusCode statusCode, T data) {
        return new ApiDataResponse<>(statusCode, data, null);
    }

    public static <T> ApiDataResponse<T> of(HttpStatusCode statusCode, T data, String message) {
        return new ApiDataResponse<>(statusCode, data, message);
    }
}
