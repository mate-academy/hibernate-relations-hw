package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actordao;

    public ActorServiceImpl(ActorDao actordao) {
        this.actordao = actordao;
    }

    @Override
    public Actor add(Actor actor) {
        return actordao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actordao.get(id).get();
    }
}
