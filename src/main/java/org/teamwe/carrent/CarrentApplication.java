package org.teamwe.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CarrentApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello, Car Rent System!";
    }

	public static void main(String[] args) {
		SpringApplication.run(CarrentApplication.class, args);
	}
}
