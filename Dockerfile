# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the local machine to the container
COPY target/transactionms.jar /app/transactionms.jar

# Run the application
ENTRYPOINT ["java", "-jar", "transactionms.jar"]

# Expose the port the app runs on
EXPOSE 8080
