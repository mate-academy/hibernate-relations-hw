package mate.academy.hibernate.relations.exeption;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
