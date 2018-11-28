package br.com.pvprojects.loja.integration.decoder;

import static br.com.pvprojects.loja.util.ConventionsHelper.INTEGRATION_ERROR;

import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class MockErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 404) {
            return new DefaultException(INTEGRATION_ERROR);
        }

        if (response.status() == 500) {
            return new DefaultException(INTEGRATION_ERROR);
        }

        return new DefaultException(INTEGRATION_ERROR);
    }
}
