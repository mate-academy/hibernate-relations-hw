package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.SessionFactory;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        return null;
    }

    @Override
    public Optional<Actor> get(Long id) {
        return null;
    }

    @Override
    public List<Actor> getAll() {
        return null;
    }

    @Override
    public Actor update(Actor actor) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
