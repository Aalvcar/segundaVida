# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven al contenedor
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Render usa la variable PORT para exponer el puerto de la app
ENV PORT=8080
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "app.jar"]
