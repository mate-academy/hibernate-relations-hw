package mate.academy.hibernate.relations.service;

import java.util.List;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorService {
    Actor add(Actor actor);

    Actor get(Long id);

    List<Actor> getAll();

    Actor update(Actor actor);

    void delete(Long id);
}
