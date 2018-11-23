package br.com.pvprojects.loja.infra.handle;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String message;

    public StandardError(String error, String message) {
        super();
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
