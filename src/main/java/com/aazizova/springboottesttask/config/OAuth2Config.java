package com.aazizova.springboottesttask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private String clientId = "user";
    private String clientSecret = "my-secret-key";
    private String privateKey = "MIIEowIBAAKCAQEAxk7+hRQ8+k/zt/1iVhIi4M+hG5ksLN86g7g7KZO5+H6l7ojP\n" +
            "vTXruu1Fioitd98U8qajgankp+Ve7CJaPWjWK0Dp6/BqvZTHzioRbAp6hRTYr83g\n" +
            "X+zmumKGbQWxZFM9ANpCh10Da8kTz0CCq3UuNn+8C/8xjuqc8OkALLBGa7E9p29Y\n" +
            "A3fDIrlLTfZPoaFwRSdZ/LkQXv7uc3ibCfyDAadk1Z+tCDS/fFQc1DOGrE+qbsVG\n" +
            "jYgQ17HeqYEllzm0SXO+sZ509qvL8nOrkK3SlyQk0hz+0FZiC/Wp7zXhdLQ9RKgs\n" +
            "0zff091bEIt96ytlSYbThRpmkK9fottRAnUt8wIDAQABAoIBAQC8armxP7VgLy7l\n" +
            "lb4lAT5aU2xDiJlMWRyDoju0GUQNMmf958dZgy3igSYdphcLWc3RbzrTK9SxIWG7\n" +
            "AjizODSlDBw1rEv+rKna3GWJPbuDTndDMl0JR1FBrfMDCvGs3NmNQdMMQffKJVbJ\n" +
            "CAYfWkr6RQsqauFvZMKQsxETZLjEAApQHNA1kLonFDTp1cjp88jGJz5FVCwPMCkF\n" +
            "kPnxrtKk3prWIYWsUF61ctB7Md9WKg6GkKVUJJgcYaNohSncsI3SROVLzCcLu00v\n" +
            "UdnDfipbgwdv1fIQsrbzYagyWEkoxcy4fDSqBSK1hErJCyECcmeMxsS2yJ+LBYSh\n" +
            "8enggV6BAoGBAO14yd8u3c0VHExujbhqnpeDhCGatxWxKiL6fRGZI04zV1L5D9yG\n" +
            "NJwlvP/06pz8fbnbMvtXZqPyw+I6qr2qGJ0c2uO4/Vg5XqmeunTi3q2TPd53yHGL\n" +
            "iXl8vBCBLRxPc20SKCjKNMJTNbbHx1Bqre+g84n/HqOkJdhED4W7zi1jAoGBANXH\n" +
            "95StuLK2JhkT49n3ZhkIODofqIucoPnCHzVMCpKs7JhL+bir4K7Erv/fD7ThsTHg\n" +
            "jdqkUianSVKc0MWfXf4d5obvJqrPBPgvHuv2rWy1bzvEVFBUOQDOqt/hYLhhO4c8\n" +
            "1xZpX2sUUapyFbhj6Tc8ic8IyCW199wpxEu8FuoxAoGAXrmV3P723r2TAjH/Ohbd\n" +
            "mBaP+E+cxHxdEILWrNRnLuRv6tsKpq6qAbdUAs2RYHmfNSno1jVw7ekClhVM0kct\n" +
            "dk5ySfAarryDtClquwFcTeS0Ay/ItvIwLlPBONwuQGK46kMZ3dt63CERBg3+xWco\n" +
            "ZrPPMbagZCowJmpPvsrBXX0CgYA9hWpuUwAQDfMwzFSJ6XRsR71GnM24P77joUWx\n" +
            "5Mw2IaWEvniC+JOUN+3UnLC8cSa5H9Iy1Y7+qK2lJJPAC3jVJG2u7yvFhAX+FjFj\n" +
            "3n1SB3SXUoyhAVvWK6NpAkb/SJwE8zFCtdhLzHEkB+jQQ5Bd4QIXnoNatt85cJuY\n" +
            "3+wIQQKBgBoOX+6Vcw+K+WmB6xCMKEP4ACsAQu9dONc6IBmr1uJntfho7J6kmTzi\n" +
            "HQxKg9lAZ3ePec7x1mgT6lUfoeRLYw+Hz8kIhg4g7bz5xH7YemKmIWRhYqScVR1D\n" +
            "flPaGXJo7T09l9MJ7aEr8Ybqx2nC8CDsmHmsQL3yafS6TeSzyen/\n";
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxk7+hRQ8+k/zt/1iVhIi\n" +
            "4M+hG5ksLN86g7g7KZO5+H6l7ojPvTXruu1Fioitd98U8qajgankp+Ve7CJaPWjW\n" +
            "K0Dp6/BqvZTHzioRbAp6hRTYr83gX+zmumKGbQWxZFM9ANpCh10Da8kTz0CCq3Uu\n" +
            "Nn+8C/8xjuqc8OkALLBGa7E9p29YA3fDIrlLTfZPoaFwRSdZ/LkQXv7uc3ibCfyD\n" +
            "Aadk1Z+tCDS/fFQc1DOGrE+qbsVGjYgQ17HeqYEllzm0SXO+sZ509qvL8nOrkK3S\n" +
            "lyQk0hz+0FZiC/Wp7zXhdLQ9RKgs0zff091bEIt96ytlSYbThRpmkK9fottRAnUt\n" +
            "8wIDAQAB\n";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(clientSecret).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }
}
