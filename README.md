# Gestión de parcelas (Daniel y Óscar)

## Tecnologías

- Java 25
- Spring Boot
- Spring MVC y Thymeleaf
- Spring Data JPA
- MariaDB
- Bootstrap

## Requisitos

- JDK 25
- MariaDB en ejecución

## Configuración de la base de datos

Crea la base de datos `ejemplo` en MariaDB:

La conexión se configura en `src/main/resources/application.yaml`. Ajusta la URL, el usuario y la contraseña para que coincidan con tu instalación de MariaDB. La configuración actual usa el puerto `3306`.

## Ejecución

Ejecuta la aplicación en intellij

Abre el listado de parcelas en:

```text
http://localhost:8080/parcelas
```

El formulario para registrar una parcela está disponible en:

```text
http://localhost:8080/parcelas/nueva
```

## Funcionalidades

- Listado de parcelas con nombre, ubicación, superficie y tipo de cultivo.
- Visualización del detalle de cada parcela.
- Registro de nuevas parcelas.
- Eliminación de parcelas con confirmación.
- Cálculo de la superficie total.

## Estructura principal

```text
src/main/java/com/daw/parcelas/
├── controller/   
├── model/        
├── repository/ 
└── services/

src/main/resources/
├── static/
└── templates/
```
