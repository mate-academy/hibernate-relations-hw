package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception ex) {
        super(message, ex);
    }

    public DataProcessingException(String message) {
        super(message);
    }

}
