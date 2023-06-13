package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao<Actor> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return addEntity(actor);
    }

    @Override
    public Optional<Actor> get(Long id) {
        return getEntity(id);
    }

    @Override
    public Class<Actor> getClassInstance() {
        return Actor.class;
    }
}
