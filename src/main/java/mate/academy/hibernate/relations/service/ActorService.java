package mate.academy.hibernate.relations.service;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorService {
    Actor add(Actor actor);

    Optional<Actor> get(Long id);
}
