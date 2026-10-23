# ms-exchange-rate

Servicio REST desarrollado con Quarkus para consultar el tipo de cambio y consultar el historial de consultas asociado a un documento. Las consultas se almacenan en PostgreSQL.

## Requisitos

- Java 21
- PostgreSQL accesible desde la aplicacion
- Conexion a Internet para consultar el servicio externo de tipo de cambio

## Configuracion

La configuracion se encuentra en `src/main/resources/application.properties`. Antes de iniciar el servicio, configura las credenciales de PostgreSQL y confirma que exista la base de datos `ms-exchange-rate` en `localhost:5432` (o ajusta la URL para tu entorno).

La aplicacion escucha en el puerto `8083`. El esquema de Hibernate esta configurado con `drop-and-create`; al iniciar, las tablas se vuelven a crear y los datos existentes se eliminan. Cambia esta configuracion antes de usar el servicio si necesitas conservar datos.

## Endpoints

### Consultar tipo de cambio

```http
GET /api/v1/tipo-cambio/{documento}
```

Obtiene el tipo de cambio del servicio externo y registra la consulta para el documento indicado.

Ejemplo:

```shell
curl.exe http://localhost:8083/api/v1/tipo-cambio/12345678
```

Respuesta `200 OK`:

```json
{
  "fecha": "2026-10-22",
  "sunat": 3.5,
  "compra": 3.49,
  "venta": 3.51
}
```

El documento debe contener al menos 8 caracteres. Se permiten como maximo 10 consultas por documento al dia; el servicio responde `400 Bad Request` si el documento no cumple la longitud minima y `429 Too Many Requests` al alcanzar el limite diario.

### Consultar registros por documento

```http
GET /api/v1/tipo-cambio/{documento}/registros
```

Devuelve la lista de consultas registradas para el documento, con la fecha y los valores de tipo de cambio.

Ejemplo:

```shell
curl.exe http://localhost:8083/api/v1/tipo-cambio/12345678/registros
```

Respuesta `200 OK`:

```json
[
  {
    "fecha": "2026-10-22",
    "sunat": 3.5,
    "compra": 3.49,
    "venta": 3.51
  }
]
```

Si el documento no tiene consultas, el servicio responde `404 Not Found`. Tambien requiere un documento de al menos 8 caracteres.

## Iniciar en modo desarrollo

Desde la raiz del proyecto, ejecuta:

```powershell
.\mvnw.cmd quarkus:dev
```

El servicio queda disponible en `http://localhost:8083`. Detenlo con `Ctrl+C`.

## Empaquetar y ejecutar

```powershell
.\mvnw.cmd package
java -jar target\quarkus-app\quarkus-run.jar
```

La aplicacion empaquetada tambien requiere que PostgreSQL este configurado y disponible.
