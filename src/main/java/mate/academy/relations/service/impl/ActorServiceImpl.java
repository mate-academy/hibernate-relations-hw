package mate.academy.relations.service.impl;

import mate.academy.relations.dao.ActorDao;
import mate.academy.relations.dao.impl.ActorDaoImpl;
import mate.academy.relations.model.Actor;
import mate.academy.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow();
    }
}
