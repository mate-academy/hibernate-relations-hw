package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao;

    public ActorServiceImpl(ActorDaoImpl actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return new ActorDaoImpl(HibernateUtil.getSessionFactory()).add(actor);
    }

    @Override
    public Actor get(Long id) {
        ActorDao actorDao = new ActorDaoImpl(HibernateUtil.getSessionFactory());
        return actorDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can't get actor with id " + id));
    }
}
