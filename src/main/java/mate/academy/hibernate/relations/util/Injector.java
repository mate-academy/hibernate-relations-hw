package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import org.hibernate.SessionFactory;

public class Injector {
    private final ActorService actorService;
    private final CountryService countryService;
    private final MovieService movieService;

    public Injector(SessionFactory sessionFactory) {
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        this.actorService = new ActorServiceImpl(actorDao);
        this.countryService = new CountryServiceImpl(countryDao);
        this.movieService = new MovieServiceImpl(movieDao);
    }

    public ActorService getActorService() {
        return actorService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public MovieService getMovieService() {
        return movieService;
    }
}
