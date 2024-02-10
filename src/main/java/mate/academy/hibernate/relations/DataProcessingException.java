package mate.academy.hibernate.relations;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String msg, Exception e) {
        super(msg, e);
    }
}
