package mate.academy.hibernate.relations;

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

        CountryService countryService = new CountryServiceImpl(sessionFactory);
        Country usa = new Country("USA");
        countryService.add(usa);
        Country australia = new Country("Australia");
        countryService.add(australia);
        Country britain = new Country("Great Britain");
        countryService.add(britain);
        countryService.getAll().forEach(System.out::println);
        System.out.println(countryService.get(britain.getId()));
        countryService.delete(britain);
        countryService.getAll().forEach(System.out::println);

        ActorService actorService = new ActorServiceImpl(sessionFactory);
        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);
        Actor robertDowney = new Actor("Robert Downey Jr.");
        robertDowney.setCountry(usa);
        actorService.add(robertDowney);
        Actor chrisHemsworth = new Actor("Chris Hemsworth");
        chrisHemsworth.setCountry(australia);
        actorService.add(chrisHemsworth);
        Actor liamHemsworth = new Actor("Liam Hemsworth");
        liamHemsworth.setCountry(australia);
        actorService.add(liamHemsworth);
        actorService.getAll().forEach(System.out::println);
        System.out.println(actorService.get(liamHemsworth.getId()));
        actorService.delete(liamHemsworth);
        actorService.getAll().forEach(System.out::println);

        MovieService movieService = new MovieServiceImpl(sessionFactory);
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setActors(List.of(vinDiesel));
        movieService.add(fastAndFurious);
        Movie avengers = new Movie("Avengers");
        avengers.setActors(List.of(robertDowney, chrisHemsworth));
        movieService.add(avengers);
        Movie ironMan = new Movie("IronMan");
        ironMan.setActors(List.of(robertDowney));
        movieService.add(ironMan);
        movieService.getAll().forEach(System.out::println);
        avengers.setTitle("Avengers Endgame");
        movieService.update(avengers);
        movieService.getAll().forEach(System.out::println);
        System.out.println(movieService.get(ironMan.getId()));
        movieService.delete(ironMan);
        movieService.getAll().forEach(System.out::println);
    }
}
