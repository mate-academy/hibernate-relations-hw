package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        if (actor.getName() == null || actor.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        return actorDao.get(id).orElseThrow(
                () -> new RuntimeException("Actor Not Found, id = " + id));
    }
}
