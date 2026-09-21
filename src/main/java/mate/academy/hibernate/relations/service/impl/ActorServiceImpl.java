package mate.academy.hibernate.relations.service.impl;

import static mate.academy.hibernate.relations.util.HibernateUtil.getSessionFactory;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private static final ActorDao actorDao = new ActorDaoImpl(getSessionFactory());

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(()
                -> new DataProcessingException("Actor with id " + id + " not found"));
    }
}
