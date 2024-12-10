#Dockerfile para la creación de la imagen de la aplicación
#utilizando la imagen base de Java 21
#Se copia el archivo jar de la aplicación a la imagen
#y ejecuta el comando para correr la aplicación
FROM openjdk:21
ARG PROFILE
ARG DB_URL
ARG USER_POSTGRESQL
ARG PASSWORD_POSTGRESQL
ARG EUREKA_URL

ENV SPRING_PROFILES_ACTIVE=${PROFILE}
ENV DB_URL=${DB_URL}
ENV USER_POSTGRESQL=${USER_POSTGRESQL}
ENV PASSWORD_POSTGRESQL=${PASSWORD_POSTGRESQL}
ENV EUREKA_URL=${EUREKA_URL}

VOLUME /tmp
COPY build/libs/asignacioncamasMicroservicio-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","app.jar"]
# Fin del archivo Dockerfile