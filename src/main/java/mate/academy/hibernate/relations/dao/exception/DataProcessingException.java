package mate.academy.hibernate.relations.dao.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable e) {
        super(message, e);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
