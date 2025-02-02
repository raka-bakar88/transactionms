package com.challenge.transactionms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TransactionmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionmsApplication.class, args);
	}
}
