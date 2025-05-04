package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl extends AbstractService implements ActorService {
    private static ActorDao actorDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        initActorDao();
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't get actor by id: " + id));
    }

    private void initActorDao() {
        actorDao = new ActorDaoImpl(factory);
    }
}
