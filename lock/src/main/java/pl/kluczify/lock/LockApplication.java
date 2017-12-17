package pl.kluczify.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EntityScan(basePackageClasses = {LockApplication.class})
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class LockApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockApplication.class, args);
	}


}
