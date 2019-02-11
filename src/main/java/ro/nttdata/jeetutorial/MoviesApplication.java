package ro.nttdata.jeetutorial;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import ro.nttdata.jeetutorial.boundary.MovieResource;

@ApplicationPath("rs")
public class MoviesApplication  extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MovieResource.class);
        classes.add(JacksonFeature.class);
        return classes;
    }
}
