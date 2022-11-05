package appsprototyping.apiclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// FROM : https://www.baeldung.com/spring-security-oauth-auth-server
public class ApiClientApp {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientApp.class, args);
	}

}
