package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private ActorDao actorDao = new ActorDaoImpl(sessionFactory);

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't get the actor by id: " + id));
    }
}
