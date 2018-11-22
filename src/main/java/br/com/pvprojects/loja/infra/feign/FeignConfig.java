package br.com.pvprojects.loja.infra.feign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pvprojects.loja.integration.MockApiService;
import br.com.pvprojects.loja.integration.response.MockErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class FeignConfig {

    @Value("${mock-api-url}")
    private String mockApiURL;

    @Value("${connectTimeout-feign}")
    private int connectTimeout;

    @Value("${readTimeout-feign}")
    private int readTimeout;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockApiInterceptor mockApiInterceptor;

    @Bean
    public MockApiService mockApiServiceFeign() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(connectTimeout, readTimeout))
                .retryer(Retryer.NEVER_RETRY)
                .errorDecoder(new MockErrorDecoder())
                .requestInterceptor(mockApiInterceptor)
                .target(MockApiService.class, mockApiURL);
    }
}