package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.persist(actor);
            tx.commit();
            return actor;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException("Can't add Actor " + actor, e);
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
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Actor by id " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
