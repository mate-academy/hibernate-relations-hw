package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable exception) {
        super(message, exception);
    }
}
