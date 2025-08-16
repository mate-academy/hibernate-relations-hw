package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryServiceImpl countryService =
                new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        Country country = new Country();
        country.setName("New Zealand");
        countryService.add(country);
        System.out.println(countryService.get(1L));

        ActorServiceImpl actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        Actor actor = new Actor();
        actor.setName("Bob");
        actor.setCountry(country);
        actorService.add(actor);
        System.out.println(actorService.get(1L));

        MovieServiceImpl movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        Movie movie = new Movie();
        movie.setTitle("Your name");
        movie.setActors(List.of(actor));
        movieService.add(movie);
        System.out.println(movieService.get(1L));
    }
}
