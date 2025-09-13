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
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryDao countryDao = new CountryDaoImpl(sessionFactory);

        Country usa = new Country("USA");
        CountryService countryService = new CountryServiceImpl(countryDao);
        countryService.add(usa);

        ActorDao actorDao = new ActorDaoImpl(sessionFactory);

        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        ActorService actorService = new ActorServiceImpl(actorDao);
        actorService.add(vinDiesel);

        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = new MovieServiceImpl(movieDao);
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
