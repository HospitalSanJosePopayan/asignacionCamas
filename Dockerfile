#Dockerfile para la creación de la imagen de la aplicación
#utilizando la imagen base de Java 21
#Se copia el archivo jar de la aplicación a la imagen
#y ejecuta el comando para correr la aplicación
# Usa una imagen base de OpenJDK adecuada
FROM openjdk:21

# Crea un directorio temporal
VOLUME /tmp

# Copia el .jar generado
COPY build/libs/asignacioncamasMicroservicio-0.0.1-SNAPSHOT.jar app.jar

# Define un argumento para el perfil, con un valor predeterminado
ARG PROFILE=prod

# Establece el perfil como variable de entorno
ENV SPRING_PROFILES_ACTIVE=$PROFILE

# Define el punto de entrada con el perfil activo
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]

# Fin del archivo Dockerfile