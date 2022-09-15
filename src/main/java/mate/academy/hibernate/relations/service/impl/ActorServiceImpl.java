package mate.academy.hibernate.relations.service.impl;

import mate.academy.lib.Service;
import mate.academy.lib.Injector;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
    Injector injector = Injector.getInstance("mate/academy/hibernate/relations");
    ActorDao actorDao = (ActorDao) injector.getInstance(ActorDao.class);

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).get();
    }
}
