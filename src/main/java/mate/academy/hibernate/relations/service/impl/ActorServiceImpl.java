package mate.academy.hibernate.relations.service.impl;

import javax.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDaoImpl actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(()
                -> new EntityNotFoundException("No actor with such id " + id));
    }
}
