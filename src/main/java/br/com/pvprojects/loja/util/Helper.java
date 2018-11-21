package br.com.pvprojects.loja.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Helper {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");

    public String uuidGenerator() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID + "-" + dateFormatter.format(new Date());
    }
}
