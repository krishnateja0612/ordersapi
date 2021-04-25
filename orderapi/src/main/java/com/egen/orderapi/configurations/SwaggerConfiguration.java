package com.egen.orderapi.configurations;
import java.util.Collections;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//http://localhost:8081/v2/api-docs : for non UI swagger
//http://localhost:8081/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.egen.orderapi.controllers"))
                //.paths(PathSelectors.any())
                .paths(regex("/.*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Order API",
                "App to demonstrate Order API Documentation",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("","",""),
                "",
                "",
                Collections.emptyList());
    }
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");

//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
   // }
}
