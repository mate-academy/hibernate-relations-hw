package mate.academy.hibernate.relations.dao.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
