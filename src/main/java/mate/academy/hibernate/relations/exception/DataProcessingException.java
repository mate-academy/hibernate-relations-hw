package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String s, RuntimeException e) {
        super(s, e);
    }
}
