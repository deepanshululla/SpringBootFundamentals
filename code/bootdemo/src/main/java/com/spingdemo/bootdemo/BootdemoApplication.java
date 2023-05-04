package com.spingdemo.bootdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootdemoApplication.class, args);
	}

}


@Component
class StartUpRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception  {
		System.out.println("Starting up...");
	}
}
