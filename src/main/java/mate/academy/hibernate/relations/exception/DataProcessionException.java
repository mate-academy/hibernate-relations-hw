package mate.academy.hibernate.relations.exception;

public class DataProcessionException extends RuntimeException {
    public DataProcessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProcessionException(String message) {
        super(message);
    }
}
