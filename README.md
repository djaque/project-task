# project-task

El siguiente repositorio cuenta con dos componentes:
- Backend/TaskManager: Applicación spring boot
- fontend/task-web: Applicación react+redux

Antes de iniciar el proyecto se necesita contar con una base de datos Mysql a la cual se pueda conectar el proyecto Spring boot.

## Clone este proyecto

Clona el repositorio:
```bash
git clone https://github.com/djaque/project-task/tree/main
cd <nombre_del_directorio>
```
## Mysql
Instrucciones para levantar la base de datos MySQL con Docker
Antes de ejecutar cualquiera de las aplicaciones, asegúrate de tener Docker instalado en tu sistema. Puedes descargar Docker desde el sitio oficial: https://www.docker.com/.

Pasos para levantar la base de datos MySQL.
En el directorio raíz encontraras un archivo docker-compose.yml el cual tiene preconfigurado las variables necesarias para trabajar con el proyecto.
Este compose expondrá el puerto por defecto de mysql en tu maquina local, así que debes asegurarte de que esté disponible.

Ejecuta el siguiente comando en tu terminal:

```bash
docker-compose up -d
````

## Springboot APP

El directorio Backend/TaskManager: cuenta con su propio readme para mejores referencias.
Dicho projecto es una app de tipo CRUD que se implementó usando clean arquitecture a modo de ejemplo simplemente, se que para proyectos pequeños no vale tanto la pena implementar dicha arquitectura, pero quien sabe si el proyecto crecerá.


```bash
cd Backend/TaskManager

mvn spring-boot:run
``` 

Esta API se levanta utilizando el puerto 8080 en la direccion `http://localhost:8080/api/task`.
Este proyecto cuenta con Swagger y test unitarios.
Visita: http://localhost:8080/swagger-ui/index.html

Y para ejecutar los test
```
mvn test
```


## React app

En el caso de la app de react esta se levantará usando el puerto 3000 en la direccion `http://localhost:3000` para el correcto funcionamiento de esta app el backend debe estar funcionando.

En otra terminal debes ejecutar los siguientes comandos:

```bash
cd frontend/tak-web

npm install
npm start
```

Los comandos anteriores instalaran las dependencias indicadas en el package.json e iniciará el proyecto en modo desarrollo local.
Esta app no cuenta con test de momento. Pero se planea desarrollar los mismos a futuro para incrementar el aprendizaje.
 
