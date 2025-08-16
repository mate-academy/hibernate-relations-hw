package mate.academy.hibernate.relations;

import java.util.List;
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

public class Application {
    public static void runTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country country = new Country("Earth");
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        countryService.add(country);

        Actor alice = new Actor("Alice");
        alice.setCountry(country);
        Actor bob = new Actor("Bob");
        bob.setCountry(country);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        actorService.add(alice);
        actorService.add(bob);

        Movie movie = new Movie("All true about: 'Why Bob and Alice?'");
        movie.setActors(List.of(alice, bob));
        MovieService movieService = new MovieServiceImpl(sessionFactory);
        movieService.add(movie);

        System.out.println(movieService.get(movie.getId()));
    }
}
