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
        // use this session factory when you will initialize service instances
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        countryService.add(usa);

        Country ua = new Country("Ukraine");
        countryService.add(ua);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        Actor ryanGosling = new Actor("Ryan Gosling");
        ryanGosling.setCountry(ua);
        Actor paxom = new Actor("Paxom");
        paxom.setCountry(ua);
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        actorService.add(vinDiesel);
        actorService.add(ryanGosling);
        actorService.add(paxom);

        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie bazaKormit = new Movie("Eto baza");
        fastAndFurious.setActors(List.of(vinDiesel));
        bazaKormit.setActors(List.of(paxom, ryanGosling));

        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        movieService.add(fastAndFurious);
        movieService.add(bazaKormit);
        System.out.println(movieService.get(fastAndFurious.getId()));
        System.out.println(movieService.get(bazaKormit.getId()));
    }
}
