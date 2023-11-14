package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl extends AbstractDao implements ActorService {

    private ActorDao actorDao = new ActorDaoImpl(factory);

    public ActorServiceImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorOptional = actorDao.get(id);
        return actorOptional.orElse(null);
    }
}
