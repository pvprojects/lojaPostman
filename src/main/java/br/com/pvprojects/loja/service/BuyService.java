package br.com.pvprojects.loja.service;

import java.util.List;

import br.com.pvprojects.loja.domain.response.HistoryResponse;
import br.com.pvprojects.loja.integration.request.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;

public interface BuyService {

    MockResponseData buy(String login, String number, MockRequestData requestData);

    HistoryResponse saveHistory(String customerId, String login, MockRequestData requestData);

    List<HistoryResponse> getHistoryByLogin(String login);
}