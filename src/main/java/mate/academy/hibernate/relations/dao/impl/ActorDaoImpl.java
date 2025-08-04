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
        Session session = null;
        try {
            session = super.factory.openSession();
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding actor", e);
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

        try {
            session = super.factory.openSession();
            transaction = session.beginTransaction();
            Actor actor = session.get(Actor.class, id);
            transaction.commit();

            return Optional.ofNullable(actor);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error getting actor", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
