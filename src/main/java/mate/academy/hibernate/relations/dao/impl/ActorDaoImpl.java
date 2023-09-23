package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        factory.inTransaction(session -> session.persist(actor));
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = factory.openSession();
        Optional<Actor> actorFromDb = Optional.ofNullable(session.get(Actor.class, id));
        session.close();
        return actorFromDb;
    }
}
