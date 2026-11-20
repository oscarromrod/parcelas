# ---------- Etapa 1: compilación ----------
FROM docker.io/library/eclipse-temurin:25-jdk AS build
WORKDIR /app
# Primero solo lo necesario para descargar dependencias.
# Así esta capa se cachea y no se repite si solo cambia el código.
COPY gradlew build.gradle settings.gradle ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew --no-daemon -q dependencies
# Ahora el código fuente
COPY src src
RUN ./gradlew --no-daemon -q bootJar -x test

# ---------- Etapa 2: ejecución ----------
FROM docker.io/library/eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]