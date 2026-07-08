# Step 1: Base image (Java 17)
# FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk
# Step 2: Working directory inside container
WORKDIR /app

# Step 3: Copy JAR file into container
COPY /HMS/HMS/target/HMS-0.0.1-SNAPSHOT.jar app.jar
# Step 4: Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
# Step 1: Build stage
# FROM eclipse-temurin:17-jdk AS build
# WORKDIR /app
# COPY . .
# RUN ./mvnw clean package -DskipTests

# # Step 2: Run stage
# FROM eclipse-temurin:17-jdk
# WORKDIR /app
# COPY --from=build /app/target/HMS-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","app.jar"]
