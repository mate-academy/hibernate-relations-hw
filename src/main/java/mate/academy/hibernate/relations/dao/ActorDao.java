package mate.academy.hibernate.relations.dao;

import mate.academy.hibernate.relations.model.Actor;

public interface ActorDao {
    Actor add(Actor actor);

    Actor get(Long id);
}
