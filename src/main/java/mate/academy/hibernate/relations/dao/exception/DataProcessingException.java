package mate.academy.hibernate.relations.dao.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException() {
        super();
    }

    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
