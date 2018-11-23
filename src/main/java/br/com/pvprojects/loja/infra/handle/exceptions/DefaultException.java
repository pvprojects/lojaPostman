package br.com.pvprojects.loja.infra.handle.exceptions;

public class DefaultException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DefaultException(String msg) {
        super(msg);
    }

    public DefaultException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
