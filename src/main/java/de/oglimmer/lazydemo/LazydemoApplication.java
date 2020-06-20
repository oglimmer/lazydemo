package de.oglimmer.lazydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LazydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazydemoApplication.class, args);
	}

}
