package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl extends AbstractService implements ActorService {
    public ActorServiceImpl(AbstractDao abstractDao) {
        super(abstractDao);
    }

    @Override
    public Actor add(Actor actor) {
        ActorDao actorDao = (ActorDao) abstractDao;
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        ActorDao actorDao = (ActorDao) abstractDao;
        return actorDao.get(id).get();
    }
}
