package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor must not be null");
        }
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be positive and not null");
        }
        return actorDao.get(id)
                .orElseThrow(() -> new RuntimeException("Actor with id " + id + " not found"));
    }
}
