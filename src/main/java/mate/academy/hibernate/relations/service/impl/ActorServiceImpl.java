package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exceptions.DataNotFoundException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl extends AbstractService implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        super();
        this.actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        validateService.validateNotNull(actor, "Actor can't be null");
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        validateService.validateNotNull(id, "Id can't be null.");
        return actorDao.get(id).orElseThrow(() -> new DataNotFoundException("Actor with id "
                + id + " not found"));
    }
}
