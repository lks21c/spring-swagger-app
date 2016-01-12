package com.creamsugardonut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;

@SpringBootApplication
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SwaggerApplication.class);
		application.setRegisterShutdownHook(true);
		
		// Pid File Path 설정
		String pidFilePath = System.getProperty("pidFilePath");
		ApplicationPidFileWriter listener = null;
		if (pidFilePath != null && pidFilePath.length() > 0) {
			listener = new ApplicationPidFileWriter(pidFilePath);
		} else {
			listener = new ApplicationPidFileWriter("/tmp/swagger.pid");
		}

		listener.setTriggerEventType(ApplicationStartedEvent.class);
		application.addListeners(listener);
		application.run(args);
	}
}
