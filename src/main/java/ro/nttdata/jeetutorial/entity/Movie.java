package ro.nttdata.jeetutorial.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import ro.nttdata.jeetutorial.integration.omdbapi.entity.OmdbMovie;

@NamedQueries({
        @NamedQuery(name = Movie.FIND_ALL, query = "select m from Movie m"),
        @NamedQuery(name = Movie.FIND_BY_TITLE, query = "select m from Movie m where m.title like :" + Movie.PARAM_TITLE)
})
@Entity
@Table(name = "T_MOVIES")
public class Movie {

    public static final String FIND_ALL = "Movie.findAll";
    public static final String FIND_BY_TITLE = "Movie.findByTitle";
    public static final String PARAM_TITLE = "title";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOVIE_ID")
    private List<Actor> cast;

    @Transient
    private OmdbMovie exIf;

    public OmdbMovie getExIf() {
        return exIf;
    }

    public void setExIf(final OmdbMovie exIf) {
        this.exIf = exIf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<Actor> getCast() {
        if (cast == null) {
            cast = new ArrayList<>();
        }
        return cast ;
    }

    public void setCast(final List<Actor> cast) {
        this.cast = cast;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void updateFields(final Movie update) {
        this.cast = update.getCast();
        this.title = update.getTitle();
    }

    public Movie addExIf(final Optional<OmdbMovie> movieInfo) {
        movieInfo.ifPresent(this::setExIf);
        return this;
    }
}

