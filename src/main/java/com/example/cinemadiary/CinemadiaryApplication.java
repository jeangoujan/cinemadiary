package com.example.cinemadiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class CinemadiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemadiaryApplication.class, args);
	}
}
