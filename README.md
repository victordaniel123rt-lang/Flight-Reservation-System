# Plataforma de Reservación de Vuelos (Flight Reservation System)

## Descripción

Repositorio que simula una PLATAFORMA DE RESERVACIÓN DE VUELOS basada en una arquitectura de microservicios. Los microservicios se comunican de forma síncrona (OpenFeign / HTTP) para validaciones puntuales (cliente, vuelo) y de forma asíncrona mediante Apache Kafka para el flujo de eventos (reservación, pago, notificaciones). Se utilizan Spring Boot y el ecosistema Spring Cloud (Eureka, Config Server, Gateway).

## Arquitectura

(Adjunta o coloca la imagen del diagrama de arquitectura en `docs/architecture.png` o en la raíz del repo)

- API Gateway (Spring Cloud Gateway)
- Eureka Discovery Server (Netflix Eureka)
- Config Server (Spring Cloud Config)
- Microservicios:
  - Flight Service (puerto 8081) — gestiona vuelos y asientos
  - Customer Service (puerto 8082) — gestiona clientes/pasajeros
  - Reservation Service (puerto 8083) — gestiona reservas; valida cliente y vuelo (síncrono) y publica eventos (Kafka)
  - Payment Service (puerto 8084) — procesa pagos; consume eventos y publica resultados
  - Notification Service (puerto 8085) — envía correos / SMS; consume eventos
- Infraestructura:
  - Apache Kafka (topics: `reservation-created`, `payment-completed`, `reservation-cancelled`)
  - Zookeeper (si la versión de Kafka lo requiere)
  - Bases de datos independientes (PostgreSQL o MariaDB según configuración):
    - flight_db, customer_db, reservation_db, payment_db, notification_db
  - Orquestación con Docker / Docker Compose

## Tecnologías

- Java + Spring Boot
- Spring Cloud (Eureka, Config, Gateway)
- Spring Data JPA
- OpenFeign (cliente HTTP entre microservicios)
- Apache Kafka (comunicación asíncrona / eventos)
- Resilience4j / Circuit Breaker (opcional)
- Swagger / OpenAPI (documentación de APIs)
- Docker / Docker Compose
- PostgreSQL (o MariaDB) para persistencia

## Reglas de negocio principales

- No vender más asientos de los disponibles.
- No permitir reservación duplicada (mismo cliente y mismo vuelo).
- Si el pago falla, cancelar la reservación automáticamente.
- Al confirmar el pago:
  - Reducir la cantidad de asientos disponibles del vuelo.
  - Si no quedan asientos, marcar el vuelo como FULL / sin disponibilidad.

## Topics de Kafka

- `reservation-created` — publicado por Reservation Service cuando se crea una solicitud de reserva.
- `payment-completed` — publicado por Payment Service si el pago se logra.
- `reservation-cancelled` — publicado por Payment Service (o Reservation Service) si se cancela la reserva por fallo de pago u otra razón.

## Servicios y endpoints (ejemplos)

Nota: Los endpoints exactos pueden variar según la implementación en el código. Ajusta según los controladores reales en cada microservicio.

### Flight Service (8081)
- GET  /api/flights — listar vuelos
- GET  /api/flights/{id} — obtener vuelo
- POST /api/flights — crear vuelo
- PUT  /api/flights/{id}/seats — actualizar asientos

### Customer Service (8082)
- GET  /api/customers — listar clientes
- GET  /api/customers/{id} — obtener cliente
- POST /api/customers — crear cliente

### Reservation Service (8083)
- POST /api/reservations
  - Body ejemplo:
    {
      "customerId": 1,
      "flightId": 10,
      "seats": 2
    }
  - Flujo: valida cliente y vuelo (llamadas síncronas a Customer y Flight), crea la reserva y publica `reservation-created` en Kafka.
- GET /api/reservations/{id} — obtener estado de la reserva

### Payment Service (8084)
- POST /api/payments — iniciar pago (o consumir `reservation-created`)
  - Procesa pago y publica `payment-completed` (o `reservation-cancelled` en caso de fallo)

### Notification Service (8085)
- Consume `payment-completed` y `reservation-cancelled` para enviar correos/SMS.

