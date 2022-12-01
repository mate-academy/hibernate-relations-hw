package mate.academy.hibernate.relations.dao;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorDao {
    Actor add(Actor actor) throws DataProcessingException;

    Optional<Actor> get(Long id);
}
