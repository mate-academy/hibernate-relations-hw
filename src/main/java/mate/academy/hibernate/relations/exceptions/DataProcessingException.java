package mate.academy.hibernate.relations.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String s) {
        super(s);
    }

    public DataProcessingException(String s, Exception ex) {
        super(s, ex);
    }
}
