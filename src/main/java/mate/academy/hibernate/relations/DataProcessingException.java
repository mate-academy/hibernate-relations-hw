package mate.academy.hibernate.relations;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String exceptionInformation, Throwable e) {
        super(exceptionInformation, e);
    }

    public DataProcessingException(String exceptionInformation) {
        super(exceptionInformation);
    }

    public DataProcessingException(RuntimeException e) {
        super(e);
    }
}

