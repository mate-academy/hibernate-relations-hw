package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
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
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long id = (Long) session.save(actor);
                actor.setId(id);
                transaction.commit();
                return actor;
            } catch (Exception e) {
                transaction.rollback();
                throw new DataProcessingException("Error while adding an actor", e);
            }
        } catch (Exception e) {
            throw new DataProcessingException("Error while opening a session", e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting an actor " + id, e);
        }
    }
}
