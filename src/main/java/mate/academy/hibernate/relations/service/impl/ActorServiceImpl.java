package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao abstractDao;

    public ActorServiceImpl(ActorDaoImpl actorDao) {
        this.abstractDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return abstractDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actor = abstractDao.get(id);
        if (actor.isEmpty()) {
            throw new DataProcessingException("Actor not found with id: " + id);
        }
        return actor.get();
    }
}
