package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            try {
                Long actorId = (Long) session.save(actor);
                session.getTransaction().commit();
                actor.setId(actorId);
                return actor;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new DataProcessingException("Error while adding actor to the database", e);
            }
        } catch (Exception e) {
            throw new DataProcessingException("Error while opening session", e);
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            Actor actor = session.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting actor from the database", e);
        }
    }
}
