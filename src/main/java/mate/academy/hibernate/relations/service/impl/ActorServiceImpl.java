package mate.academy.hibernate.relations.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorDao actorDao;

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).get();
    }
}
