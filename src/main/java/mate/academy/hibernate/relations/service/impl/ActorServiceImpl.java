package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {

    private final ActorDao dao;

    public ActorServiceImpl(ActorDao actorDao) {
        dao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return dao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return dao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't get actor by id: " + id));
    }
}
