package com.xp.phr.desafio_final.coupon.infra.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("coupons")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(new Info().title("Coupon API").version("1.0"));
                })
                .pathsToMatch("/coupons/**")
                .build();
    }
}