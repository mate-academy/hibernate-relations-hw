package mate.academy.hibernate.relations.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
