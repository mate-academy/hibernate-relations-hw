package mate.academy.hibernate.relations;

import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.impl.ActorServiceImpl;
import mate.academy.hibernate.relations.service.impl.CountryServiceImpl;
import mate.academy.hibernate.relations.service.impl.MovieServiceImpl;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        CountryService countryService = new CountryServiceImpl(
                new CountryDaoImpl(HibernateUtil.getSessionFactory()));
        ActorService actorService = new ActorServiceImpl(
                new ActorDaoImpl(HibernateUtil.getSessionFactory()));
        MovieService movieService = new MovieServiceImpl(
                new MovieDaoImpl(HibernateUtil.getSessionFactory()));

        Country usa = new Country("USA");
        countryService.add(usa);

        Actor actor = new Actor("Tom Hanks");
        actor.setCountry(usa);
        actorService.add(actor);

        Movie movie = new Movie("Forrest Gump");
        movie.getActors().add(actor);
        movieService.add(movie);

        System.out.println(movieService.get(movie.getId()));
    }
}
