package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Actor;

import java.util.Optional;

public interface ActorService {
    Actor add(Actor actor);

    Optional<Actor> get(Long id);
}
