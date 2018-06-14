package org.teamwe.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CarRentApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello, Car Rent System!";
    }
    @GetMapping("/${hello.myname}")
    public String ttt() {
        return "Hell";
    }


    public static void main(String[] args) {
		SpringApplication.run(CarRentApplication.class, args);
	}
}
