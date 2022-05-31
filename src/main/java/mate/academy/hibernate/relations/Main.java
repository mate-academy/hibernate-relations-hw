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

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        countryService.add(usa);

        Country ua = new Country("Ukraine");
        countryService.add(ua);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        Actor robertDowneyJr = new Actor("Robert Downey Jr.");
        robertDowneyJr.setCountry(ua);
        Actor johnnyDepp = new Actor("Johnny Depp");
        johnnyDepp.setCountry(ua);
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        actorService.add(vinDiesel);
        actorService.add(robertDowneyJr);
        actorService.add(johnnyDepp);

        Movie ironMan = new Movie("Iron Man");
        Movie piratesOfTheCaribbean = new Movie("Pirates of the Caribbean");
        ironMan.setActors(List.of(robertDowneyJr));
        piratesOfTheCaribbean.setActors(List.of(johnnyDepp, vinDiesel));

        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        movieService.add(ironMan);
        movieService.add(piratesOfTheCaribbean);
        System.out.println(movieService.get(ironMan.getId()));
        System.out.println(movieService.get(piratesOfTheCaribbean.getId()));
    }
}
