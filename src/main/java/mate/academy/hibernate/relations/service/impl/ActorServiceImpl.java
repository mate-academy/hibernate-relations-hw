package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
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
        if (actor.getName() == null || actor.getName().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        return actorDao.get(id)
                .orElseThrow(() -> new DataProcessingException(
                        "Can not find actor with id: " + id));
    }
}
