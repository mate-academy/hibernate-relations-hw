package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private static ActorDaoImpl actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    //TODO: Maybe should change null for new Actor()
    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseGet(null);
    }
}
