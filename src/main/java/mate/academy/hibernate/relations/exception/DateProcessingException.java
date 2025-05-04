package mate.academy.hibernate.relations.exception;

public class DateProcessingException extends RuntimeException {
    public DateProcessingException(String message) {
        super(message);
    }

    public DateProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
