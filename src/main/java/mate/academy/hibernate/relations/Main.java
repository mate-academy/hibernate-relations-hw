package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
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

    public static final String NAME_OF_COUNTRY = "USA";
    public static final String NAME_OF_ACTOR = "Vin Diesel";
    public static final String NAME_OF_MOVIE = "Fast and Furious";

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);
        initData(countryDao, actorDao, movieDao);
    }

    private static void initData(CountryDao countryDao, ActorDao actorDao, MovieDao movieDao) {
        Country usa = new Country(NAME_OF_COUNTRY);
        CountryService countryService = new CountryServiceImpl(countryDao);
        countryService.add(usa);
        countryService.get(5L);

        Actor vinDiesel = new Actor(NAME_OF_ACTOR);
        vinDiesel.setCountry(usa);
        ActorService actorService = new ActorServiceImpl(actorDao);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie(NAME_OF_MOVIE);
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = new MovieServiceImpl(movieDao);
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
