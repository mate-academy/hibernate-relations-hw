package mate.academy.hibernate.relations.exception;

public class DataProccesingException extends RuntimeException {
    public DataProccesingException(String message, Exception e) {
        super(message, e);
    }
}
