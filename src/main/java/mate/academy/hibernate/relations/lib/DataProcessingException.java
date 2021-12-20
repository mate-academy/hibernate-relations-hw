package mate.academy.hibernate.relations.lib;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, RuntimeException exception) {
        super(message, exception);
    }
}
