package com.impurityonline.twitch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitchApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@org.junit.jupiter.api.Test
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
