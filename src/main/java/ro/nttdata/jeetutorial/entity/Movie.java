package ro.nttdata.jeetutorial.entity;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
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
}
