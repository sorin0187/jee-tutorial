package ro.nttdata.jeetutorial.control;

import java.util.List;
import java.util.Optional;

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

    public Optional<Movie> getMovie(final Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public Optional<Movie> deleteMovie(final Long id) {
        final Optional<Movie> movieToDelete = getMovie(id);
        movieToDelete.ifPresent(entityManager::remove);
        return movieToDelete;
    }

    public Optional<Movie> updateMovie(final Long id, final Movie updatedMovie) {
        final Optional<Movie> movie = getMovie(id);
        movie.ifPresent(m -> m.updateFields(updatedMovie));
        return movie;
    }

    public List<Movie> findByTitle(final String title) {
        final Query findByTitle = entityManager.createNamedQuery(Movie.FIND_BY_TITLE);
        return findByTitle.setParameter(Movie.PARAM_TITLE, title.replace("*", "%").replace("?", "_")).getResultList();
    }
}
