package br.com.pvprojects.loja.util.dateHelper;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        String data = jp.readValueAs(String.class);

        if (data != null && !data.isEmpty())
            return LocalDateTime.parse(data);

        return null;
    }
}