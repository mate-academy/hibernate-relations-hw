package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class ActorDaoImpl extends AbstractDao implements ActorDao {

    public ActorDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Actor add(Actor actor) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(actor);
            tx.commit();
            return actor;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DataProcessingException("Can't add actor", e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor", e);
        }
    }
}

