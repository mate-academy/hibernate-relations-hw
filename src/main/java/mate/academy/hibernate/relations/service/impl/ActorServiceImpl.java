package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import java.util.NoSuchElementException;

public class ActorServiceImpl implements ActorService {
    private static final String EXCEPTION_MESSAGE = "Can't get movie by id=";

    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(
                () -> new NoSuchElementException(EXCEPTION_MESSAGE + id));
    }
}
