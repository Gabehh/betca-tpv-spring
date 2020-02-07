## [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
## Back-end con Tecnologías de Código Abierto: **SPRING**
> Proyecto TPV. Este proyecto es un apoyo docente de la asignatura. Es una aplicación Back-end,
mediante programación asíncrona, funcionando conjuntamente con 
Front-end: [betca-tpv-angular](https://github.com/miw-upm/betca-tpv-angular), 
realizado en Angular.

### Estado del código  
[![Build Status](https://travis-ci.org/miw-upm/betca-tpv-spring.svg?branch=develop)](https://travis-ci.org/miw-upm/betca-tpv-spring)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=es.upm.miw%3Abetca-tpv-spring&metric=alert_status)](https://sonarcloud.io/code?id=es.upm.miw%3Abetca-tpv-spring)
[![BCH compliance](https://bettercodehub.com/edge/badge/miw-upm/betca-tpv-spring?branch=develop)](https://bettercodehub.com/)
[![Heroku broken](https://betca-tpv-spring.herokuapp.com/api/v0/system/version-badge)](https://betca-tpv-spring.herokuapp.com/api/v0/system/app-info)

### Tecnologías necesarias
`Java` `Maven` `Spring-Boot` `Reactor` `MondoDB` --- CI: `GitHub` `Travis-CI` `Sonarcloud` `Better Code Hub` `Heroku`

### Clonar el proyecto
 Clonar el repositorio en tu equipo, **mediante consola**:
```sh
> cd <folder path>
> git clone https://github.com/miw-upm/betca-tpv-spring
```
Importar el proyecto mediante **IntelliJ IDEA**
1. **Import Project**, y seleccionar la carpeta del proyecto.
1. Marcar **Create Project from external model**, elegir **Maven**.
1. **Next** … **Finish**.

> Ejecución de test:
> * Utiliza MongoDB embebido 

> Ejecución en local:
> * Se debe tener arrancado el motor de MongoDB: `mongodb://localhost:27017/tpv`  
> * Ejecutar el **API** en linea de comando, mediante: `> mvn clean spring-boot:run`  

## Presentación
Este proyecto es la práctica de Angular desarrollada de forma colaborativa por todos los alumnos.
Se parte de la versión `core`, ya implementada, y se pretende ampliar con un conjunto de mejoras.  
Un **T**erminal **P**unto de **V**enta es un sistema informático que gestiona el proceso de venta mediante una interfaz accesible para los vendedores o compradores.
Un único sistema informático permite la creación e impresión del recibo ticket o factura de venta —con los detalles de las referencias y precios— de los artículos vendidos, actualiza los cambios en el nivel de existencias de mercancías (STOCK) en la base de datos...

## Arquitectura
![](https://github.com/miw-upm/betca-tpv-spring/blob/develop/docs/tpv-architecture.png)

### Responsabilidades
* `config` Clases de configuración de **Spring**.
* `exceptions`tratamiento de errores, convierte las excepciones lanzadas en respuestas de error HTTP.
* `rest_controllers` Clases que conforman el **API**.
   * Define el path del recurso.
   * Deben organizar la recepción de datos de la petición.
   * Delega en los **dtos** la validación de campos.
   * Delega en **exceptions** las respuestas de errores **HTTP**.
   * Delega en los **bussines_controllers** la ejecución de la petición.
* `bussines_controllers` Clases que procesan la petición.
   * Desarrollan el proceso que conlleva la ejecución de la petición.
   * Construye los **documents** a partir de los **dtos** de entrada.
   * Delega en los **dtos** la construcción de los **dtos** de respuesta a partir de los **documents**.
   * Delega en los **repositories** el acceso básico a las BD.
   * Delega en los **data_services** procesos de acceso avanzado a las BD.
   * Delega en los **business_services** procesos genéricos avanzados de la capa de negocio.
* `busines_services` Clases de servicios de apoyo, como fachada de construcción de PDF, fachada de tratamiento de JWT, encriptación...
* `data_services` Clases de servicios avanzados de BD.
* `repositories` Clases de acceso a BD mediante el patrón DAO.
   * Operaciones CRUD sobre BD.
   * Consultas a BD.
* `documents` Clases con los documentos persistentes en BD y utilidades.

## Autenticación
Se plantean mediante **Basic Auth** para logearse y obtener un **API Key** o **token** de tipo **JSON Web Tokens (JWT)**. Uso del **Bearer APIkEY** para el acceso a los recursos.  
Para obtener el **API Key** se accede al recurso: `POST \users\token`, enviando por **Basic auth** las credenciales, van en la cabecera de la petición.
Para el acceso a los recursos, se envia el **token** mediante **Bearer auth**, tambien en la cabecera de la petición.
> Authorization = Basic "user:pass"<sub>Base64</sub>  
> Authorization = Bearer "token"<sub>Base64</sub>  

Para asegurar los recursos, se plantea una filosofía distribuida, es decir, se establece sobre cada recursos (clase). Para ello, se anotará sobre cada clase los roles permitidos; modificando el rol sobre el método si éste, tuviese un rol diferente.  
```java
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
public class Clazz {
    //...
    @PreAuthorize("hasRole('ADMIN')")
    public void method(){...}
    //...
    public void method2(){...}
}
```
Existe un rol especial que se obtiene cuando se envía el usuario y contraseña por **Basic Auth** y es el rol de **authenticated**, sólo se utiliza para logearse.

![](https://github.com/miw-upm/betca-tpv-spring/blob/develop/docs/authentication.png)

## Tratamiento de errores
Se realiza un tratamiento de error centralizado.  
![](https://github.com/miw-upm/betca-tpv-spring/blob/develop/docs/exceptions.png)

## DTOs
Son los objetos de transferencia del API, para recibir los datos (input) y enviar las respuestas (output).

* Los **input** se encargan de las validaciones de entrada mediante las anotaciones.  
* Los **output**. Se deben poder construir a partir de los **documentos**. Añadir la anotación `@JsonInclude(Include.NON_NULL)` para que no se devuelvan null en el Json.

![](https://github.com/miw-upm/betca-tpv-spring/blob/develop/docs/dtos.png)   

## Bases de datos
> Se dispone de un servicio para poblar la BD: DatabaseSeederService se carga automáticamente al iniciar la aplicación en el perfil **dev**.  
> Existe el recurso `/admins/db` para poder borrar o poblar la BD.  
> El servicio `DatabaseSeederService` nos permiter recargar las BD.  
> Se debe intentar no abusar de la recarga, ya que ralentiza la ejecución de los tests.

:exclamation: **Si se crea un nuevo _documento_, se debe añadir el `deleteAll()` asociado al nuevo documento, dentro del método `deleteAllAndInitialize` de la clase `DatabaseSeederService`**

