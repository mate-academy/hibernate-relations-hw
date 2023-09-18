package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private SessionFactory factory;

    public ActorServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Actor add(Actor actor) {
        return new ActorDaoImpl(factory).add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorGetOptional = new ActorDaoImpl(factory).get(id);
        if (actorGetOptional.isEmpty()) {
            return null;
        }
        Actor actorProcessed = actorGetOptional.get();
        return actorProcessed;
    }
}
