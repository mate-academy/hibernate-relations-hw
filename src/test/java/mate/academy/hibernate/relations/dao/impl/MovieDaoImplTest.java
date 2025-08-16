package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.AbstractTest;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieDaoImplTest extends AbstractTest {
    private static final Movie shawshankRedemption = new Movie("The Shawshank Redemption");
    private static final Actor morganFreeman = new Actor("Morgan Freeman");
    private static final Country usa = new Country("USA");

    @Override
    protected Class<?>[] entities() {
        return new Class[]{Movie.class, Actor.class, Country.class};
    }

    @Test
    public void create_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        verifyCreateMovieWorks(movieDao, shawshankRedemption.clone(), 1L);
    }

    @Test
    public void getById_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        verifyCreateMovieWorks(movieDao, shawshankRedemption.clone(), 1L);
        Optional<Movie> actualOptional = movieDao.get(1L);
        Assertions.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
    }

    @Test
    public void saveWithActor_ok() {
        Actor morganFreemanClone = morganFreeman.clone();
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        ActorDaoImplTest.verifyCreateActorWorks(actorDao, morganFreemanClone, 1L);

        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Movie shawshankRedemptionWithActor = shawshankRedemption.clone();
        shawshankRedemptionWithActor.setActors(List.of(morganFreemanClone));
        verifyCreateMovieWorks(movieDao, shawshankRedemptionWithActor, 1L);

        Optional<Movie> actualOptional = getMovieById(1L);
        Assertions.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
        Assertions.assertNotNull(actual.getActors());
        Assertions.assertEquals(1, actual.getActors().size());
        Assertions.assertEquals(morganFreeman.getName(), actual.getActors().get(0).getName());
    }

    @Test
    public void saveWithActorAndCountry_ok() {
        Country usaClone = usa.clone();
        CountryDao countryDao = new CountryDaoImpl(getSessionFactory());
        CountryDaoImplTest.verifyCreateCountryWorks(countryDao, usaClone, 1L);

        Actor morganFreemanClone = morganFreeman.clone();
        morganFreemanClone.setCountry(usaClone);
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        ActorDaoImplTest.verifyCreateActorWorks(actorDao, morganFreemanClone, 1L);

        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Movie shawshankRedemptionWithActor = shawshankRedemption.clone();
        shawshankRedemptionWithActor.setActors(List.of(morganFreemanClone));
        verifyCreateMovieWorks(movieDao, shawshankRedemptionWithActor, 1L);

        Optional<Movie> actualOptional = getMovieById(1L);
        Assertions.assertTrue(actualOptional.isPresent());
        Movie actual = actualOptional.get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(shawshankRedemption.getTitle(), actual.getTitle());
        Assertions.assertNotNull(actual.getActors());
        Assertions.assertEquals(1, actual.getActors().size());
        Assertions.assertEquals(morganFreeman.getName(), actual.getActors().get(0).getName());
        Assertions.assertNotNull(actual.getActors().get(0).getCountry());
        Assertions.assertEquals(1, actual.getActors().get(0).getCountry().getId());
        Assertions.assertEquals(usa.getName(), actual.getActors().get(0).getCountry().getName());
    }

    @Test
    public void getByNotExistingId_Ok() {
        MovieDao movieDao = new MovieDaoImpl(getSessionFactory());
        Optional<Movie> actual = movieDao.get(100L);
        Assertions.assertFalse(actual.isPresent());
    }

    private void verifyCreateMovieWorks(MovieDao movieDao, Movie movie, Long expectedId) {
        Movie actual = movieDao.add(movie);
        Assertions.assertNotNull(actual, "Check you have implemented the `create` method "
                + "in the MovieDaoImpl class");
        Assertions.assertNotNull(actual.getId(), "ID for movie should be autogenerated");
        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(movie.getTitle(), actual.getTitle());
    }

    private Optional<Movie> getMovieById(Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM Movie m "
                            + "LEFT JOIN FETCH m.actors "
                            + "WHERE m.id = :id", Movie.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get a movie by id: " + id, e);
        }
    }
}
