# Spring Boot API de Registro de Usuarios

![Java](https://img.shields.io/badge/Java-8%2B-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3%2B-blue)

Esta es una simple API para registrar usuarios construida con Spring Boot e incluye Swagger UI para la prueba y documentación.

## Prerrequisitos
- Java 8 o superior
- Maven

## Documentación
La documentación de la API se encuentra [aquí](http://localhost:8080/swagger-ui/). Para generar la documentación en formato JSON, puedes descargarla [aquí](http://localhost:8080/v3/api-docs).

## Ejecutando la API
1. Clona el [repositorio](https://github.com/camilitwo/test-bci):
    ```shell
    git clone https://github.com/camilitwo/test-bci.git
    ```

2. Ejecuta la API usando Maven:
    ```shell
    mvn spring-boot:run
    ```

## Probando la API
Para probar la API, utiliza Swagger UI, disponible [aquí](http://localhost:8080/swagger-ui/). Desde allí, puedes ver una lista de endpoints finales disponibles y probarlos.

## Endpoints finales
- **POST /login:** Autentica un usuario y devuelve un token de acceso.
- **POST /usuarios:** Crea un nuevo usuario.
# /login
## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST para obtener un token de acceso:

```shell
curl --location 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B2C738DC4A21C0D48C88B538F03FBB54' \
--data '{
    "username":"user",
    "password":"password"
}'
```

## Ejemplo de respuesta
####
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicGFzc3dvcmQiOiJwYXNzd29yZCIsImlzcyI6ImF1dGgwIiwiZXhwIjoxNzAxNTYzNjU4LCJ1c2VybmFtZSI6InVzZXIifQ.n9bJSkpzC21QU-rIcOCcT-TxCx49Bw9BIjTX-dXQt3s",
  "type": "Bearer",
  "expiration": "2023-12-03T00:34:18.436+00:00"
}
```

# /users
## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST para guardar un usuario en la base de datos:

```shell
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicGFzc3dvcmQiOiJwYXNzd29yZCIsImlzcyI6ImF1dGgwIiwiZXhwIjoxNzAxNTYzNjU4LCJ1c2VybmFtZSI6InVzZXIifQ.n9bJSkpzC21QU-rIcOCcT-TxCx49Bw9BIjTX-dXQt3s' \
--header 'Cookie: JSESSIONID=0F41F12F394EE6E2A5CF62BBD141DF7D' \
--data-raw '{
   "name":"prueba",
   "email":"prueba@mail.com",
   "password":"123456",
   "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
        },
        {
        "number": "098989899889",
        "citycode": "1",
        "contrycode": "57"
        }
    ]
}'
```

## Ejemplo de respuesta
####
```json
{
  "id": "0ff648ed-e163-4ba8-9972-12840710ba7a",
  "name": "prueba",
  "email": "prueba@mail.com",
  "password": "123456",
  "created": "2023-12-03T00:03:57.760+00:00",
  "lastLogin": "2023-12-03T00:03:57.760+00:00",
  "isActive": true,
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicGFzc3dvcmQiOiJwYXNzd29yZCIsImlzcyI6ImF1dGgwIiwiZXhwIjoxNzAxNTYzNjU4LCJ1c2VybmFtZSI6InVzZXIifQ.n9bJSkpzC21QU-rIcOCcT-TxCx49Bw9BIjTX-dXQt3s",
  "phones": [
    {
      "id": "a18358aa-03f2-4518-b7ba-be272cecb9d2",
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    },
    {
      "id": "e461049e-74bf-48ae-8fcb-fcada2dad526",
      "number": "098989899889",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

**Proyecto realizado por Camilo Gonzalez**

[![GitHub Profile](https://img.shields.io/badge/GitHub-camilitwo-green?style=flat&logo=github)](https://github.com/camilitwo)
