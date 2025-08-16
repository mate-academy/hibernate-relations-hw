package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) throws DataProcessingException {
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
            throw new DataProcessingException("Can't add actor to the DB: " + actor, e);
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) throws DataProcessingException {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get the Actor object with ID:  " + id, e);
        }
    }
}
