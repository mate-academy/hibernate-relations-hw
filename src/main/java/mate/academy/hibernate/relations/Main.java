package mate.academy.hibernate.relations;

import java.util.ArrayList;
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

public class Main {
    public static void main(String[] args) {
        final ActorService actorService = new ActorServiceImpl(
                new ActorDaoImpl(HibernateUtil.getSessionFactory())
        );
        final CountryService countryService = new CountryServiceImpl(
                new CountryDaoImpl(HibernateUtil.getSessionFactory())
        );
        final MovieService movieService = new MovieServiceImpl(
                new MovieDaoImpl(HibernateUtil.getSessionFactory())
        );

        Actor actor = new Actor();
        actor.setName("Leonardo DiCaprio");
        actorService.add(actor);

        Country country = new Country();
        country.setName("USA");
        countryService.add(country);

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setActors(new ArrayList<>());
        movieService.add(movie);

        Actor retrievedActor = actorService.get(actor.getId());
        System.out.println("Retrieved Actor: " + retrievedActor.getName());

        Country retrievedCountry = countryService.get(country.getId());
        System.out.println("Retrieved Country: " + retrievedCountry.getName());

        Movie retrievedMovie = movieService.get(movie.getId());
        System.out.println("Retrieved Movie: " + retrievedMovie.getTitle());
    }
}
