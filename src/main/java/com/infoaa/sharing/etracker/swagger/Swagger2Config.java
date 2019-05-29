package com.infoaa.sharing.etracker.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Niraj Tripathi", "http://www.infoaaa.com", "tripan12@gmail.com");


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.infoaa.sharing.etracker.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfo(
                "eSharingApp - Spring Boot REST API",
                "Project eSharingApp Management REST APIs.",
                "1.0",
                "Terms of service",
                DEFAULT_CONTACT,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html",
                Collections.emptyList());
    }
}

