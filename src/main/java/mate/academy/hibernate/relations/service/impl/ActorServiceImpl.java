package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.ActorService;
import org.hibernate.SessionFactory;

public class ActorServiceImpl implements ActorService {
    private final ActorDao actorDao;
    private final CountryDao countryDao;

    public ActorServiceImpl(SessionFactory sessionFactory) {
        this.actorDao = new ActorDaoImpl(sessionFactory);
        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Country country = actor.getCountry();
        if (country != null && country.getId() == null) {
            countryDao.add(country);
        }
        return actorDao.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return actorDao.get(id).orElseThrow(() ->
                new DataProcessingException("There is no such an actor with id: " + id));
    }
}
