package mate.academy.hibernate.relations.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
