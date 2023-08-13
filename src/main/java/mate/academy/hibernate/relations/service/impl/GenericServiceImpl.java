package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.GenericDao;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

    protected final GenericDao<T> dao;

    public GenericServiceImpl(GenericDao<T> actorDao) {
        this.dao = actorDao;
    }

    @Override
    public T add(T t) {
        return dao.add(t);
    }

    @Override
    public abstract T get(Long id);
}
