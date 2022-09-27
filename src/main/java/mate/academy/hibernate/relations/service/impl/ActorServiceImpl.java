package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class ActorServiceImpl implements ActorService {
    private ActorDao dao = new ActorDaoImpl(HibernateUtil.getSessionFactory());
    
    @Override
    public Actor add(Actor actor) {
        return dao.add(actor);
    }
    
    @Override
    public Actor get(Long id) {
        return dao.get(id).orElseThrow(() -> new DataProcessingException(
                "No actor with ID: " + id));
    }
}
