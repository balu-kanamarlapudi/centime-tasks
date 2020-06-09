package com.centime.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DatabaseApp {
	
	@Autowired
	private Environment env;
	
	public static void main(String args[]) {
		SpringApplication.run(DatabaseApp.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "DELETE"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", config);

		return new CorsFilter(source);
	}

	@Bean
	public Docket api() {
		final Set<String> protocols = new HashSet<String>();
		protocols.add("http");
		protocols.add("https");
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.centime.database.controller")).paths(PathSelectors.any())
				.build().pathMapping("").protocols(protocols).host(env.getProperty("swagger.host.url", ""));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Centime API").description("Centime API reference for developers")
				.termsOfServiceUrl("http://centime.com").contact("database@centime.com")
				.license("Centime Service License").licenseUrl("database@centime.com").version("1.0").build();
	}

}
