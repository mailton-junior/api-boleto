package br.com.mailton.validator_ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ValidatorTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidatorTicketApplication.class, args);
	}

}
