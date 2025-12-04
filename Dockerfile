# ======================
# Stage 1: Build Backend
# ======================
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Kopieer alle bestanden en build de jar (zonder tests)
COPY . .
RUN gradle build -x test --no-daemon

# ======================
# Stage 2: Build Test Container
# ======================
FROM gradle:8.5-jdk21 AS test-build
WORKDIR /app
COPY . .

# Install dependencies voor headless Chrome
RUN apt-get update && apt-get install -y \
    wget unzip xvfb libxi6 libgconf-2-4 libnss3 libxss1 libappindicator1 libatk-bridge2.0-0 \
    libgtk-3-0 libx11-xcb1 fonts-liberation libasound2 curl

# Install Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
 && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' \
 && apt-get update && apt-get install -y google-chrome-stable

# Install ChromeDriver
RUN LATEST=$(curl -sSL https://chromedriver.storage.googleapis.com/LATEST_RELEASE) \
 && wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$LATEST/chromedriver_linux64.zip \
 && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
 && rm /tmp/chromedriver.zip

# Run tests (unit + integration + E2E)
CMD ["./gradlew", "clean", "test", "--no-daemon"]

# ======================
# Stage 3: Runtime Backend
# ======================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Kopieer alleen de jar uit build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Start backend
ENTRYPOINT ["java", "-jar", "app.jar"]
