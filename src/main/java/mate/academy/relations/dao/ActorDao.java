package mate.academy.relations.dao;

import java.util.Optional;
import mate.academy.relations.model.Actor;

public interface ActorDao {
    Actor add(Actor actor);

    Optional<Actor> get(Long id);
}
