package com.swiftbeard.ecommerce_app.exception;


import java.io.Serial;

public class GenericAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public GenericAlreadyExistsException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public GenericAlreadyExistsException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.RESOURCE_NOT_FOUND.getErrCode();
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
