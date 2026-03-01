package br.com.batch.senac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BatchSenacApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchSenacApplication.class, args);
	}

}
