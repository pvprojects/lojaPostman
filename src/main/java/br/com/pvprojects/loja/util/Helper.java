package br.com.pvprojects.loja.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.pvprojects.loja.infra.handle.exceptions.BadRequestException;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;

public class Helper {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public String uuidGenerator() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID + "-" + dateFormatter.format(new Date());
    }

    public static String getHeaderFromRequest(String keyHeader) {
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (null == curRequest.getHeader(keyHeader))
            throw new BadRequestException("Header não encontrado");

        return curRequest.getHeader(keyHeader);
    }

    public static void checkIfStringIsBlank(String string, String error) {
        if (null == string || string.isEmpty())
            throw new DefaultException(error);
    }

    public static void checkIfObjectIsNull(Object object, String error) {
        if (object == null)
            throw new DefaultException(error);
    }

    public static void checkIfCollectionIsNullOrEmpty(Collection collection, String error) {
        if (CollectionUtils.isEmpty(collection))
            throw new DefaultException(error);
    }
}