package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import java.util.zip.DataFormatException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.lib.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        try (Session session = factory.openSession()) {
            session.save(actor);
        } catch (HibernateException exception) {
            throw new DataProcessingException("Can't add actor" + actor + "to DB", exception);
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (HibernateException exception) {
            throw new DataProcessingException("Can't get actor by id=" + id, exception);
        }
    }
}
