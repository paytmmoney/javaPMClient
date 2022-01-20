package com.paytmmoney.equities.pmclient.exception;

public class ApplicationException extends Exception {
    // variables
    public String message;
    public int code;

    public ApplicationException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
