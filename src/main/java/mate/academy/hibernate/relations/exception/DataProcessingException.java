package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }
    public DataProcessingException(String message, Exception exception) {
        super(message, exception);
    }
}
