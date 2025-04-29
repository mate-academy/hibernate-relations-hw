package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        try {
            return actorDao.add(actor);
        } catch (DataProcessingException e) {
            throw new RuntimeException("Service error adding Actor", e);
        }
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id)
                .orElseThrow(() -> new RuntimeException("Actor not found, id="
                        + id));
    }
}
