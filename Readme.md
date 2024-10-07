# Plantilla basica para crear un nuevo microservicio

## Descripcion
Cuenta ya con la seguridad implementada con usuario y contraseña tomado desde la base de datos.
Cuenta con swagger para documentar la API y se accede mediante la ruta `http://localhost:puerto/swagger-ui.html`.

## AJUSTES 

### 1. Cambiar el nombre del proyecto
Cambiar el nombre del proyecto en el archivo `settings.gradle.kts` en la raiz del proyecto.

rootProject.name = 'nombre-del-proyecto'

### 2. Cambiar el nombre del paquete
Cambiar el nombre del paquete pararse en la raiz del proyecto `src/main/java/hujsp/api/plantilla` dar clic derecho y 
seleccionar la opción `Refactor` -> `Rename` y cambiar el nombre del paquete.

### 3. Cambiar el nombre de la clase principal del proyecto
en este caso es `PlantillaMircoservicioApplication` a `NombreDelProyectoApplication` en el mismo archivo.

### 4. Cambiar nombre de la carpeta
que contien el microservicio en este caso es `plantilla` a `nombre-del-proyecto` lo puedes hacer desde el explorador de archivos y renombrar la carpeta, luego volver a abrir tu proyecto en tu IDE.

### 5. Cambiar el nombre de la aplicación 
desde el archivo `application.properties` en la propiedad `spring.application.name=nombre-del-proyecto`.

### 6. Cambiar el puerto del proyecto 
en el archivo `application.properties` en la propiedad `server.port=puerto` este cambiarlo de ser necesario, pero cada microservicio debe tener un puerto diferente, no sera el puerto final donde 
se va a desplegar la información.


# Dependencias con las que ya cuenta el proyecto
- Spring Boot DevTools
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL Driver
- Lombok
- ModelMapper
- Swagger

# Atener en cuenta
- Debes tener instalado PostgreSQL en tu maquina.
- Debes tener instalado Lombok en tu IDE.
- Las constraseñas de la base de datos se deben encriptar con BCrypt. (contrsaeña: `123` = $2a$12$SS6jdjV/WmzErkwmXcCPO.nEisI.kIWZHOdZShTu/xAYNJlndIvOa) en caso de que requieras.

# Importante
- Mantener buenas practicas al nombrar las clases y metodos.
- Mantener el codigo limpio y ordenado.
- Mantener la estructura del proyecto.
- Mantener actualizado el README.md con la información del proyecto.


 
