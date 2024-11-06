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

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country country = new Country("new zealand");
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        countryService.add(country);

        Actor actor = new Actor("Ian  McKellen");
        actor.setCountry(country);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        actorService.add(actor);

        Movie movie = new Movie("Lord of the Rings");
        movie.setActors(List.of(actor));
        MovieService movieService = new MovieServiceImpl(sessionFactory);
        movieService.add(movie);
        System.out.println(movieService.get(movie.getId()));
    }
}
