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

        ActorService actorService = new ActorServiceImpl(sessionFactory);
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        MovieService movieService = new MovieServiceImpl(sessionFactory);

        Country usa = new Country("USA");
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        Movie retrievedMovie = movieService.get(fastAndFurious.getId());
        if (retrievedMovie != null) {
            System.out.println("Retrieved Movie: " + retrievedMovie);
            for (Actor actor : retrievedMovie.getActors()) {
                System.out.println("Actor in movie: " + actor.getName() + ", Country: " + actor.getCountry().getName());
            }
        } else {
            System.out.println("Movie with ID " + fastAndFurious.getId() + " not found!");
        }
        HibernateUtil.shutdown();
    }
}

