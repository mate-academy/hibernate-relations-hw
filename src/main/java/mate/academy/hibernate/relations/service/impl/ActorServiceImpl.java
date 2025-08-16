package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
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
        if (actor == null) {
            throw new DataProcessingException("The argument (actor) is null");
        }
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        if (id == null) {
            throw new DataProcessingException("The argument (id) is null");
        }
        return actorDao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Can't get actor by id, actor is null. " + id));
    }
}
