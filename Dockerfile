# =========================
# 1) Build stage
# =========================
# Use a lightweight OpenJDK image with Maven preinstalled
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

# Set working directory
WORKDIR /app

# Copy entire backend source code and Maven wrapper
COPY . .

# Make sure Maven wrapper is executable
RUN chmod +x mvnw

# Build the Spring Boot application (skip tests if needed)
RUN ./mvnw clean package -DskipTests


# =========================
# 2) Runtime stage
# =========================
# Use a very lightweight OpenJDK runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy generated JAR file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the generated JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
