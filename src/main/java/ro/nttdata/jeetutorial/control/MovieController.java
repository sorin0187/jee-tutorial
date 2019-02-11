package ro.nttdata.jeetutorial.control;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.nttdata.jeetutorial.entity.Movie;

public class MovieController {
    @PersistenceContext
    private EntityManager entityManager;

    public Long createMovie(final Movie movie) {
        entityManager.persist(movie);
        entityManager.flush();
        return movie.getId();
    }

    public List<Movie> getAllMovies() {
        final Query findAll = entityManager.createNamedQuery(Movie.FIND_ALL);
        return findAll.getResultList();
    }

    public Movie getMovie(final Long id) {
        return entityManager.find(Movie.class, id);
    }
}
