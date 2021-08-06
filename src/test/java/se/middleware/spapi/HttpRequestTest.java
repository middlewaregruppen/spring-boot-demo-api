package se.middleware.spapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import se.middleware.spapi.model.Greeting;

/**
 * Testing that the greeting controller, running on a random port will repsond correctly 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
  
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    assertThat(this.restTemplate
      .getForObject("http://localhost:" + port + "/greeting", Greeting.class))
      .hasFieldOrPropertyWithValue("content", "Hello, World!");
  }

  @Test
  public void greetingShouldReturnSpecificMessage() throws Exception {
    assertThat(this.restTemplate
      .getForObject("http://localhost:" + port + "/greeting?name=Charlie", Greeting.class))
      .hasFieldOrPropertyWithValue("content", "Hello, Charlie!");
  }
}
