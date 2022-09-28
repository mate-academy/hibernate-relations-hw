package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exeptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao<Actor> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Actor> get(Long id) {
        Actor actor = null;
        try (Session session = factory.openSession()) {
            actor = session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can not get actor from DB with id " + id);
        }
        return Optional.ofNullable(actor);
    }

}
