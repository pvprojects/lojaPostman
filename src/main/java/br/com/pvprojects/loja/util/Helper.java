package br.com.pvprojects.loja.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.pvprojects.loja.infra.handle.exceptions.HeaderNotFoundException;

public class Helper {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");

    public String uuidGenerator() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID + "-" + dateFormatter.format(new Date());
    }

    public static String getHeaderFromRequest(String keyHeader) {

        HttpServletRequest curRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        if (null == curRequest.getHeader(keyHeader)) {
            throw new HeaderNotFoundException("Header não encontrado");
        }

        return curRequest.getHeader(keyHeader);
    }
}
