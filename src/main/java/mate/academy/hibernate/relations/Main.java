package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.lib.Injector;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    private static final Injector injector =
            Injector.getInstance("mate.academy.hibernate.relations");
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Country usa = new Country("USA");
        CountryService countryService =
                (CountryService) injector.getInstance(CountryService.class, sessionFactory);
        countryService.add(usa);
        System.out.println(countryService.get(usa.getId()));

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        ActorService actorService =
                (ActorService) injector.getInstance(ActorService.class, sessionFactory);
        actorService.add(vinDiesel);
        System.out.println(actorService.get(vinDiesel.getId()));

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class, sessionFactory);
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
