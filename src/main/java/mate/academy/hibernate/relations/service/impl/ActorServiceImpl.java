package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao dao;

    public ActorServiceImpl(ActorDao dao) {
        this.dao = dao;
    }

    @Override
    public Actor add(Actor actor) {
        return dao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return dao.get(id).orElseThrow(() -> new DataProcessingException(
                "Actor not found with id " + id));
    }
}
