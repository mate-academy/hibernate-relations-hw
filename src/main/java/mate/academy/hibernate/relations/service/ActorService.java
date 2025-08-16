package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.lib.Service;
import mate.academy.hibernate.relations.model.Actor;

@Service
public interface ActorService {
    Actor add(Actor actor);

    Actor get(Long id);
}
