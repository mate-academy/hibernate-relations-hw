package mate.academy.hibernate.relations;

import java.util.List;
import java.util.Optional;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        /*// use this session factory when you will initialize service instances
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Country usa = new Country("USA");
        CountryService countryService = null; // TODO: initialize this instance
        countryService.add(usa);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        ActorService actorService = null; // TODO: initialize this instance
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = null; // TODO: initialize this instance
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));*/
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        Country country = new Country();
        country.setName("USA");
        countryDao.add(country);

        Country country2 = new Country();
        country2.setName("Brasilia");
        countryDao.add(country2);

        Actor actor = new Actor();
        actor.setName("John");
        actor.setCountry(country);
        actorDao.add(actor);

        Actor actor1 = new Actor();
        actor1.setName("Lisa");
        actor1.setCountry(country);
        actorDao.add(actor1);

        Actor actor2 = new Actor();
        actor2.setName("Mary");
        actor2.setCountry(country2);
        actorDao.add(actor2);

        Movie movie = new Movie();
        movie.setTitle("Forsage");
        movie.setActors(List.of(actor, actor1, actor2));
        movieDao.add(movie);
    }
}
