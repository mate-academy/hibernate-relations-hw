package mate.academy.hibernate.relations.dao.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {

        this.factory = sessionFactory;
    }
}
