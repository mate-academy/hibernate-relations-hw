package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(final String message) {
        super(message);
    }

    public DataProcessingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
