#Dockerfile para la creación de la imagen de la aplicación
#utilizando la imagen base de Java 21
#Se copia el archivo jar de la aplicación a la imagen
#y ejecuta el comando para correr la aplicación
# Usa una imagen base de OpenJDK adecuada
FROM openjdk:21

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el .jar generado al directorio de trabajo
COPY build/libs/asignacioncamasMicroservicio-0.0.1-SNAPSHOT.jar app.jar

# Argumentos para personalizar el perfil y el archivo de configuración
ARG PROFILE=dev
ARG CONFIG_PATH=env.properties

# Establece las variables de entorno para Java
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
ENV SPRING_CONFIG_IMPORT=file:/app/${CONFIG_PATH}

# Copia el archivo de configuración por defecto al contenedor
COPY ${CONFIG_PATH} /app/${CONFIG_PATH}

# Define el punto de entrada
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Dspring.config.import=${SPRING_CONFIG_IMPORT} -jar app.jar"]

# Fin del archivo Dockerfile