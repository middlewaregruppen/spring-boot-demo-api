package se.middleware.spapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testing that the greeting controller is there 
 */
@SpringBootTest
class BasicTests {

	@Autowired
	private GreetingController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	

}
