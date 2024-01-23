package mate.academy.hibernate.relations.exception;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
