package mate.academy.hibernate.relations.service.impl;

import java.util.List;
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
        return actorDao.get(id).orElseThrow(() ->
                new EntityNotFoundException("Actor with id " + id + " not found"));
    }

    @Override
    public List<Actor> getAll() {
        return actorDao.getAll();
    }

    @Override
    public Actor update(Actor actor) {
        return actorDao.update(actor);
    }

    @Override
    public void delete(Long id) {
        actorDao.delete(id);
    }
}
