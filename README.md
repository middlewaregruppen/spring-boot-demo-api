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
export DOCKERHUB_USER=yourdockeruser
```

```bash
docker build -t $DOCKERHUB_USER/spring-boot-demo-api .
```

Push the image to your repo

```bash
docker push -t $DOCKERHUB_USER/spring-boot-demo-api .
```

Note: You will need to update this in deployment files also to reference the correct user

- [Deployment/spring-boot-api/base/deployment.yaml](./Deployment/spring-boot-api/base/deployment.yaml)
- [Deployment/spring-boot-api/overlays/dev/image-template.yaml](./Deployment/spring-boot-api/overlays/dev/image-template.yaml)
- [Deployment/spring-boot-api/overlays/dev/image.yaml](./Deployment/spring-boot-api/overlays/dev/image.yaml)
- [Deployment/spring-boot-api/overlays/test/image-template.yaml](Deployment/spring-boot-api/overlays/test/image-template.yaml)
- [Deployment/spring-boot-api/overlays/test/image.yaml](Deployment/spring-boot-api/overlays/test/image.yaml)

## Using the demo

This app exposes a simple `/greeting` endpoint under 

```bash
http://app.domain.com/greeting
```

and 

```bash
http://app.domain.com/greeting?name=Charlie
```

Calls to the endpoint increment a counter that can be monitored using prometheus. 

## Metrics

Added a `@Timer` metric on the `greeting` method and also a custom `Counter` metric to 
track the number of times the greeting is called.

Metrics can be looked up via

http://localhost:8080/actuator/prometheus

