package br.com.pvprojects.loja.service.exceptions;

public class HeaderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HeaderNotFoundException(String msg) {
        super(msg);
    }

    public HeaderNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
