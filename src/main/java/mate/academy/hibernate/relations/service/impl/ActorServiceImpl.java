package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class ActorServiceImpl implements ActorService {
    public ActorServiceImpl(ActorDaoImpl actorDao) {
    }

    @Override
    public Actor add(Actor actor) {
        ActorDao actorDao = new ActorDaoImpl(HibernateUtil.getSessionFactory());
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        ActorDao actorDao = new ActorDaoImpl(HibernateUtil.getSessionFactory());
        return actorDao.get(id).orElse(null);
    }
}
