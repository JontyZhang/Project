package com.jt.rms.common;

import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket addUserDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfo("Restful API", "API Document管理", "", "", new Contact("", "", ""), "", "");
        docket.apiInfo(apiInfo);
        return docket;
    }
}
