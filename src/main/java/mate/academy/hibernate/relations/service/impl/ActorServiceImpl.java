package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {

    private final ActorDao dao;

    public ActorServiceImpl(ActorDao dao) {
        this.dao = dao;
    }

    public Actor add(Actor entity) {
        return dao.add(entity);
    }

    public Actor get(Long id) {
        return dao.get(id).orElse(null);
    }
}
