#Dockerfile para la creación de la imagen de la aplicación
#utilizando la imagen base de Java 21
#Se copia el archivo jar de la aplicación a la imagen
#y ejecuta el comando para correr la aplicación
# Usa una imagen base de OpenJDK adecuada
FROM openjdk:21
# Crea un directorio de trabajo
WORKDIR /app
# Copia solo el .jar generado
COPY build/libs/asignacioncamasMicroservicio-0.0.1-SNAPSHOT.jar app.jar
# Establece los argumentos por defecto para el perfil y el archivo de configuración
ARG PROFILE=dev
ARG CONFIG_PATH=env.properties
# Establece las variables de entorno para los perfiles
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
ENV SPRING_CONFIG_IMPORT=file:/app/${CONFIG_PATH}
# Define el punto de entrada para ejecutar el .jar con el perfil y configuración
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Dspring.config.import=${SPRING_CONFIG_IMPORT} -jar app.jar"]
# Fin del archivo Dockerfile