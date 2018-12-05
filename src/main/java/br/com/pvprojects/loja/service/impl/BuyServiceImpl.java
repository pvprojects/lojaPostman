package br.com.pvprojects.loja.service.impl;

import static br.com.pvprojects.loja.util.ConventionsHelper.COMPRA_REALIZADA;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_INVALID_NUMBER_EXCEPTION;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_SALVAR_HISTORICO;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_USUARIO_SEM_HISTORICO;
import static br.com.pvprojects.loja.util.ConventionsHelper.INVALID_LOGIN;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.History;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.domain.response.HistoryResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.integration.MockApiService;
import br.com.pvprojects.loja.integration.request.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;
import br.com.pvprojects.loja.repository.HistoryRepository;
import br.com.pvprojects.loja.service.BuyService;
import br.com.pvprojects.loja.util.Helper;

@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private MockApiService mockApiService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private HistoryRepository historyRepository;

    private static final Logger log = LoggerFactory.getLogger(BuyServiceImpl.class);

    @Override
    @Transactional
    public HistoryResponse saveHistory(String customerId, String login, MockRequestData requestData) {
        History history = new History();

        try {
            history.setCustomerId(customerId);
            history.setLogin(login);
            history.setItem(requestData.getItem());
            history.setPrice(requestData.getPrice());

            historyRepository.saveAndFlush(history);
        } catch (Exception e) {
            log.error(ERRO_SALVAR_HISTORICO);
            throw new DefaultException(ERRO_SALVAR_HISTORICO);
        }

        return this.historyToHistoryResponse(history);
    }

    @Override
    public List<HistoryResponse> getHistoryByLogin(String login) {
        Helper.checkIfStringIsBlank(login, INVALID_LOGIN);

        List<History> list = historyRepository.findByLoginIgnoreCase(login);
        Helper.checkIfCollectionIsNullOrEmpty(list, ERRO_USUARIO_SEM_HISTORICO);
        List<HistoryResponse> responseList = new ArrayList<>();

        list.forEach(item -> {

            HistoryResponse historyResponse = this.historyToHistoryResponse(item);

            responseList.add(historyResponse);
        });

        return responseList;
    }

    @Override
    public MockResponseData buy(String login, String number, MockRequestData requeestData) {
        Helper.checkIfStringIsOnlyNumbers(number, ERRO_INVALID_NUMBER_EXCEPTION);

        MockResponseData responseData = mockApiService.responseBuy(number, requeestData);

        if (null != responseData) {
            log.info(COMPRA_REALIZADA);

            CustomerResponse customerResponse = customerService.findByIdOrLogin(login);
            this.saveHistory(customerResponse.getId(), customerResponse.getLogin(), requeestData);
        }

        return responseData;
    }

    private HistoryResponse historyToHistoryResponse(History history) {
        HistoryResponse historyResponse = new HistoryResponse();

        historyResponse.setCustomerId(history.getCustomerId());
        historyResponse.setItem(history.getItem());
        historyResponse.setPrice(history.getPrice());
        historyResponse.setCreated(history.getCreated());
        return historyResponse;
    }
}