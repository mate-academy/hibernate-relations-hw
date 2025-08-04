package mate.academy.hibernate.relations;

import java.util.Arrays;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Use interfaces for all variables
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);

        CountryService countryService = new CountryServiceImpl(countryDao);
        ActorService actorService = new ActorServiceImpl(actorDao);

        Country country = new Country("USA");
        country = countryService.add(country);

        Actor actor1 = new Actor("Brad Pitt");
        actor1.setCountry(country);
        actor1 = actorService.add(actor1);

        Actor actor2 = new Actor("Angelina Jolie");
        actor2.setCountry(country);
        actor2 = actorService.add(actor2);

        MovieDao movieDao = new MovieDaoImpl(sessionFactory);
        MovieService movieService = new MovieServiceImpl(movieDao);
        Movie movie = new Movie("Mr. & Mrs. Smith");
        movie.setActors(Arrays.asList(actor1, actor2));
        movie = movieService.add(movie);

        sessionFactory.close();
    }
}

