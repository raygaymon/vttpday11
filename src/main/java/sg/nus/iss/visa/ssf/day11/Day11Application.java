package sg.nus.iss.visa.ssf.day11;

import java.lang.System.Logger;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Day11Application {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Day11Application.class);
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {

		logger.info("main method initialised");
		
		SpringApplication app = new SpringApplication(Day11Application.class);

		//read args and check for port parameter

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		//returns a list of all the key values with a viable port number from cmd line
		List opsValues = appArgs.getOptionValues("port");

		String portNum;

		//if port number is not in args when program is executed
		if (opsValues == null || opsValues.get(0) == null ) {

			//logic here reads port number from the environment variable in application.properties
			portNum = System.getenv("PORT");

			if (portNum == null) {

				portNum = DEFAULT_PORT;
			}
		} else {

			//passing poer number from CLI
			portNum = (String) opsValues.get(0);
		}

		//setting the port number in spring-boot
		if (portNum != null) {
			app.setDefaultProperties(Collections.singletonMap("server.port", portNum));
		}

		System.out.println("This is the main method");
		
		logger.info("Port number is " + portNum);
		//launch spring app
		app.run(args);

	}

	@Bean
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
	}

}
