package mate.academy.hibernate.relations.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return (Actor) super.factory.openSession().save(actor);
    }

    @Override
    public Actor get(Long id) {
        try (Session session = super.factory.openSession()){
            return session.get(Actor.class, id);
        } catch (Exception e) {
            throw new NoSuchElementException("Actor not found with id: " + id);
        }
    }
}
