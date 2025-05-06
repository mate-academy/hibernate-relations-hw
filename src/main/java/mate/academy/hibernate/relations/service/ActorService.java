package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;

public interface ActorService {
    Actor add(Actor actor) throws DataProcessingException;

    Actor get(Long id) throws DataProcessingException;
}
