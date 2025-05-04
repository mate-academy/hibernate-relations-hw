package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        if (actor != null) {
            return actorDao.add(actor);
        }
        throw new RuntimeException("Cannot add null actor");
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actor = actorDao.get(id);
        if (actor.isPresent()) {
            return actor.get();
        }
        throw new RuntimeException("Cannot find actor with id: " + id);
    }
}
