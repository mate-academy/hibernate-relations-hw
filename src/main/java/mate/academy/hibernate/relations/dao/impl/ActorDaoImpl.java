package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.excpetion.DataProcessingException;
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
            session = super.factory.openSession();
            transaction = session.beginTransaction();
            Long id = (Long) session.save(actor);
            transaction.commit();

            actor.setId(id);
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't commit the transaction", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        Actor actor;
        try (Session session = super.factory.openSession()) {
            actor = session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get the movie by id: " + id, e);
        }
        return Optional.ofNullable(actor);
    }
}
