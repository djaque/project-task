# Nombre de tu aplicación

## Requisitos previos
Antes de construir la aplicación, asegúrate de tener instalado lo siguiente:
- Java JDK 17
- Maven (versión requerida)

## Instrucciones de construcción

1. Clona el repositorio:
git clone <URL_del_repositorio>
cd <nombre_del_directorio>

2. Compila el proyecto con Maven:

mvn compile

3. Empaqueta la aplicación:

mvn package

4. Ejecuta la aplicación localmente:

mvn spring-boot:run

5. Una vez que la aplicación se esté ejecutando, puedes utilizar la ruta:

http://localhost:8080/api/task

6. Si deseas ver el swagger de la API puede ingresar en la ruta:
http://localhost:8080/swagger-ui/index.html


7. Para detener la aplicación, presiona `Ctrl + C` en la terminal.

## Opciones adicionales

- Si deseas ejecutar pruebas unitarias, puedes utilizar el siguiente comando:

mvn test

- Si deseas generar solo el archivo JAR sin ejecutar pruebas:

mvn package -Dmaven.test.skip=true


- Para limpiar el proyecto y eliminar archivos compilados:

mvn clean

## Estructura del proyecto

Esta estructura sigue el patrón de arquitectura limpia o Clean Architecture, lo que facilita el desarrollo, pruebas y mantenimiento del código. Cada capa tiene una responsabilidad específica y se comunican a través de interfaces (puertos) para evitar acoplamientos directos entre ellas.

```csharp
└── cl
    └── taskmanager
        ├── TaskManagerApplication.java
        ├── adapter
        │   ├── in
        │   │   └── web
        │   │       ├── CreationTask.java
        │   │       ├── ListTaskController.java
        │   │       └── UpdateTask.java
        │   └── out
        │       └── persistence
        │           ├── SpringTaskRepository.java
        │           ├── TaskEntity.java
        │           ├── TaskMapper.java
        │           └── TaskPersistenceAdapter.java
        ├── application
        │   ├── port
        │   │   ├── in
        │   │   │   └── TaskManagerPort.java
        │   │   └── out
        │   │       ├── CreateTaskPort.java
        │   │       ├── DeleteTaskPort.java
        │   │       ├── GetTaskPort.java
        │   │       ├── ListTaskPort.java
        │   │       └── UpdateTaskPort.java
        │   └── service
        │       └── TaskManagerService.java
        ├── common
        │   ├── PersistenceAdapter.java
        │   └── UseCase.java
        └── domain
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

- common: Clases comunes que pueden ser utilizadas en diferentes capas de la aplicación.

- domain: El dominio de la aplicación, donde se encuentra la representación de elementos de negocio.




## Contribuciones

Si deseas contribuir a este proyecto, por favor realiza un fork y crea una pull request. Agradezco mucho las contribuciones de la comunidad.

## Información de contacto

Si tienes alguna pregunta o problema, no dudes en contactarnos a través de [djaque@gmail.com.com](mailto:djaque@gmail.com) o crea un issue en el repositorio.