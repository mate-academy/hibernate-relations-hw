package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

import java.util.Optional;

public class ActorServiceImpl implements ActorService {
    private static ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> optionalActor = actorDao.get(id);
        if (optionalActor.isPresent()) {
            return optionalActor.get();
        } else {
            throw new RuntimeException("Actor with id " + id + " not found");
        }
    }
}
