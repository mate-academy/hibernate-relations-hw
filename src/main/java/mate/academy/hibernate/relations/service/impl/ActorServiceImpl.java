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
        try {
            return actorDao.add(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Error adding actor: " + actor, e);
        }
    }

    @Override
    public Actor get(Long id) {
        try {
            return actorDao.get(id)
                    .orElseThrow(()
                            -> new DataProcessingException("Actor not found with id " + id));
        } catch (Exception e) {
            throw new DataProcessingException("Error fetching actor with id " + id, e);
        }
    }
}
