package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor is null!");
        }

        Actor added = actorDao.add(actor);
        return added;
    }

    @Override
    public Actor get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null!");
        }

        Optional<Actor> actor = actorDao.get(id);

        if (actor.isEmpty()) {
            throw new IllegalArgumentException("Actor not found!");
        }
        return actor.get();
    }
}
