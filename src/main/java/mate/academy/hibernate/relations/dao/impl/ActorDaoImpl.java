package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao<Actor> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = super.factory.openSession();
        Actor actor = session.get(Actor.class, id);
        session.close();
        return Optional.ofNullable(actor);
    }
}
