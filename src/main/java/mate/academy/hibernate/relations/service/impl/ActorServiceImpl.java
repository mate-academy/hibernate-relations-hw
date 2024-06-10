package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(SessionFactory factory) {
        this.actorDao = new ActorDaoImpl(factory);
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actor = actorDao.get(id);
        return actor.orElse(null);
    }
}
