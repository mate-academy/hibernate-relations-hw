package mate.academy.hibernate.relations.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id)
                .orElseThrow(() -> new RuntimeException("Can't get movie from DB by id" + id));
    }
}
