package app.test.exceptions;

public class TechnicalErrorException extends RuntimeException{
    public TechnicalErrorException(String message) {
        super(message);
    }
}
