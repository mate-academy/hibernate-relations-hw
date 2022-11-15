package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.GenericDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.exception.EntityNotFoundException;

public class ActorServiceImpl extends GenericServiceImpl<Actor> implements ActorService {

    public ActorServiceImpl(GenericDao<Actor> actorDao) {
        super(actorDao);
    }

    @Override
    public Actor get(Long id) {
        return dao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Actor not found with id "
                + id));
    }
}
