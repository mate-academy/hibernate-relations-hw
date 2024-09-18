package mate.academy.hibernate.relations.dao.impl;

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
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String actorDetails = (actor != null) ?
                    String.format("Actor[id=%d, name=%s]", actor.getId(), actor.getName()) :
                    "Unknown Actor";
            throw new DataProcessingException("Error while adding actor: " + actorDetails, e);
        }

        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Actor actor;
        try (Session session = factory.openSession()) {
            actor = session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting actor with id: " + id, e);
        }
        return Optional.ofNullable(actor);
    }
}
