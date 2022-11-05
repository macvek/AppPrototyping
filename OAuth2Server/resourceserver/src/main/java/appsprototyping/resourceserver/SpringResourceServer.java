package appsprototyping.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// FROM : https://www.baeldung.com/spring-security-oauth-auth-server
// local DNS requires adding 127.0.0.1 auth-server
public class SpringResourceServer {

	public static void main(String[] args) {
		SpringApplication.run(SpringResourceServer.class, args);
	}

}
