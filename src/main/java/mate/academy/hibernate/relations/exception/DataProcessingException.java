package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String s, Throwable e) {
        super(s, e);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
