# ======================
# Stage 1: Build
# ======================
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY . .
RUN gradle clean build -x test --no-daemon

# ======================
# Stage 2: Runtime
# ======================
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]
