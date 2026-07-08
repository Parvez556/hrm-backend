# Step 1: Base image (Java 17)
# FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk
# Step 2: Working directory inside container
WORKDIR /app

# Step 3: Copy JAR file into container
COPY target/HMS-0.0.1-SNAPSHOT.jar app.jar
# Step 4: Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
