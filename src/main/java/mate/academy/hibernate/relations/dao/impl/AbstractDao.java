package mate.academy.hibernate.relations.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return factory.getCurrentSession();

    }
}
