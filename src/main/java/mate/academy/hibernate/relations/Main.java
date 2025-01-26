package mate.academy.hibernate.relations;

import java.util.List;
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

        // DAO and Services initialization
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));

        // Create and save Country
        Country usa = new Country();
        usa.setName("USA");
        countryService.add(usa);

        // Create and save Actor
        Actor vinDiesel = new Actor();
        vinDiesel.setName("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        // Create and save Movie
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        // Retrieve and display Movie with Actors
        Movie retrievedMovie = movieService.get(fastAndFurious.getId());
        System.out.println(retrievedMovie);
    }
}
