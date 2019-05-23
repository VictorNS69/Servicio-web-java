# Servicio-web-java
Definición e implementación de un servicio web JAVA.

## Objetivo
La práctica consiste en implementar un servicio web, _UserManagement_, que simula un portal para la gestión de estudiantes. En este portal habrá un usuario administrador que dará de alta/baja a usuarios, además de usuarios que pueden cambiar su contraseña, eliminar su cuenta o consultar asignaturas y añadir-consultar notas de asignaturas. Además, se dispone de un servicio web, _UPMCourses_, que ofrece operaciones para consultar la lista de asignaturas por curso, así como consultar si existe una asignatura dada.

Las operaciones en Java del servicio web _UserManagement_ que se debe generar desde el fichero WSDL, son:
- Response login (User user)
- void logout()
- Response addUser (User user)
- Response removeUser (Username username)
- Response changePassword (PasswordPair passwordPair)
- CourseResponse showCourses(Course course)
- addCourseGradeResponse addCourseGrade (CourseGrade courseGrade)
- showAllGradesResponse showAllGrades ()

El WSDL del servicio **_UPMCourses_** dispone de las siguinetes operaciones:
- showCoursesResponse showCourses (ShowCourses course)
- checkCourseResponse checkCourse (CheckCourse course) 

## Enunciado
Para ver el enunciado de la práctica pincha [aquí](/doc/PracticaSW2019.pdf).

## Requisitos
- Tomcat 7
- axis2 1.6.2

## Añadir el proyecto en Tomcat y/o axis2
- Primero debes generar el fichero aar
```sh
cd JavaWebService
ant
```
El fichero `UserManagementWS.aar` se habrá generado en `build/lib`, lo único que hay que hacer es copiarlo al directorio siguiente de tomcat `/webapps/axis2/WEB-INF/services`.

**Nota**: No es estrictamente necesario tener Tomcat. Si solo tienes axis2, puedes copiar el fichero `UserManagementWS.aar` en el directorio de axis2 `repository/services`, y acto seguido iniciar el servidor de axis2
```sh
./bin/axis2server.sh
```
Pasados unos segundos, ya podrás usar el servidor.

## Activar el servidor via terminal
- Ir al directorio donde tengas instalado Tomcat
- Activar el servidor
```sh
./bin/startup.sh
```
Hecho esto, al cabo de unos segundos, ya podrás usar el servidor.

## Ejecutar cliente
Para ejecutar el cliente, tienes que haber añadido el proyecto a Tomcat y/o axis2, tienes que tener el servidor funcionando. Para saber si está funcionando, con acceder a `http://localhost:8080/axis2/services/listServices` y ver que aparecen los servicios de `UserManagementWS` significará que el servidor está funcionando correctamente.

Luego tienes que importar el directorio [UserManagementClient](/UserManagementClient), y **ejecutar como aplicación java el archivo [`UserManagementClient.java`](UserManagementClient/src/es/upm/fi/sos/t3/usermanagement/client/UserManagementClient.java).**

## Autores
[Víctor Nieves Sánchez](https://twitter.com/VictorNS69)

[Daniel Morgera Pérez](https://github.com/dmorgera)

## Licencia
[Licencia](/LICENSE).
