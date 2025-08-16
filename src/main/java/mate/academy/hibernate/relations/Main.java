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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        countryService.add(usa);

        Actor johnnyDepp = new Actor("Johnny Depp");
        johnnyDepp.setCountry(usa);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        actorService.add(johnnyDepp);

        Movie piratesOfTheCaribbean = new Movie("Pirates of the Caribbean");
        piratesOfTheCaribbean.setActors(List.of(johnnyDepp));
        MovieService movieService = new MovieServiceImpl(sessionFactory);
        movieService.add(piratesOfTheCaribbean);
        System.out.println(movieService.get(piratesOfTheCaribbean.getId()));
    }
}
