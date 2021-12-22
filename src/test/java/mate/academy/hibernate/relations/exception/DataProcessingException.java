package mate.academy.hibernate.relations.exception;

import org.hibernate.HibernateException;

public class DataProcessingException extends Throwable {
    public DataProcessingException(String s, HibernateException e) {
    }
}
