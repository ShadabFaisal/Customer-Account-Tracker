package com.cts.customer_account_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer Account Tracker", version = "1.0.0", description = "In today's business landscape, managing customer accounts effectively is crucial for businesses to maintain strong relationships, ensure timely payments, and provide exceptional customer service. However, many organizations still struggle with manual and inefficient methods of tracking customer accounts, resulting in a myriad of challenges. These challenges include:\n"
		+ "\n"
		+ "1. Lack of Centralized Information: Without a dedicated customer account tracking system, businesses often find it challenging to consolidate and maintain accurate and up-to-date customer information. This leads to scattered data across various platforms, making it difficult to retrieve and analyze relevant data in a timely manner.\n"
		+ "\n"
		+ "2. Inefficient Account Management: Businesses frequently face difficulties in efficiently managing customer accounts. Manually updating account details, tracking payment histories, and monitoring outstanding balances can be time-consuming and prone to errors. This inefficiency can lead to delayed payments, missed opportunities, and dissatisfied customers.", contact = @Contact(name = "Shadab Faisal", email = "shadabfaisal91@gmail.com")))

public class CustomerAccountTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountTrackerApplication.class, args);
	}

}
