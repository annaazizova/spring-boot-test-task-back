package com.aazizova.springboottesttask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
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
}