## Configuración (variables típicas)

Cada servicio normalmente usa variables y propiedades Spring:
- spring.application.name=
- server.port=8081 (u otro puerto por servicio)
- spring.datasource.url=jdbc:postgresql://host:5432/<db>
- spring.datasource.username=
- spring.datasource.password=
- eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
- spring.cloud.config.uri=http://localhost:8888
- spring.kafka.bootstrap-servers=localhost:9092
- spring.kafka.consumer.group-id=...

## Instalación / Requisitos

- Java 11+ (o la versión que use el proyecto)
- Maven (o Gradle si aplica)
- Docker & Docker Compose
- Kafka (si no se usa Docker Compose para infra)
- PostgreSQL / MariaDB (si no se usan contenedores)

## Cómo ejecutar (guía rápida)

### Opción A — Ejecutar todo con Docker Compose (recomendado para entorno local reproducible)
1. Coloca/edita `docker-compose.yml` en la raíz (si el repo ya lo trae, úsalo).
2. Levanta infra: bases de datos, zookeeper, kafka, config-server, eureka, gateway (según compose):
   ```bash
   docker-compose up -d
   ```
3. Construye los servicios (desde la raíz del repo si es multi-módulo Maven):
   ```bash
   mvn -T 1C clean package
   ```
4. Ejecuta cada microservicio (o crea contenedores y ejecútalos):
   - Usando Maven:
     ```bash
     cd flight-service
     mvn spring-boot:run -Dspring-boot.run.profiles=local
     ```
   - O con el JAR:
     ```bash
     java -jar target/flight-service.jar --spring.profiles.active=local
     ```
5. Comprueba Eureka en: http://localhost:8761
   API Gateway (rutas) en el puerto configurado del gateway.
6. Usa Swagger en cada servicio (p. ej. http://localhost:8081/swagger-ui.html) si está activado.

### Opción B — Ejecutar los servicios localmente y usar infra por Docker
1. Levanta infra mínima con Docker Compose (Kafka, PostgreSQL, Config, Eureka).
2. Build y run de cada servicio con Maven o desde tu IDE (IntelliJ/STS).
3. Asegúrate de que `application.yml` apunte a las direcciones de Kafka / Eureka / Config correctas.

## Notas sobre build

- Si el proyecto es multi-módulo Maven, compila la raíz:
  ```bash
  mvn clean install
  ```
- Los artefactos generados estarán en `*/target/*.jar`.

## Pruebas y debugging

- Revisa logs para ver consumo/producción de eventos Kafka.
- Verifica topics en Kafka y en los logs de Payment/Notification.
- Usa Postman / Insomnia para probar endpoints REST.
- Si hay problemas de descubrimiento, comprueba que los servicios se registren en Eureka.

## Buenas prácticas y despliegue

- Mantener configuraciones por ambiente en Config Server (git-backed).
- Habilitar trazabilidad distribuida (p. ej. Sleuth + Zipkin) para seguimiento de llamadas distribuidas.
- Aplicar circuit breaker / retry en llamadas OpenFeign.
- Asegurar idempotencia en consumidores de Kafka cuando sea necesario.
- Monitoreo de métricas (Prometheus / Grafana).

## Estructura sugerida de carpetas

- flight-service/
- customer-service/
- reservation-service/
- payment-service/
- notification-service/
- api-gateway/
- eureka-server/
- config-server/
- docker-compose.yml
- docs/architecture.png

## Contribuir

1. Haz fork y crea una rama feature/my-feature.
2. Implementa y agrega tests.
3. Abre un Pull Request con descripción clara.

## Licencia

Añade la licencia que corresponda (por ejemplo MIT) en un archivo `LICENSE`.

## Contacto / Soporte

- Agrega aquí un email o link al issue tracker del repo si quieres recibir reportes.

---
Si quieres, adapto el README a los nombres exactos de módulos y endpoints que ya están en tu repo (puedo leer los archivos y generar un README que refleje rutas/puertos/propiedades exactas). También puedo añadir un ejemplo de `docker-compose.yml` y archivos de entorno (.env) listos para usar.
