package mate.academy.hibernate.relations.exeptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
