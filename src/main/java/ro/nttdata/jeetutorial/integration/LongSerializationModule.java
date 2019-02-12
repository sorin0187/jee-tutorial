package ro.nttdata.jeetutorial.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LongSerializationModule extends SimpleModule {

    public LongSerializationModule() {
        addDeserializer(Long.class, new JsonDeserializer<Long>() {
            @Override
            public Long deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
                    throws IOException {
                return Long.valueOf(jsonParser.getText().replace(",", "").replace(".", ""));
            }
        });
    }
}
