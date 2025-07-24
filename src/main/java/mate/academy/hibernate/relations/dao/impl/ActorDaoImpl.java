package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.exception.DataProcessingException;
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
            throw new DataProcessingException(
                    "Помилка додавання актора до бази даних: " + actor, e);
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
        } catch (RuntimeException e) {
            throw new DataProcessingException("Помилка зчитування індифікатора актора: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
