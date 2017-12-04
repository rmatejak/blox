package pl.gov.coi.blox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BloxApplication {

	public static void main(String[] args) { SpringApplication.run(BloxApplication.class, args);
	}
}
