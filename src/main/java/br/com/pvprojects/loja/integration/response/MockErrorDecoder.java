package br.com.pvprojects.loja.integration.response;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MockErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 404) {
            //TODO fazer handler
            return  null;
        }

        if (response.status() == 500) {
            return  null;
        }

        return genericError();
    }

    private Exception genericError() {
        return null;
    }

}
