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
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot save Actor", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = factory.openSession();
        try {
            Actor actor = session.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get Actor with id " + id, e);
        } finally {
            session.close();
        }
    }
}
