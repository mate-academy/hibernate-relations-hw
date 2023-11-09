package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exeptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class ActorServiceImpl implements ActorService {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final ActorDao actorDao = new ActorDaoImpl(sessionFactory);

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> actorOptional = actorDao.get(id);
        if (actorOptional.isEmpty()) {
            throw new DataProcessingException("Actor with id = " + id + " no exist.");
        }
        return actorOptional.get();
    }
}
