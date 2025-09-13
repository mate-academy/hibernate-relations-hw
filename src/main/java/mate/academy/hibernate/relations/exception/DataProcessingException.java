package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable t) {
        super(message, t);
    }
}
