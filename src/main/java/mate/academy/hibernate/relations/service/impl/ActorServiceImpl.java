package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    public static final String ACTOR_NULL_EXCEPTION_MESSAGE = "Actor can't be null";
    public static final String ID_NULL_EXCEPTION_MESSAGE = "Id can't be null";
    public static final String ACTOR_NOT_FOUND_MESSAGE = "Actor with id: %d was not found.";

    private ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        if (actor == null) {
            throw new RuntimeException(ACTOR_NULL_EXCEPTION_MESSAGE);
        }
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_NULL_EXCEPTION_MESSAGE);
        }

        return actorDao.get(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format(ACTOR_NOT_FOUND_MESSAGE, id)
                        )
                );
    }
}
