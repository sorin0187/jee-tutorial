package ro.nttdata.jeetutorial.integration.omdbapi.entity;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbMovie {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Poster")
    private URL poster;

    private Float imdbRating;

    private Long imdbVotes;

    @JsonProperty("imdbID")
    private String imdbId;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public URL getPoster() {
        return poster;
    }

    public void setPoster(final URL poster) {
        this.poster = poster;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(final Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Long getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(final Long imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }
}
