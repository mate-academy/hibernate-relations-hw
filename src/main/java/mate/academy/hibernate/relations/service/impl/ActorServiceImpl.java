package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorServiceImpl implements ActorService {
    private final SessionFactory factory;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding Movie: " + actor, e);
        } finally {
            assert session != null;
            session.close();
        }
    }

    @Override
    public Actor get(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Actor with ID " + id, e);
        }
    }
}
