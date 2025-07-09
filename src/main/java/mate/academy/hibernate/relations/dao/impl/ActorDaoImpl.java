package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.impl.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can't add actor", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = factory.openSession();
        try {
            Actor actor = session.get(Actor.class, id);
            if (actor == null) {
                return Optional.empty();
            }
            return Optional.of(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor by id " + id, e);
        } finally {
            session.close();

        }
    }
}
