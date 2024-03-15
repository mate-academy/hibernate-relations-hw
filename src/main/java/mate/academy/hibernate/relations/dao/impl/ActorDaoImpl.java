package mate.academy.hibernate.relations.dao.impl;

import jakarta.persistence.EntityNotFoundException;
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
        if (actor == null) {
            throw new RuntimeException("Link to the actor is null");
        }
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        } catch (EntityNotFoundException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add this actor to db - " + actor, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        if (id == null) {
            throw new RuntimeException("This 'id' is null");
        }
        Actor actor;
        try (Session session = factory.openSession()) {
            actor = session.get(Actor.class, id);
        } catch (EntityNotFoundException ex) {
            throw new DataProcessingException("Can't get actor from db by id = " + id, ex);
        }
        return Optional.ofNullable(actor);
    }
}
