package mate.academy.hibernate.relations.service.impl;

import org.hibernate.SessionFactory;

public abstract class AbstractServiceImpl {
    protected final SessionFactory factory;

    protected AbstractServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }
}
