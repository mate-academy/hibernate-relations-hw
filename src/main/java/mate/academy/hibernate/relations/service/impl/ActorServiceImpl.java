package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final ActorDao ACTOR_DAO = new ActorDaoImpl(sessionFactory);

    public ActorServiceImpl(SessionFactory sessionFactory) {
        ActorServiceImpl.sessionFactory = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        return ACTOR_DAO.add(actor);
    }

    @Override
    public Actor get(Long id) {
        Optional<Actor> optionalActor = ACTOR_DAO.get(id);
        return optionalActor.orElse(null);
    }
}
