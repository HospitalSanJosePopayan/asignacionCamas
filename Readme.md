# Microservicio para Camas & Camilleros

## Descripcion Camas
Este microservicio se encarga de gestionar las solicitudes de camas, cuando se realiza una solicitud de cama se espera que el servicio de una respuesta a esa solicitud.


## Descripcion Camilleros
Este se encarga de realizar una solicitud de camillero des de el servicio origen hasta el destino final, el cual administra un lider central de camilleros quien remite la solicitud a un camillero en especifico..

Cuenta con swagger para documentar la API y se accede mediante la ruta:

http://localhost:8004/swagger-ui.html

# Dependencias con las que ya cuenta el proyecto
- Spring Boot DevTools
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL Driver
- Lombok
- ModelMapper
- Swagger

# Para realizar cambios no se debe modificar el archivo propperties
- evitar hacer commit del arcivo application.properties
- usar el siguiente comando para eviar que se tomen los cambios del archivo application.properties
```bash  
git update-index --assume-unchanged src/main/resources/application.properties
```
- desactivar el comando anterior para tomar los cambios del archivo application.properties
```bash
git update-index --no-assume-unchanged src/main/resources/application.properties
```

# Atener en cuenta
- se conecta a una base de datos postgresql la cual ya debe tener las tablas persona, usuario y roles.
- Se debe configurar el archivo application.properties con los datos de la base de datos.
- Se debe desactivar el eureka client en el archivo application.properties.
- Se debe configurar la opcion para crear-actualizar la base de datos en el archivo application.properties.

# Importante
- Mantener buenas practicas al nombrar las clases y metodos.
- Mantener el codigo limpio y ordenado.
- Mantener la estructura del proyecto.
- Mantener actualizado el README.md con la información del proyecto.

# Archivos de configuración
- ``application.properties`` se ejecuta por defecto 
- ``application-dev.properties`` usar un archivo externo para configurar el perfil de desarrollo
- ``application-prod.properties`` usar un archivo externo para configurar el perfil de produccion
- ``env.properties`` archivo alojado en el servidor con información sensible


 
