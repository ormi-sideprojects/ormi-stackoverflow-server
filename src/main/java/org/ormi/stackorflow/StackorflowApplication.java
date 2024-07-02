package org.ormi.stackorflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("org.ormi.stackorflow")
public class StackorflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackorflowApplication.class, args);
	}

}
