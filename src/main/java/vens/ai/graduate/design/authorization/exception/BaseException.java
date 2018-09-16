package vens.ai.graduate.design.authorization.exception;

/**
 * @author vens
 * @date 2018-05-07 15:07
 **/
public class BaseException extends Exception {
    String errorCode;
    String errorMessage;

    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
