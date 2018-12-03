package br.com.pvprojects.loja.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvprojects.loja.domain.Historic;
import br.com.pvprojects.loja.domain.response.CustomerResponse;
import br.com.pvprojects.loja.domain.response.HistoricResponse;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;
import br.com.pvprojects.loja.integration.MockApiService;
import br.com.pvprojects.loja.integration.request.MockRequestData;
import br.com.pvprojects.loja.integration.response.MockResponseData;
import br.com.pvprojects.loja.repository.HistoricRepository;
import br.com.pvprojects.loja.service.BuyService;
import br.com.pvprojects.loja.util.Helper;

@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private MockApiService mockApiService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private HistoricRepository historicRepository;

    private static final Logger log = LoggerFactory.getLogger(BuyServiceImpl.class);

    @Override
    @Transactional
    public HistoricResponse saveHistoric(String customerId, String login, MockRequestData requestData) {
        Historic historic = new Historic();

        try {
            historic.setCustomerId(customerId);
            historic.setLogin(login);
            historic.setItem(requestData.getItem());
            historic.setPrice(requestData.getPrice());
            historicRepository.saveAndFlush(historic);

        } catch (Exception e) {
            log.error("Erro ao criar o historico");
            throw new DefaultException("Erro ao criar historico de compra.");
        }

        HistoricResponse historicResponse = this.historicToHistoricResponse(historic);

        return historicResponse;
    }

    @Override
    public MockResponseData buy(String login, String number, MockRequestData requeestData) {
        Helper.checkIfStringIsOnlyNumbers(number, "Dado não valido.");

        MockResponseData responseData = mockApiService.responseBuy(number, requeestData);

        if (null != responseData) {
            log.info("Item comprado");
            CustomerResponse customerResponse = customerService.findByIdOrLogin(login);
            this.saveHistoric(customerResponse.getId(), customerResponse.getLogin(), requeestData);
        }

        return responseData;
    }

    private HistoricResponse historicToHistoricResponse(Historic historic) {
        HistoricResponse historicResponse = new HistoricResponse();

        historicResponse.setCustomerId(historic.getCustomerId());
        historicResponse.setItem(historic.getItem());
        historicResponse.setPrice(historic.getPrice());
        historicResponse.setCreated(historic.getCreated());
        return historicResponse;
    }
}