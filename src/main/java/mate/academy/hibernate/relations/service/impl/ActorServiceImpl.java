package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return Optional.ofNullable(actorDao.get(id))
                .get()
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Actor entity with id=%d not found".formatted(id)
                        )
                );
    }
}
