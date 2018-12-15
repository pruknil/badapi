package com.guanbad.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
//@EnableDiscoveryClient
@SpringBootApplication
@PropertySource("file:config/application.properties")
@ComponentScan({ "com.guanbad.*" })
public class App {

	public static void main(String[] args) {
		// new SpringApplicationBuilder(App.class).build().run(args);
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);
		// Setting the application context for further references
		AppContext.getInstance().setContext(applicationContext);

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
