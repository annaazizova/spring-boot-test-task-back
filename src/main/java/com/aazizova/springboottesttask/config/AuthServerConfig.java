package com.aazizova.springboottesttask.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * AuthServerConfig.
 */
@Configuration
@EnableAuthorizationServer
@Log4j2
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * Grant type password.
     */
    static final String GRANT_TYPE_PASSWORD = "password";
    /**
     * Authorization code.
     */
    static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * Refresh token.
     */
    static final String REFRESH_TOKEN = "refresh_token";
    /**
     * Implicit.
     */
    static final String IMPLICIT = "implicit";
    /**
     * Scope read.
     */
    static final String SCOPE_READ = "READ";
    /**
     * Scope write.
     */
    static final String SCOPE_WRITE = "WRITE";
    /**
     * Access token validity seconds.
     */
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    /**
     * Refresh token validity seconds.
     */
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    /**
     * Client id.
     */
    @Value("${security.oauth2.client.clientId}")
    private String CLIENT_ID;

    /**
     * Client secret.
     */
    @Value("${security.oauth2.client.clientSecret}")
    private String CLIENT_SECRET;

    /**
     * Password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authentication manager.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Logging exception translator.
     *
     * @return WebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator loggingExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(final Exception e)
                    throws Exception {
                // This is the line that prints the stack trace to the log.
                // You can customise this to format the trace etc if you like
                e.printStackTrace();

                // Carry on handling the exception
                ResponseEntity<OAuth2Exception> responseEntity =
                        super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                return new ResponseEntity<>(excBody,
                        headers,
                        responseEntity.getStatusCode());
            }
        };
    }

    /**
     * Access token converter.
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
    }

    /**
     * Token store.
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Configure.
     *
     * @param configurer ClientDetailsServiceConfigurer
     *
     * @throws Exception
     */
    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer)
            throws Exception { //TODO define exception
        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD,
                        AUTHORIZATION_CODE,
                        REFRESH_TOKEN,
                        IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    /**
     * Configure.
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     *
     * @throws Exception
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception { //TODO define exception
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .exceptionTranslator(loggingExceptionTranslator());
    }
}
