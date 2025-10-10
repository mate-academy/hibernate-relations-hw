package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
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

        actorDao.add(actor);
        Optional<Actor> addedActor = actorDao.get(actor.getId());
        if (addedActor.isPresent()) {
            return addedActor.get();
        }
        return addedActor.orElseThrow(() -> new DataProcessingException(
                "Actor " + actor + " wasn't added to the database", null));
    }

    @Override
    public Actor get(Long id) {

        Optional<Actor> actor = actorDao.get(id);
        if (actor.isPresent()) {
            return actor.get();
        }
        return actor.orElseThrow(() -> new DataProcessingException(
                "Actor with id " + id + " does not exist in database", null));
    }
}
