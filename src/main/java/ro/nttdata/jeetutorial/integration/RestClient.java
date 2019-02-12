package ro.nttdata.jeetutorial.integration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

public abstract class RestClient {

    protected Client createClient() {
        // initialize Jersey client
        final ClientBuilder builder = ClientBuilder.newBuilder();

        final Client client = builder.build();
        client.register(JacksonFeature.class);
        client.register(JacksonConfigurator.class);
        client.property(ClientProperties.CONNECT_TIMEOUT, getConnectionTimeoutValue());
        client.property(ClientProperties.READ_TIMEOUT, getReadTimeoutValue());
        client.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        return client;
    }

    protected abstract int getConnectionTimeoutValue();
    protected abstract int getReadTimeoutValue();
}
