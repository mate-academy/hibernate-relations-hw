package mate.academy.hibernate.relations.exeption;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception e) {
        super(message);
    }
}
