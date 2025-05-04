package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActorServiceImpl implements ActorService {
    private static final Logger logger = LogManager.getLogger(ActorServiceImpl.class);
    private final ActorDao actorDao;

    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public Actor add(Actor actor) {
        try {
            return actorDao.add(actor);
        } catch (Exception e) {
            logger.error("Can't add actor: " + actor, e);
            throw new DataProcessingException("Can't add actor: " + actor, e);
        }
    }

    @Override
    public Actor get(Long id) {
        try {
            Optional<Actor> actorOptional = actorDao.get(id);
            if (actorOptional.isEmpty()) {
                throw new DataProcessingException("Actor not found with id: " + id);
            }
            return actorOptional.get();
        } catch (Exception e) {
            logger.error("Can't get actor by id: " + id, e);
            throw new DataProcessingException("Can't get actor by id: " + id, e);
        }
    }
}
