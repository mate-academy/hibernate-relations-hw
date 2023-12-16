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
        // use this session factory when you will initialize service instances
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryService countryService = new CountryServiceImpl();
        // TODO: Initialize with the actual implementation
        ActorService actorService = new ActorServiceImpl();
        // TODO: Initialize with the actual implementation
        MovieService movieService = new MovieServiceImpl();
        // TODO: Initialize with the actual implementation

        Country usa = new Country("USA");
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        // Retrieve and print the movie details
        Movie retrievedMovie = movieService.get(fastAndFurious.getId());
        System.out.println("Retrieved Movie: " + retrievedMovie);
    }
}
