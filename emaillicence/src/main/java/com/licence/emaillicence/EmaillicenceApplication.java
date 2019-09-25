package com.licence.emaillicence;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmaillicenceApplication {

	static Logger log= Logger.getLogger(EmaillicenceApplication.class.getName());	
	public static void main(String[] args) {
		System.out.println("start");
		log.debug("email Debug message");
		log.info("email Info message");
		
		SpringApplication.run(EmaillicenceApplication.class, args);
		System.out.println("end");
	}

}
