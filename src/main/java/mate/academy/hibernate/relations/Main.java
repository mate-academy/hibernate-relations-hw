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

        Actor colinFarrell = new Actor("CColin Farrell");
        colinFarrell.setCountry(usa);
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        actorService.add(colinFarrell);

        Actor matthewMcConaughey = new Actor("Matthew McConaughey");
        matthewMcConaughey.setCountry(usa);
        actorService.add(matthewMcConaughey);

        Movie gentlemen = new Movie("The Gentlemen");
        gentlemen.setActors(List.of(colinFarrell));
        gentlemen.setActors(List.of(colinFarrell, matthewMcConaughey));
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        movieService.add(gentlemen);
        System.out.println(movieService.get(gentlemen.getId()));
    }
}
