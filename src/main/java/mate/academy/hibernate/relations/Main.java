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

        Country someCountry = new Country("some country");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        countryService.add(someCountry);

        Actor someActor = new Actor("some actor");
        someActor.setCountry(someCountry);
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        actorService.add(someActor);

        Movie someMovie = new Movie("some movie");
        someMovie.setActors(List.of(someActor));
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        movieService.add(someMovie);
    }
}
