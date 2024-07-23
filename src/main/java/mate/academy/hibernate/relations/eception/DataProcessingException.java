package mate.academy.hibernate.relations.eception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String errorMessage) {
        super(errorMessage);
    }

    public DataProcessingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
