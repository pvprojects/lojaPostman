package br.com.pvprojects.loja.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${SECURITY_OAUTH2_CLIENT_CLIENT_ID}")
    private String clientId;

    @Value("${SECURITY_OAUTH2_CLIENT_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${security.oauth2.client.scope}")
    private String grantType;

    @Value("${config.security.oauth2.authorization}")
    private String authorizationCode;

    @Value("${config.security.oauth2.refresh.token}")
    private String refreshToken;

    @Value("${config.security.oauth2.implict}")
    private String implict;

    @Value("${config.security.oauth2.scope.read}")
    private String scopeRead;

    @Value("${config.security.oauth2.scope.write}")
    private String scopeWrite;

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grantType, authorizationCode, refreshToken, implict)
                .scopes(scopeRead, scopeWrite)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }

}