package com.swiftbeard.ecommerce_app.exception;

public class ErrorUtils {

    private ErrorUtils() {
    }

    /**
     * Creates and return an error object
     *
     * @param errMsgKey
     * @param errorCode
     * @param httpStatusCode
     * @return error
     */
    public static Error createError(final String errMsgKey, final String errorCode,
                                    final Integer httpStatusCode) {
        Error error = new Error();
        error.setMessage(errMsgKey);
        error.setErrorCode(errorCode);
        error.setStatus(httpStatusCode);
        return error;
    }
}