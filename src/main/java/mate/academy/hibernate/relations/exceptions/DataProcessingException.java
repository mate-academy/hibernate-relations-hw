package mate.academy.hibernate.relations.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Exception exception) {
        super(message, exception);
    }
}
