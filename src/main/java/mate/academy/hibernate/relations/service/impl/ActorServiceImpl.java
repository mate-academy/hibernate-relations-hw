package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        if (actor != null) {
            return actorDao.add(actor);
        }
        throw new RuntimeException("Actor can't be null!");
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't find actor with id: " + id));
    }
}
