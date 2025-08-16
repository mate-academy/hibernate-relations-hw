package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
