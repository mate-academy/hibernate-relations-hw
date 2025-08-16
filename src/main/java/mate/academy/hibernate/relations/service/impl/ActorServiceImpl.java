package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private static ActorDao actorDao;

    public ActorServiceImpl(ActorDaoImpl actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actor = actorDao.get(id);
        if (actor.isPresent()) {
            return actor.get();
        }
        throw new NoSuchElementException("Cannot get actor with id " + id);
    }
}
