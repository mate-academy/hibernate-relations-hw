package mate.academy.hibernate.relations.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorDao {
    Actor add(Actor actor);

    Optional<Actor> get(Long id);

    List<Actor> getAll();

    Actor update(Actor actor);

    void delete(Long id);
}
