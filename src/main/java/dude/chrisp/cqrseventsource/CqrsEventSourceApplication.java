package dude.chrisp.cqrseventsource;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CqrsEventSourceApplication extends CarRabbitConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CqrsEventSourceApplication.class, args);
	}

}
