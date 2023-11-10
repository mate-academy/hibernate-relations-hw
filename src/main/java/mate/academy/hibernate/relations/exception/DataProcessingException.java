package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public <T> DataProcessingException(String message, T ex) {
        super(message);
    }
}
