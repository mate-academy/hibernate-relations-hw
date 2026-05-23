package mate.academy.hibernate.relations;

import java.util.ArrayList;
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

        CountryService countryService = new CountryServiceImpl(sessionFactory);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        MovieService movieService = new MovieServiceImpl(sessionFactory);

        System.out.println("--- Stage 1: Initial Data Creation ---");

        // 1. Create and save country
        Country usa = new Country("USA");
        usa = countryService.add(usa); // make sure USA is persisted

        // 2. Create actor, set persisted country, then save actor
        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        vinDiesel = actorService.add(vinDiesel);

        // 3. Create movie and link existing actor
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(new ArrayList<>(List.of(vinDiesel)));

        Movie savedMovie = movieService.add(fastAndFurious);

        Movie retrievedMovie = movieService.get(savedMovie.getId());
        System.out.println("Created movie: " + retrievedMovie);
        System.out.println("Actors in movie: " + retrievedMovie.getActors());
        System.out.println("-------------------------------------------\n");

        System.out.println("--- Stage 2: Adding a new Actor to the existing movie ---");

        // 4. Create and save another country
        Country uk = new Country("UK");
        uk = countryService.add(uk); // ensure UK is persisted

        // 5. Create actor, set persisted country, then save actor
        Actor jasonStatham = new Actor("Jason Statham");
        jasonStatham.setCountry(uk);
        jasonStatham = actorService.add(jasonStatham);

        // 6. Reload movie, add actor, and update movie
        Movie movieToUpdate = movieService.get(savedMovie.getId());
        movieToUpdate.getActors().add(jasonStatham);

        // If you have movieService.update(movieToUpdate) use it here instead of add
        movieService.add(movieToUpdate);

        System.out.println("Added a new actor: " + jasonStatham);
        System.out.println("-------------------------------------------\n");

        System.out.println("--- Stage 3: Final consistency check ---");
        Movie finalMovie = movieService.get(savedMovie.getId());
        System.out.println("Final movie state: " + finalMovie);
        System.out.println("Actors in final list: " + finalMovie.getActors());
        System.out.println("Expected actors count: 2. Actual: "
                + finalMovie.getActors().size());

        sessionFactory.close();
    }
}
