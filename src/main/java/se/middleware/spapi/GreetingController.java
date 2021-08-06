package se.middleware.spapi;

import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.middleware.spapi.model.Greeting;

@RestController
public class GreetingController {
  
  private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    logger.info("Called greeting enpoint with {}", name);
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
  
}
