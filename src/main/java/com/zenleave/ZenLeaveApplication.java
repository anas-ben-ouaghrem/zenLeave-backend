package com.zenleave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.zenleave"})
@EnableScheduling
public class ZenLeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZenLeaveApplication.class, args);
	}

}
