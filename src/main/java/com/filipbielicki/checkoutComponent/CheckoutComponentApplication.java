package com.filipbielicki.checkoutComponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.filipbielicki.checkoutComponent"})
public class CheckoutComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutComponentApplication.class, args);
	}
}
