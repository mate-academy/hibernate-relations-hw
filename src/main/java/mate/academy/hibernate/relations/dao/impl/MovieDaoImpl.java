package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
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

        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(movie);
            transaction.commit();
            System.out.println(transaction.getStatus());
        } catch (RuntimeException e) {
            transaction.rollback();
            session.close();
            throw new DataProcessingException();
        }
        session.close();
        return movie;

    }

    @Override
    public Optional<Movie> get(Long id) {

        Session session = this.factory.openSession();
        Optional<Movie> answer = Optional.ofNullable(session.get(Movie.class,id));
        session.close();
        return answer;
    }
}
