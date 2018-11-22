package br.com.pvprojects.loja.integration;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.pvprojects.loja.integration.response.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;

@Consumes("application/json")
public interface MockApiService {

    @POST
    @Path("/buy/{number}")
    MockResponseData responseBuy(@PathParam("number") String number, MockRequestData requestData);
}
