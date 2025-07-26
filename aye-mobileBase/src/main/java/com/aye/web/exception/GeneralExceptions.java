package com.aye.web.exception;

/**
 * Created by Munna on 08/06/2022.
 */
public class GeneralExceptions extends RuntimeException {

    private String errorMessage;

    public GeneralExceptions(String message, Throwable cause) {
        super(message, cause);

    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
