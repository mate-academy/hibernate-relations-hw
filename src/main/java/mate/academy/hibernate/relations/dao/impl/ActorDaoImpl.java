package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessionException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        try {
            return factory.fromTransaction(session -> {
                session.persist(actor);
                return actor;
            });
        } catch (Exception e) {
            throw new DataProcessionException("Can't add actor to DB: " + actor.getName(), e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessionException("Can't get Actor by id: " + id, e);
        }
    }
}
