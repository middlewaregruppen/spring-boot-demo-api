package se.middleware.spapi;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import se.middleware.spapi.model.Greeting;

@RestController
public class GreetingController {
  
  private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
  private MeterRegistry meterRegistry;
  private Counter greetingCounter;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  public GreetingController(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;

    registerMetricsCounters();
  }

  private void registerMetricsCounters() {
    greetingCounter = Counter.builder("greeting.count")
        .tag("direction", "sent")
        .description("The number of greetings sent out")
        .register(this.meterRegistry);
  }

  @GetMapping("/greeting")
  @Timed(value = "greeting.time", description = "Time taken to return greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    logger.info("Called greeting enpoint with {}", name);
    greetingCounter.increment(1.0);
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
  
}
