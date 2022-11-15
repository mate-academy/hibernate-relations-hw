package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.GenericDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.service.exception.EntityNotFoundException;

public class MovieServiceImpl extends GenericServiceImpl<Movie> implements MovieService {
    public MovieServiceImpl(GenericDao<Movie> actorDao) {
        super(actorDao);
    }

    @Override
    public Movie get(Long id) {
        return dao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Movie not found with id "
                + id));
    }
}
