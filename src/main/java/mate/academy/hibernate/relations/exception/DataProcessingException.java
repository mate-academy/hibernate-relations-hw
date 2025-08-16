package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable err) {
        super(message, err);
    }
}
