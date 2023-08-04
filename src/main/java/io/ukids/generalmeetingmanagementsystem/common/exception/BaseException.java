package io.ukids.generalmeetingmanagementsystem.common.exception;

import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private final ErrorCode errorCode;
}