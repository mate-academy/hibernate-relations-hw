package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException() {

    }

    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable e) {
        super(message, e);
    }
}
