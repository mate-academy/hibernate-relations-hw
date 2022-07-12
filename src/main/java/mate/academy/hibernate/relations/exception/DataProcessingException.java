package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable exc) {
        super(message, exc);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
