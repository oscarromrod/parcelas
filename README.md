# AgroMonitor

**Autores:** Daniel y Óscar.

Aplicación web para la gestión de parcelas, sensores y producciones agrícolas.

## Tecnologías

- Java 25 y Spring Boot 4
- Spring MVC, Thymeleaf y Spring Data JPA
- MariaDB
- Bootstrap 5.3.3 y Bootstrap Icons
- Gradle

## Funcionalidades

- **Panel:** indicadores del número de parcelas, superficie total, kilos recolectados y partidas de calidad Extra; muestra también las últimas recolecciones.
- **Parcelas:** listado con búsqueda por nombre y filtro por cultivo, ficha, alta, edición y eliminación con ventana de confirmación.
- **Sensores:** listado con filtros por nombre y tipo, vista de sensores de temperatura, ficha, alta, edición y eliminación. El formulario incluye estado y coordenadas.
- **Producciones:** listado con filtros por producto y calidad, ordenado por fecha descendente; ficha, alta, edición y eliminación.
- Formularios con validación en servidor y en el navegador.
- Interfaz adaptable y modo oscuro con preferencia guardada en el navegador.

## Rutas disponibles

| Sección | Ruta |
|---|---|
| Panel | `GET /` |
| Listado de parcelas | `GET /parcelas` |
| Ficha de parcela | `GET /parcelas/{id}` |
| Alta de parcela | `GET /parcelas/nueva` y `POST /parcelas` |
| Edición de parcela | `GET /parcelas/editar/{id}` y `POST /parcelas` |
| Eliminar parcela | `POST /parcelas/{id}/eliminar` |
| Listado de sensores | `GET /sensors` |
| Sensores de temperatura | `GET /sensors/temperatura` |
| Ficha de sensor | `GET /sensors/{id}` |
| Alta de sensor | `GET /sensors/new` y `POST /sensors/save` |
| Edición de sensor | `GET /sensors/update/{id}` y `POST /sensors/save` |
| Eliminar sensor | `POST /sensors/delete/{id}` |
| Listado de producciones | `GET /producciones` |
| Ficha de producción | `GET /producciones/{id}` |
| Alta de producción | `GET /producciones/nueva` y `POST /producciones` |
| Edición de producción | `GET /producciones/editar/{id}` y `POST /producciones` |
| Eliminar producción | `POST /producciones/{id}/eliminar` |

## Ejecución local

Requisitos:

- JDK 25
- MariaDB en ejecución
- Base de datos `ejemplo`

La conexión se configura en `src/main/resources/application.yaml`. Se puede ajustar mediante las variables de entorno `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD`. Los valores predeterminados son para desarrollo local; configura credenciales propias y no las publiques.

Desde la raíz del proyecto:

```bash
./gradlew bootRun
```

La aplicación estará disponible en <http://localhost:8080/>.

Para ejecutar las pruebas:

```bash
./gradlew test
```

## Contenedores y AWS

El repositorio incluye `Containerfile` y `compose.yaml` para ejecutar la aplicación y MariaDB en contenedores. La configuración de Compose debe verificarse antes de usarla: `compose.yaml` especifica `build: .`, pero el archivo de construcción se llama `Containerfile`.

## Estructura del proyecto

```text
src/main/java/com/daw/parcelas/
├── controller/
├── exceptions/
├── model/
├── repository/
└── services/

src/main/resources/
├── messages.properties
├── static/
│   ├── css/
│   └── js/
└── templates/
    ├── error/
    ├── fragments/
    ├── parcelas/
    ├── producciones/
    └── sensores/
```

## Interfaz y asistencia de IA
La interfaz está creada con Bootstrap y estilos propios. GitHub Copilot se utilizó como apoyo para revisar requisitos, resolver dudas de implementación y mejorar documentación y comentarios.
