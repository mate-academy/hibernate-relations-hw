package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import mate.academy.hibernate.relations.util.Injector;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Injector injector = new Injector(sessionFactory);

        CountryService countryService = injector.getCountryService();
        Country usa = new Country("USA");
        countryService.add(usa);

        ActorService actorService = injector.getActorService();
        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        MovieService movieService = injector.getMovieService();
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);

        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
