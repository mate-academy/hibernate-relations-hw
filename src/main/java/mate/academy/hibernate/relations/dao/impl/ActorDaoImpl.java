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
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot add actor: " + actor, e);
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        Actor actor;
        try {
            session = factory.openSession();
            actor = session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get actor by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(actor);
    }
}
