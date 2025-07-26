# Technic Test Back-End

Este proyecto contiene dos microservicios: **Productos** e **Inventario**, desarrollados con **Java 8**, **Spring Boot**, y base de datos **Oracle**. Los servicios están diseñados para demostrar habilidades en desarrollo de API RESTful, pruebas unitarias y consumo entre microservicios.

---

## 🧱 Estructura del Proyecto

```
technic-test-back-end/
├── inventario/           → Microservicio de Inventario
├── productos/            → Microservicio de Productos
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
- Lombok

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
- *(Opcional)* Registrar historial de compras
- *(Opcional)* Emitir eventos cuando cambia el inventario

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

---

## ✅ Pruebas Unitarias

Cada microservicio contiene pruebas con JUnit y Mockito. Para ejecutarlas:

```bash
mvn test
```

---

## 📬 Notas Finales

- Asegúrate de usar Oracle y tener configurado el driver correctamente en el classpath.
- Este proyecto puede ampliarse con eventos asíncronos (como RabbitMQ) o un historial de compras con auditoría.

---

## 📚 Autor

Anderson Causa
