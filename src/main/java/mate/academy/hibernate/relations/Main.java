package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.ActorDao;
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
        Country ukraine = new Country("Ukraine");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        countryService.add(usa);
        countryService.add(ukraine);

        Actor vinDiesel = new Actor("Vin Diesel");
        Actor eugeneSinitsa = new Actor("Eugene Sinitsa");
        vinDiesel.setCountry(usa);
        eugeneSinitsa.setCountry(ukraine);
        ActorService actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        actorService.add(vinDiesel);
        actorService.add(eugeneSinitsa);

        Movie fastAndFurious = new Movie("Fast and Furious");
        Movie pryhodyProgramista = new Movie("Pryhody programista");
        pryhodyProgramista.setActors(List.of(eugeneSinitsa));
        fastAndFurious.setActors(List.of(vinDiesel));
        MovieService movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));
        movieService.add(fastAndFurious);
        movieService.add(pryhodyProgramista);
        System.out.println(movieService.get(fastAndFurious.getId()));
        System.out.println(movieService.get(eugeneSinitsa.getId()));

        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        actorDao.add(eugeneSinitsa);
    }

}
