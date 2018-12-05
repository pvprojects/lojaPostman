package br.com.pvprojects.loja.util;

import static br.com.pvprojects.loja.util.ConventionsHelper.DATA_PATTERN;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_HEADER_NOT_FOUND;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_INVALID_NUMBER_EXCEPTION;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.pvprojects.loja.infra.handle.exceptions.BadRequestException;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;

public class Helper {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat(DATA_PATTERN);

    public String uuidGenerator() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID + "-" + dateFormatter.format(new Date());
    }

    public static String createPasswordByBCrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static String getHeaderFromRequest(String keyHeader) {
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (null == curRequest.getHeader(keyHeader))
            throw new BadRequestException(ERRO_HEADER_NOT_FOUND);

        return curRequest.getHeader(keyHeader);
    }

    public static void checkIfStringIsOnlyNumbers(String number, String error) {

        checkIfStringIsBlank(number, ERRO_INVALID_NUMBER_EXCEPTION);

        if (!number.matches("[0-9]+")) {
            throw new DefaultException(error);
        }
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