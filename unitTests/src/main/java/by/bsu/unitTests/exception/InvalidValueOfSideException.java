package by.bsu.unitTests.exception;

public class InvalidValueOfSideException extends Exception {
    public InvalidValueOfSideException() {

    }

    public InvalidValueOfSideException (String message) {
        super(message);
    }

    public InvalidValueOfSideException(Throwable cause) {
        super(cause);
    }

    public InvalidValueOfSideException(String message, Throwable cause) {
        super(message, cause);
    }
}
