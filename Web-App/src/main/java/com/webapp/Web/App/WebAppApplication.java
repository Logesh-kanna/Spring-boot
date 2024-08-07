package com.webapp.Web.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.webapp.Web.App.WebApp.Home;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext bean = SpringApplication.run(WebAppApplication.class, args);
		bean.getBean(Home.class);
	}

}
