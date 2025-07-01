# Usa imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR compilado
COPY target/structure-microservice-1.0.0.jar app.jar

# Expone el puerto de Spring Boot
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
