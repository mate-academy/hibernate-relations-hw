package mate.academy.hibernate.relations.service.impl;

import org.hibernate.SessionFactory;

public class AbstractService {
    protected SessionFactory factory;

    protected AbstractService(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
