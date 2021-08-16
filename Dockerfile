FROM openjdk:11
ARG JAR_FILE=target/*.jar
RUN mvn --batch-mode --update-snapshots clean package
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]