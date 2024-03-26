package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private ActorDaoImpl movieDao;

    public ActorServiceImpl(ActorDaoImpl movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Actor add(Actor actor) {
        return movieDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new NoSuchElementException("No movie found for id: " + id));
    }
}
