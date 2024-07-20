package mate.academy.hibernate.relations.dao.impl;

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
        String errorMessage = "Cannot add the actor: " + actor.toString();
        return (Actor) super.add(actor, errorMessage);
    }

    @Override
    public Optional<Actor> get(Long id) {
        String errormesage = "Cannot get actor(ID=" + id + ") from DB";
        return (Optional<Actor>) super.get(id, Actor.class.toString(), errormesage);
    }
}
