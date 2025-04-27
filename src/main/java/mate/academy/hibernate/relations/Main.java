package mate.academy.hibernate.relations;

import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;
import java.util.List;

public class Main {
    public static void main(String... args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryService countryService = new CountryServiceImpl(
                new CountryDaoImpl(sessionFactory)
        );
        Country usa = countryService.add(new Country("USA"));

        ActorService actorService = new ActorServiceImpl(
                new ActorDaoImpl(sessionFactory)
        );
        Actor vin = new Actor("Vin Diesel");
        vin.setCountry(usa);
        vin = actorService.add(vin);

        MovieService movieService = new MovieServiceImpl(
                new MovieDaoImpl(sessionFactory)
        );
        Movie ff = new Movie("Fast and Furious");
        ff.setActors(List.of(vin));
        ff = movieService.add(ff);

        System.out.println(movieService.get(ff.getId()));
    }
}
