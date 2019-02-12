package ro.nttdata.jeetutorial.integration.omdbapi.boundary;

import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ro.nttdata.jeetutorial.integration.RestClient;
import ro.nttdata.jeetutorial.integration.omdbapi.entity.OmdbMovie;

public class OmdbapiService extends RestClient {
    private static final String URL = "http://www.omdbapi.com";

    private static final String APIKEY_PARAM = "apikey";
    private static final String TITLE_PARAM = "t";

    private static final String APIKEY = "40fc59f1";

    private Client client = createClient();

    @Override
    protected int getConnectionTimeoutValue() {
        return 5000;
    }

    @Override
    protected int getReadTimeoutValue() {
        return 10000;
    }

    public Optional<OmdbMovie> getMovieInfo(final String title) {
        final WebTarget webTarget = client.target(URL).queryParam(TITLE_PARAM, title).queryParam(APIKEY_PARAM, APIKEY);
        final Response response = webTarget.request().get();
        return Optional.ofNullable(response.readEntity(OmdbMovie.class));
    }
}
