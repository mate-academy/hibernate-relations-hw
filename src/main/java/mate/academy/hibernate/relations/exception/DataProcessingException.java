package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String messages, Throwable cause) {
        super(messages, cause);
    }
}
