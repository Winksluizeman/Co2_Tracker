# Gebruik een Java runtime
FROM openjdk:17-jdk-slim

# Zet de werkdirectory
WORKDIR /app

# Kopieer het gebuildde JAR-bestand
COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

# Stel het commando in om je Spring Boot app te starten
ENTRYPOINT ["java","-jar","app.jar"]