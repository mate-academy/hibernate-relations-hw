package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        try {
            factory.inTransaction(session ->
                    session.persist(actor));
            return actor;
        } catch (Exception e) {
            throw new DataProcessingException("Can't add a new Actor: " + actor, e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try {
            return Optional.ofNullable(factory.fromSession(session ->
                    session.find(Actor.class, id)));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get an Actor by ID: " + id, e);
        }
    }
}
