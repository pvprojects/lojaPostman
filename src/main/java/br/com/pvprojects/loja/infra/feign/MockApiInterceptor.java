package br.com.pvprojects.loja.infra.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import br.com.pvprojects.loja.util.Helper;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class MockApiInterceptor implements RequestInterceptor {

    @Value("${mock-api-header}")
    private String APPLICATION_ID_HEADER;

    @Value("${mock-api-value}")
    private String APPLICATION_ID_VALUE;

    @Override
    public void apply(RequestTemplate template) {
        template.header(APPLICATION_ID_HEADER, this.getApplicationIdValue());
    }

    private String getApplicationIdValue() {
        Helper h = new Helper();
        return h.getHeaderFromRequest("app_id");
    }
}
