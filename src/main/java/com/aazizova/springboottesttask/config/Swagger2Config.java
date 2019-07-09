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

@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * Github url.
     */
    private static final String GITHUB_URL = "https://github.com/annaazizova"; //TODO move constants to separate class
    /**
     * Author email.
     */
    private static final String EMAIL = "annaazizova9@gmail.com";
    /**
     * Author name.
     */
    private static final String AUTHOR_NAME = "Anna Azizova";

    /**
     * Api.
     *
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.aazizova.springboottesttask.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot Test Task API")
                .description("Products Management REST API")
                .contact(new Contact(AUTHOR_NAME, GITHUB_URL, EMAIL))
                .version("1.0.0")
                .build();
    }
}
