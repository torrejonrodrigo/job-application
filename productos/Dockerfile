# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim AS build


# Install Maven
RUN apt-get update && apt-get install -y maven && apt-get clean


# Set the working directory
WORKDIR /app


# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src


# Package the application
RUN mvn clean package -DskipTests


# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim


# Set the working directory
WORKDIR /app


# Copy the packaged jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080


# Add wait-for-it script
COPY wait-for-it.sh /usr/local/bin/wait-for-it
RUN chmod +x /usr/local/bin/wait-for-it

# Command to run the application
ENTRYPOINT ["wait-for-it", "postgres:5432", "--timeout=30", "--strict", "--", "java", "-jar", "app.jar"]