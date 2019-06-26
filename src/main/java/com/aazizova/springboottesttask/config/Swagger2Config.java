package com.aazizova.springboottesttask.config;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Swagger config.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    //TODO move constants to separate class
    /**
     * Github url.
     */
    private static final String GITHUB_URL = "https://github.com/annaazizova";
    /**
     * Author email.
     */
    private static final String EMAIL = "annaazizova9@gmail.com";
    /**
     * Author name.
     */
    private static final String AUTHOR_NAME = "Anna Azizova";
    /**
     * Package name.
     */
    private static final String PACKAGE_NAME =
            "com.aazizova.springboottesttask.controller";

    /**
     * Api.
     *
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage(PACKAGE_NAME))
                .build()
                .apiInfo(apiEndPointsInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(securitySchema()));
    }

    /**
     * Api end points info.
     *
     * @return ApiInfo
     */
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot Test Task API")
                .description("Products Management REST API")
                .contact(new Contact(AUTHOR_NAME, GITHUB_URL, EMAIL))
                .version("1.0.0")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = Collections.singletonList(new AuthorizationScope("READ", "descr"));
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8080/oauth/token");
        List<GrantType> grantTypes = Collections.singletonList(resourceOwnerPasswordCredentialsGrant);
        return new OAuth("securitySchemaOAuth2", authorizationScopeList, grantTypes);
    }
}
