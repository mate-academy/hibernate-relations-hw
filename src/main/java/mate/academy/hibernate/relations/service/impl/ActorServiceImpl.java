package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.lib.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private ActorDao actorDao;
    private SessionFactory sessionFactory;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorOptional = actorDao.get(id);
        if (actorOptional.isPresent()) {
            return actorOptional.get();
        }
        throw new DataProcessingException("Not found in DB actor with id = " + id);
    }
}
