# Technic Test Back-End

Este proyecto contiene dos microservicios: **Productos**, **Inventario** e **Compra** desarrollados con **Java 17**, **Spring Boot**, y base de datos **Oracle**. Los servicios están diseñados para demostrar habilidades en desarrollo de API RESTful, pruebas unitarias y consumo entre microservicios.

---

## 🧱 Estructura del Proyecto

```
technic-test-back-end/
├── inventario/           → Microservicio de Inventario
├── productos/            → Microservicio de Productos
├── compra/               → Microservicio de compra
├── README.md             → Este archivo
```

---

## 🧪 Requisitos Previos

- Java 17
- Maven 3.8+
- Oracle Database (recomendado Oracle 19c o superior)
- Postman o Swagger para pruebas manuales

---

## 📦 Tecnologías Usadas

- Spring Boot
- Spring Data JPA
- Oracle DB
- JUnit 5 / Mockito para pruebas

---

## ⚙️ Configuración de Oracle

1. Crear usuario con los permisos necesarios (por ejemplo: `user_developer`).
2. Asegurarse de que tenga acceso al tablespace `USERS`:

```sql
ALTER USER user_developer QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO user_developer;
```

3. Configurar `application.properties` de cada microservicio:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=user_developer
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.Oracle10gDialect
```

---

## 📁 Microservicio: Productos

### Modelo:
- `Product`: id, name, description, price.

### Endpoints:

| Método | Ruta              | Descripción                       |
|--------|-------------------|-----------------------------------|
| POST   | /api/product      | Crear un nuevo producto           |
| GET    | /api/product/{id} | Consultar un producto por ID      |
| GET    | /api/product      | Listar todos los productos (paginado) |

---

## 📁 Microservicio: Inventario

### Modelo:
- `Inventario`: producto_id, cantidad

### Funcionalidades:

- Consultar cantidad de un producto (consulta el microservicio de productos)
- Actualizar cantidad disponible
- Registrar historial de compras
- Emitir eventos cuando cambia el inventario

---

## ▶️ Instrucciones de Ejecución

1. Asegúrate de que Oracle esté corriendo localmente y accesible.
2. Clona este repositorio:

```bash
git clone https://github.com/fabian346/technic-test-back-end.git
```

3. Entra a cada microservicio (`productos/` o `inventario/`) y ejecuta:

```bash
mvn spring-boot:run
```

4. Los endpoints estarán disponibles en:

```
http://localhost:8080/api/product
http://localhost:8081/api/inventario
```
🔁 Tolerancia a fallos

Tiempo de espera configurable (3 segundos por defecto)

Reintento simple de hasta 3 veces al consultar otro servicio

📝 Documentación OpenAPI (Swagger UI)

Disponible en:

http://localhost:8080/swagger-ui/index.html#/ → Microservicios

---

## ✅ Pruebas Unitarias

Cada microservicio contiene pruebas con JUnit y Mockito. Para ejecutarlas:

```bash
mvn test
```

---
🧾 Flujo de Compra (Orquestado desde Inventario)

El cliente llama al endpoint de compra en Inventario (/purchase).

Inventario consulta vía HTTP al microservicio de Productos para validar existencia.

Si el producto existe y hay stock suficiente:

Actualiza la cantidad en inventario

Registra en el historial

Emite un evento (opcional)

Devuelve la información de la compra

Si hay error (no existe, sin stock): se retorna el mensaje correspondiente.

🗃️ Arquitectura y Diagrama

🔗 Diagrama de Interacción

sequenceDiagram
participant Cliente
participant Inventario
participant Productos

    Cliente->>Inventario: POST /purchase
    Inventario->>Productos: GET /products/{id} (con API Key)
    Productos-->>Inventario: Datos del producto
    Inventario->>Inventario: Verifica stock
    Inventario->>Inventario: Actualiza stock
    Inventario->>Historial: Guarda compra
    Inventario-->>Cliente: Resultado de la compra

🏗️ Estructura de Carpetas (Simplificada)

technic-test-back-end/
├── product-service/
│   └── src/main/java/.../controller, service, model, repository
├── inventory-service/
│   └── src/main/java/.../controller, service, model, repository



## 📬 Notas Finales

- Asegúrate de usar Oracle y tener configurado el driver correctamente en el classpath.
- Este proyecto puede ampliarse con eventos asíncronos (como RabbitMQ) o un historial de compras con auditoría.

---

## 📚 Autor

Anderson Causa
