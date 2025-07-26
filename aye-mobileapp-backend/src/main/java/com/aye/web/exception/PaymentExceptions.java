package com.aye.web.exception;

/**
 * Created by Munna on 08/06/2022.
 */
public class PaymentExceptions extends Exception {

    private String errorMessage;

    public PaymentExceptions(String message, Throwable cause) {
        super(message, cause);

    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
