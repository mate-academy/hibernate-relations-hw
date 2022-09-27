package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {
    private MovieDao dao = new MovieDaoImpl(HibernateUtil.getSessionFactory());
    
    @Override
    public Movie add(Movie movie) {
        return dao.add(movie);
    }
    
    @Override
    public Movie get(Long id) {
        return dao.get(id).orElseThrow(() -> new DataProcessingException(
                "No movie with ID: " + id));
    }
}
