package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }
}
