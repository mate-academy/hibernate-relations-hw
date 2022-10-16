package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.AbstractDao;

public abstract class AbstractService {
    protected final AbstractDao abstractDao;

    protected AbstractService(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }
}
