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

        Country usa = new Country("USA");
        Country austria = new Country("AUS");
        CountryService countryService = new CountryServiceImpl(); // TODO: initialize this instance
        countryService.add(usa);
        countryService.add(austria);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        Actor arny = new Actor("Arny");
        arny.setCountry(austria);
        Actor arny1 = new Actor("Arny");
        arny1.setCountry(usa);
        Actor sly = new Actor("Sly");
        sly.setCountry(usa);

        ActorService actorService = new ActorServiceImpl(); // TODO: initialize this instance
        actorService.add(vinDiesel);
        actorService.add(arny);
        actorService.add(sly);
        actorService.add(arny1);

        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie terminator = new Movie("Terminator");
        fastAndFurious.setActors(List.of(vinDiesel, sly, arny1));
        terminator.setActors(List.of(arny, sly));
        MovieService movieService = new MovieServiceImpl(); // TODO: initialize this instance
        movieService.add(fastAndFurious);
        movieService.add(terminator);
        System.out.println(movieService.get(fastAndFurious.getId()));
        System.out.println(movieService.get(terminator.getId()));
        
    }
}
