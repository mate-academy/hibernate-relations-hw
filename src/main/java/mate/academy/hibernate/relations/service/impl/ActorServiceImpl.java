package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao = new ActorDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Actor add(Actor actor) {
        if (actor == null) {
            throw new DataProcessingException("ERROR: The actor is null!");
        }
        actorDao.add(actor);
        return actor;
    }

    @Override
    public Actor get(Long id) {
        if (id == null) {
            throw new DataProcessingException("ERROR: The id is null!");
        }
        return actorDao.get(id).orElseThrow();
    }
}
