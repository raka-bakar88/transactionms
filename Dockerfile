FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/transactionms-0.0.1-SNAPSHOT.jar /app/transactionms.jar
ENTRYPOINT ["java", "-jar", "transactionms.jar"]

EXPOSE 8080
