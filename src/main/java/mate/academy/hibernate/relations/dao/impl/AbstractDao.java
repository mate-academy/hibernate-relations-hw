package mate.academy.hibernate.relations.dao.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory factory) {
        this.factory = factory;
    }
}
