package mate.academy.hibernate.relations.dao.impl;

import java.util.Objects;
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
            transaction.begin();
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw new DataProcessingException("Can't add actor to dataBase", e);
        } finally {
            Objects.requireNonNull(session).close();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            Actor actorFromDb = session.get(Actor.class, id);
            return Optional.ofNullable(actorFromDb);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor from dataBase by whom id: " + id, e);
        }
    }
}
