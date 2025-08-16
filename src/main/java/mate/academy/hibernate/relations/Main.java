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
        Country canada = new Country("Canada");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        countryService.add(usa);
        countryService.add(canada);
        System.out.println(countryService.get(1L));
        System.out.println(countryService.get(2L));

        Actor ryanGosling = new Actor("Ryan Gosling");
        Actor vinDiesel = new Actor("Vin Diesel");
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        ryanGosling.setCountry(canada);
        vinDiesel.setCountry(usa);
        actorService.add(ryanGosling);
        actorService.add(vinDiesel);
        System.out.println(actorService.get(1L));
        System.out.println(actorService.get(2L));

        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie drive = new Movie("Drive");
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        drive.setActors(List.of(ryanGosling));
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);
        movieService.add(drive);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
