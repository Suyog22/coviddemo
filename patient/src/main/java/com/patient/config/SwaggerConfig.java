package com.patient.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String BASIC_AUTH = "basicAuth";
    private static final String BEARER_AUTH = "Bearer";

	@Bean
	public Docket getCustomizedDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.patient"))
				.build()
				.apiInfo(getApiInfo())
				.securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Rest Api Documentation of Patient Service", 
				"This Api is defined for Patient Service ",
				"1.0.0.",
				"http://www.zensar.com",
				new Contact("Suyog Majgaonkar","http://gpl.com","suyog.majgaonkar@zensar.com"),
				"GPL",
				"http://gppplaise.com",
				new ArrayList<>()
				);
	}
	
	private List<SecurityScheme> securitySchemes() {
        return List.of(new ApiKey(BEARER_AUTH, "Authorization", "header"));
    }
 
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(bearerAuthReference())).forPaths(PathSelectors.ant("/patient/**")).build();
    }
 
    private SecurityReference bearerAuthReference() {
        return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
    }
}
