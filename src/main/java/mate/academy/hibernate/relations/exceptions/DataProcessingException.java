package mate.academy.hibernate.relations.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable e) {
        super(message, e);
    }
}
