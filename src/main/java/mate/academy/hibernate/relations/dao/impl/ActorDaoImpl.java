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
            transaction = session.getTransaction();
            session.save(actor);
            transaction.commit();
        } catch (Exception e) {
            throw new DataProcessingException("Can't save actor to DB " + actor, e);
        } finally {
            transaction.rollback();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(super.factory.openSession().get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor with id=" + id + " from DB", e);
        }
    }
}
