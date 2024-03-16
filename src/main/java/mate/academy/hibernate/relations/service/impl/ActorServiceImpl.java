package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import java.util.function.Supplier;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {

    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorOptional = actorDao.get(id);
        return actorOptional.orElseThrow((Supplier<RuntimeException>) ()
                -> new DataProcessingException("could not retrieve "
                + "actor with id: " + id, null));
    }
}
