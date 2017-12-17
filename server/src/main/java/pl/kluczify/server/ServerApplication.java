package pl.kluczify.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EntityScan(basePackageClasses = {ServerApplication.class})


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ServerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
	    SpringApplication.run(ServerApplication.class, args);
	}
}
