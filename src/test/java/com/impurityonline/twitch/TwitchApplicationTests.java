package com.impurityonline.twitch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TwitchApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("The application entry works")
	public void application_entry() {
		TwitchApplication.main(new String[]{"Apples", "Bananas"});
		assertNotNull(applicationContext);
	}

	@Test
	@DisplayName("The application context loads")
	public void context_loads() {
		assertNotNull(applicationContext);
	}
}
