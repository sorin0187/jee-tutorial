package ro.nttdata.jeetutorial.boundary;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ro.nttdata.jeetutorial.entity.Movie;

@Stateless
@Path("movies")
@Produces("application/json")
@Consumes("application/json")
public class MovieResource {

    private static List<Movie> moviesList = new ArrayList<>();

    @POST
    public Response createMovie(final Movie movie) throws URISyntaxException {
        moviesList.add(movie);
        return Response.created(new URI("movies/" + (moviesList.size() - 1))).build();
    }

    @GET
    public Response getAllMovies() {
        return Response.ok(moviesList).build();
    }

    @GET
    @Path("{id}")
    public Response getMovieById(@PathParam("id") final Integer id) {
        return Response.ok(moviesList.get(id)).build();
    }
}
