# project-task

El siguiente repositorio cuenta con dos componentes:
- Backend/TaskManager: Applicación spring boot
- fontend/task-web: Applicación react+redux

Antes de iniciar el proyecto se necesita contar con una base de datos Mysql a la cual se pueda conectar el proyecto Spring boot.

## Clone este proyecto

Clona el repositorio:
```bash
git clone git@github.com:djaque/project-task.git
cd project-task
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

Este projecto es una app de tipo CRUD que se implementó usando clean arquitecture a modo de ejemplo simplemente.


```bash
cd Backend/TaskManager

mvn clean install
mvn spring-boot:run
``` 

Esta API se levanta utilizando el puerto 8080 en la direccion http://localhost:8080/api/task.

Este proyecto cuenta con Swagger y test unitarios.
Visita: http://localhost:8080/swagger-ui/index.html

## Requisitos previos
Antes de construir la aplicación, asegúrate de tener instalado lo siguiente:
- Java Open JDK version: 20.0.1
- Maven 3.9.3

## Instrucciones de construcción

1. Clona el repositorio

2. Compila el proyecto con Maven:

```bash 
mvn compile
```

3. Empaqueta la aplicación:

```bash
mvn package
```

4. Ejecuta la aplicación localmente:

```bash
mvn spring-boot:run
```

5. Una vez que la aplicación se esté ejecutando, puedes utilizar la ruta: http://localhost:8080/api/task

6. Si deseas ver el swagger de la API puede ingresar en la ruta:
http://localhost:8080/swagger-ui/index.html


7. Para detener la aplicación, presiona `Ctrl + C` en la terminal.

## Opciones adicionales

- Si deseas ejecutar pruebas unitarias, puedes utilizar el siguiente comando:
```bash
mvn test
```

- Si deseas generar solo el archivo JAR sin ejecutar pruebas:

```bash
mvn package -Dmaven.test.skip=true
```

- Para limpiar el proyecto y eliminar archivos compilados:

```bash 
mvn clean
```

## Estructura del proyecto

Esta estructura sigue el patrón de arquitectura limpia o Clean Architecture, lo que facilita el desarrollo, pruebas y mantenimiento del código. Cada capa tiene una responsabilidad específica y se comunican a través de interfaces (puertos) para evitar acoplamientos directos entre ellas.

```csharp
.
└── cl
    └── taskmanager
        ├── TaskManagerApplication.java
        ├── adapter
        │   ├── in
        │   │   └── web
        │   │       ├── BoardController.java
        │   │       ├── BoardCreation.java
        │   │       ├── CreationTask.java
        │   │       ├── ListTaskController.java
        │   │       └── UpdateTask.java
        │   └── out
        │       └── persistence
        │           ├── BoardEntity.java
        │           ├── BoardMapper.java
        │           ├── BoardPersistenceAdapter.java
        │           ├── SpringBoardRepository.java
        │           ├── SpringTaskRepository.java
        │           ├── TaskEntity.java
        │           ├── TaskMapper.java
        │           └── TaskPersistenceAdapter.java
        ├── application
        │   ├── port
        │   │   ├── in
        │   │   │   ├── BoardManagerPort.java
        │   │   │   └── TaskManagerPort.java
        │   │   └── out
        │   │       ├── board
        │   │       │   ├── CreateBoardPort.java
        │   │       │   ├── DeleteBoardPort.java
        │   │       │   ├── GetBoardPort.java
        │   │       │   ├── ListBoardPort.java
        │   │       │   └── UpdateBoardPort.java
        │   │       └── task
        │   │           ├── CreateTaskPort.java
        │   │           ├── DeleteTaskPort.java
        │   │           ├── GetTaskPort.java
        │   │           ├── ListTaskPort.java
        │   │           └── UpdateTaskPort.java
        │   └── service
        │       ├── BoardManagerService.java
        │       └── TaskManagerService.java
        ├── common
        │   ├── PersistenceAdapter.java
        │   └── UseCase.java
        └── domain
            ├── Board.java
            └── Task.java
```

- TaskManagerApplication.java: Clase principal de la aplicación Spring Boot que inicializa la aplicación.

- adapter: En este paquete, se encuentran las implementaciones concretas de los puertos de entrada y salida (in y out).

    - adapter.in.web: Implementaciones de los controladores web para interactuar con la aplicación desde el exterior.

    - adapter.out.persistence: Implementaciones concretas para la persistencia de datos (adaptadores de persistencia) que interactúan con la base de datos.

- application: Aquí se encuentran los casos de uso de la aplicación y las interfaces de los puertos para interactuar con ellos.

    - application.port.in: Interfaces para los puertos de entrada de los casos de uso.

    - application.port.out: Interfaces para los puertos de salida de los casos de uso.

    - application.service: Implementaciones de los casos de uso que contienen la lógica de negocio.

- common: Clases comunes que pueden ser utilizadas en diferentes capas de la aplicación. Estos elementos de momento son solo definiciones para las anotaciones, así independizamos el core del framework.

- domain: El dominio de la aplicación, donde se encuentra la representación de elementos de negocio.





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

## Contribuciones

Si deseas contribuir a este proyecto, por favor realiza un fork y crea una pull request. Agradezco mucho las contribuciones de la comunidad.

## Información de contacto

Si tienes alguna pregunta o problema, no dudes en contactarnos a través de [djaque@gmail.com](mailto:djaque@gmail.com) o crea un issue en el repositorio.
 
