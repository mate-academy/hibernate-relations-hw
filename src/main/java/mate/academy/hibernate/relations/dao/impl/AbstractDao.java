package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.lib.Dao;
import org.hibernate.SessionFactory;

@Dao
public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
}
