package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
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
    public Actor add(Actor actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
            return actor;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not to add actor " + actor,e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            return Optional.ofNullable(session.find(Actor.class, id));
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not to find actor " + id,e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
