package mate.academy.hibernate.relations.service;

import org.hibernate.SessionFactory;

public abstract class AbstractService {
    protected SessionFactory factory;

    protected AbstractService(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
