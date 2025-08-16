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
        Country france = new Country("France");
        CountryService countryService = new CountryServiceImpl(sessionFactory);
        countryService.add(france);
        Actor samyNaceri = new Actor("Samy Naceri");
        samyNaceri.setCountry(france);
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        actorService.add(samyNaceri);
        Movie taxi = new Movie("Taxi");
        List<Actor> list = new ArrayList<>();
        list.add(samyNaceri);
        taxi.setActors(list);
        MovieService movieService = new MovieServiceImpl(sessionFactory);
        movieService.add(taxi);
    }
}
