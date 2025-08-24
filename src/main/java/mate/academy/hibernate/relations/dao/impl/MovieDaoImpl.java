package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        Movie mergedMovie = null; // Змінна для результату merge
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            mergedMovie = (Movie) session.merge(movie); // Зберігаємо результат merge
            transaction.commit();
            return mergedMovie; // Повертаємо об'єкт з оновленим id
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert or update movie " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



    @Override
    public Optional<Movie> get(Long id) {
        // ДОДАЙТЕ ЦЕЙ РЯДОК ДЛЯ ПЕРЕВІРКИ
        System.out.println("!!! EXECUTING NEW GET METHOD !!!");

        String hql = "FROM Movie m "
                + "LEFT JOIN FETCH m.actors a "
                + "LEFT JOIN FETCH a.country "
                + "WHERE m.id = :id";
        try (Session session = factory.openSession()) {
            return session.createQuery(hql, Movie.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie by id: " + id, e);
        }
    }
}
