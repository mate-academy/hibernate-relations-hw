package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    private static final String EXCEPTION_ADD
            = "Exception while trying to persist entity to DB.";

    private static final String EXCEPTION_GET
            = "Exception while trying to get entity from DB with id = ";

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(EXCEPTION_ADD, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Transaction transaction = null;
        Actor actor = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            actor = session.get(Actor.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(EXCEPTION_GET + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(actor);
    }
}
