package com.gojek.parking.exception;

import com.gojek.parking.util.ErrorCodes;

public class BaseException extends RuntimeException {

    private String errorCode;
    private String userMessage;

    public BaseException(String code, String message) {
        super(message);
        this.errorCode = code;
    }
    public BaseException(String code, String message, String userMessage) {
        super(message);
        this.errorCode = code;
        this.userMessage = userMessage;
    }
    public BaseException(String message) {
        super(message);
        this.errorCode = ErrorCodes.UNKNOWN_ERROR;
    }
    public BaseException(String code, Throwable throwable) {
        super(throwable);
        this.errorCode = code;
    }
    public String getErrorCode()
    {
        return this.errorCode;
    }

    public String getUserMessage()
    {
        return userMessage;
    }
}
