package br.com.pvprojects.loja.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDateTime> {

//    @Override
//    public LocalDate deserialize(JsonParser jp,
//            DeserializationContext ctxt) throws IOException {
//        String data = jp.readValueAs(String.class);
//        if (data != null && !data.isEmpty()) {
//            return LocalDate.parse(data);
//        }
//        return null;
//    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        String data = jp.readValueAs(String.class);

        if (data != null && !data.isEmpty()) {
            return LocalDateTime.parse(data);
        }

        return null;
    }
}
