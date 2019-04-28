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
\#TODO

## Importar proyecto
\#TODO
## Activar el servidor localhost de Tomcat desde Eclipse
\#TODO
## Memoria del proyecto
\#TODO

## Autores
[Víctor Nieves Sánchez](https://twitter.com/VictorNS69)

Daniel Morgera Pérez

## Licencia
[Licencia](/LICENSE).
