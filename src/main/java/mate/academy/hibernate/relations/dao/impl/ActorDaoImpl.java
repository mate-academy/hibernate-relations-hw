package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    private SessionFactory instance = null;

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        instance = sessionFactory;
    }

    @Override
    public Actor add(Actor actor) {
        SessionFactory sessionFactory = instance;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding actor", e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        SessionFactory sessionFactory = ActorDaoImpl.this.instance;
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error getting actor", e);
        }
    }
}
