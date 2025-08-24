package mate.academy.hibernate.relations;

import java.util.ArrayList;
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
        ActorService actorService = new ActorServiceImpl(sessionFactory);
        MovieService movieService = new MovieServiceImpl(sessionFactory);

        // --- СТВОРЕННЯ ПОЧАТКОВИХ ДАНИХ ---
        System.out.println("--- Етап 1: Створення початкових даних ---");
        Country usa = new Country("USA");
        countryService.add(usa);
        Actor vinDiesel = new Actor("Vin Diesel");
        vinDiesel.setCountry(usa);
        actorService.add(vinDiesel);

        Movie fastAndFurious = new Movie("Fast and Furious");
        // Важливо: List.of() створює незмінний список, тому обгортаємо його в ArrayList
        fastAndFurious.setActors(new ArrayList<>(List.of(vinDiesel)));
        movieService.add(fastAndFurious);

        Movie retrievedMovie = movieService.get(fastAndFurious.getId());
        System.out.println("Створено фільм: " + retrievedMovie);
        System.out.println("Актори у фільмі: " + retrievedMovie.getActors());
        System.out.println("-------------------------------------------\n");

        // --- ОНОВЛЕННЯ ФІЛЬМУ: ДОДАВАННЯ НОВОГО АКТОРА ---
        System.out.println("--- Етап 2: Додавання нового актора до існуючого фільму ---");
        Country uk = new Country("UK");
        countryService.add(uk);
        Actor jasonStatham = new Actor("Jason Statham");
        jasonStatham.setCountry(uk);
        actorService.add(jasonStatham);

        // Отримуємо фільм з бази, щоб оновити його
        Movie movieToUpdate = movieService.get(fastAndFurious.getId());
        movieToUpdate.getActors().add(jasonStatham); // Додаємо нового актора
        movieService.add(movieToUpdate); // Зберігаємо оновлений фільм

        System.out.println("Додано нового актора: " + jasonStatham);
        System.out.println("-------------------------------------------\n");


        // --- ФІНАЛЬНА ПЕРЕВІРКА ---
        System.out.println("--- Етап 3: Фінальна перевірка ---");
        Movie finalMovie = movieService.get(fastAndFurious.getId());
        System.out.println("Остаточний стан фільму: " + finalMovie);
        System.out.println("Усі актори у фільмі: " + finalMovie.getActors());
        System.out.println("Очікувана кількість акторів: 2. Фактична: " + finalMovie.getActors().size());

        sessionFactory.close();
    }
}
