package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String text, Throwable e) {
        super(text, e);
    }
}
