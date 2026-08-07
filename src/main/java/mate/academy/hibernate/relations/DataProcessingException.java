package mate.academy.hibernate.relations;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String massage, Throwable throwable) {
        super(massage, throwable);
    }
}
