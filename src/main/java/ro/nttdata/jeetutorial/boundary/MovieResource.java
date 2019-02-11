package ro.nttdata.jeetutorial.boundary;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ro.nttdata.jeetutorial.control.MovieController;
import ro.nttdata.jeetutorial.entity.Movie;

@Stateless
@Path("movies")
@Produces("application/json")
@Consumes("application/json")
public class MovieResource {

    @Inject
    private MovieController movieController;

    @POST
    public Response createMovie(final Movie movie) throws URISyntaxException {
        final Long id = movieController.createMovie(movie);
        return Response.created(new URI("movies/" + id)).build();
    }

    @GET
    public Response getAllMovies() {
        return Response.ok(movieController.getAllMovies()).build();
    }

    @GET
    @Path("{id}")
    public Response getMovieById(@PathParam("id") final Long id) {
        return Response.ok(movieController.getMovie(id)).build();
    }
}
