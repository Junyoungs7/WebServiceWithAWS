package Jy.WebServiceWithAWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebServiceWithAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceWithAwsApplication.class, args);
	}

}
