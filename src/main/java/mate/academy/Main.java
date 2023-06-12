package mate.academy;

import java.util.List;
import mate.academy.relations.model.Actor;
import mate.academy.relations.model.Country;
import mate.academy.relations.model.Movie;
import mate.academy.relations.service.ActorService;
import mate.academy.relations.service.CountryService;
import mate.academy.relations.service.MovieService;
import mate.academy.relations.service.impl.ActorServiceImpl;
import mate.academy.relations.service.impl.CountryServiceImpl;
import mate.academy.relations.service.impl.MovieServiceImpl;
import mate.academy.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // use this session factory when you will initialize service instances
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = new MovieServiceImpl(sessionFactory);
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
