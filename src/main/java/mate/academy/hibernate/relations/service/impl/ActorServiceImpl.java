package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.DataProcessingException;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        if (actor == null) {
            throw new DataProcessingException("Actor can't be null");
        }
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        if (id == null) {
            throw new DataProcessingException("Id can't be null");
        }
        return actorDao.get(id).orElseThrow(
                () -> new DataProcessingException("Can't find actor by id " + id));
    }
}
