package ro.nttdata.jeetutorial.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.javafx.beans.IDProperty;

@Table(name = "T_MOVIES")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOVIE_ID")
    private List<Actor> cast;

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
}
