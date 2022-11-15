package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends GenericDaoImpl<Actor> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            Actor actor = session.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Failed to get Actor from db by id " + id, e);
        }
    }
}
