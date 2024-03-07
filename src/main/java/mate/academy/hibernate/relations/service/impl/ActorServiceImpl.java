package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {

    private SessionFactory sessionFactory;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        return actorDao.get(id).get();
    }
}
