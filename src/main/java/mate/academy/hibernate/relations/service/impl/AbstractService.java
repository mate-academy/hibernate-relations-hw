package mate.academy.hibernate.relations.service.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractService {
    protected final SessionFactory factory;

    protected AbstractService(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
