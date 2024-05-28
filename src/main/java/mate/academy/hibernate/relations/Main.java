package mate.academy.hibernate.relations;

import java.util.List;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        var sessionFactory = HibernateUtil.getSessionFactory();

        var countryService = new CountryServiceImpl(new CountryDaoImpl(sessionFactory));
        var actorService = new ActorServiceImpl(new ActorDaoImpl(sessionFactory));
        var movieService = new MovieServiceImpl(new MovieDaoImpl(sessionFactory));

        var usa = new Country.Builder()
                .setName("USA")
                .build();
        countryService.add(usa);

        var vinDiesel = new Actor.Builder()
                .setName("Vin Diesel")
                .setCountry(usa)
                .build();
        actorService.add(vinDiesel);

        var fastAndFurious = new Movie.Builder()
                .setTitle("Fast and Furious")
                .setActors(List.of(vinDiesel))
                .build();

        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
    }
}
