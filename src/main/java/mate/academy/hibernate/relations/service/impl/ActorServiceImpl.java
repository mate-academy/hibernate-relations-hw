package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.DataProcessingException;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao = new ActorDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Actor add(Actor actor) {
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can't get an actor from database with id " + id));
    }
}
