package br.com.alura.leilao;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.AssertFalse;

@SpringBootApplication
public class LeilaoApplication {

	// -Dspring.profiles.active=test
	public static void main(String[] args) {
		SpringApplication.run(LeilaoApplication.class, args);
	}

}
