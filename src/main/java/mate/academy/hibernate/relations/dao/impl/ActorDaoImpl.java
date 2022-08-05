package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new DataProcessingException("Error when adding an actor: " + actor, e);
        } finally {
            session.close();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        Actor actor = null;
        try {
            session = factory.openSession();
            actor = session.get(Actor.class, id);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error when getting an actor: " + actor, e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(actor);
    }
}
