package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exeption.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    private final SessionFactory sessionFactory;

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add a actor" + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Actor actor = session.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve a actor for id " + id, e);
        }
    }
}
