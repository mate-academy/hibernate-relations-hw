package mate.academy.hibernate.relations.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message,RuntimeException e) {
        super(message,e);
    }


}
