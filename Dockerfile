# Basisimage met JDK 21
FROM eclipse-temurin:21-jdk

# Werkdirectory in de container
WORKDIR /app

# Gradle wrapper en build files kopiëren
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Maak gradlew uitvoerbaar
RUN chmod +x ./gradlew

# Dependencies downloaden (Docker cache wordt gebruikt voor sneller rebuilds)
RUN ./gradlew build -x test --dry-run

# Rest van de code kopiëren
COPY src ./src

# Poort exposen waarop Spring Boot draait
EXPOSE 8080

# Start Spring Boot met hot reload
CMD ["./gradlew", "bootRun"]