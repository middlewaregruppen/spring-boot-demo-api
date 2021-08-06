# Demo Spring Boot API

Simple containerized spring boot API with test coverage that can be used for 
test purposes in CI/CD pipelines.

## Requirements

- Java JDK 11
- Maven 3.6.0
- VSCode or other IDE

## Building the jar

From the project root, after making sure you have Java 11 in the path

```bash
mvn clean package
```

## Creating the Docker image

From the project root

```bash
docker build -t middlewaregruppen/spring-boot-demo-api .
```

## Metrics

Added a `@Timer` metric on the `greeting` method and also a custom `Counter` metric to 
track the number of times the greeting is called.

Metrics can be looked up via

http://localhost:8080/actuator/prometheus

