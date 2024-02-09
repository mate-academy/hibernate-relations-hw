package mate.academy.service.impl;

import java.util.Optional;
import mate.academy.dao.ActorDao;
import mate.academy.dao.impl.ActorDaoImpl;
import mate.academy.exceptions.NoValueForParameterFound;
import mate.academy.model.Actor;
import mate.academy.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorOptional = actorDao.get(id);
        if (actorOptional.isPresent()) {
            return actorOptional.get();
        } else {
            throw new NoValueForParameterFound("Can't find an actor with id " + id);
        }
    }
}
