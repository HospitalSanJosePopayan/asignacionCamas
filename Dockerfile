FROM openjdk:21

# Crea un directorio temporal
VOLUME /tmp

# Copia el .jar generado
COPY build/libs/asignacioncamasMicroservicio-0.0.1-SNAPSHOT.jar app.jar

# Establece un perfil predeterminado como dev
ARG PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=$PROFILE

# Monta el archivo env.properties en la raíz del contenedor
VOLUME /env.properties

# Establece el punto de entrada con el perfil activo
ENTRYPOINT ["java","-Duser.timezone=America/Bogota","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dspring.config.import=file:/env.properties", "-jar", "app.jar"]

