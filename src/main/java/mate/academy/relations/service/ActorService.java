package mate.academy.relations.service;

import mate.academy.relations.model.Actor;

public interface ActorService {
    Actor add(Actor actor);

    Actor get(Long id);
}
