package br.com.pvprojects.loja.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private static final String PATTERN = "dd-MM-yyyy";

    @Override
    public void serialize(LocalDate value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString(value.format(DateTimeFormatter.ofPattern(PATTERN)));
        } else {
            gen.writeNull();
        }
    }
}
