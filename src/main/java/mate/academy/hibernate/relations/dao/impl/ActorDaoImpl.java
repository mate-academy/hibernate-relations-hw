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
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DataProcessingException("Failed to add " + actor.toString()
                    + " to database.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        Transaction transaction = null;
        Actor result = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            result = session.get(Actor.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DataProcessingException("Failed to get actor with ID " + id
                    + " from database.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return Optional.ofNullable(result);
    }
}
