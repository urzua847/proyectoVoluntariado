# Sistema de Gestión de Voluntariado Comunitario

Aplicación de escritorio desarrollada en **Java** bajo el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**, utilizando **PostgreSQL** como sistema de gestión de bases de datos.

---

## 📋 Requisitos del Sistema

Para descargar, compilar y ejecutar este proyecto, asegúrate de contar con el siguiente entorno instalado:

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Apache Maven:** Para la gestión de dependencias y compilación.
* **PostgreSQL Server:** Versión 12 o superior.
* **IDE:** NetBeans, IntelliJ IDEA, Eclipse o VS Code con soporte para proyectos Maven.

---

## 🗄️ Configuración de la Base de Datos (PostgreSQL)

El sistema requiere una base de datos llamada `voluntariado_db`.

### 1. Creación de la Base de Datos

Ejecuta la siguiente sentencia en tu cliente SQL (`pgAdmin` o terminal `psql`):

```sql
-- Crear la base de datos
CREATE DATABASE voluntariado_db;
```

### 2. Creación de Tablas y Datos Iniciales

Una vez conectado a `voluntariado_db`, ejecuta el siguiente script para crear las tablas `Usuarios` y `Solicitudes` (junto con sus restricciones y claves foráneas) e insertar el usuario administrador por defecto:

```sql
-- =======================================================
-- SCRIPT DE CREACIÓN COMPLETO DE LA BASE DE DATOS
-- =======================================================

-- 1. CREAR TABLA USUARIOS
CREATE TABLE Usuarios (
    id VARCHAR(50) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL 
        CHECK (rol IN ('ADMINISTRADOR', 'SOLICITANTE', 'VOLUNTARIO'))
);

-- 2. INSERTAR EL ADMINISTRADOR INICIAL
INSERT INTO Usuarios (id, nombre_completo, email, password, rol) 
VALUES ('admin', 'Administrador Global', 'admin@voluntariado.org', 'admin123', 'ADMINISTRADOR') 
ON CONFLICT (id) DO NOTHING;

-- 3. CREAR TABLA SOLICITUDES
CREATE TABLE Solicitudes (
    id VARCHAR(50) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL 
        CHECK (estado IN ('PENDIENTE', 'ACEPTADA', 'COMPLETADA')),
    solicitanteId VARCHAR(50) NOT NULL,
    voluntarioId VARCHAR(50),
    categoria VARCHAR(50) NOT NULL DEFAULT 'GENERAL'
        CHECK (categoria IN ('GENERAL', 'EDUCACION', 'SALUD', 'MEDIO_AMBIENTE', 'ASISTENCIA_SOCIAL')),
    
    CONSTRAINT fk_solicitante
        FOREIGN KEY (solicitanteId)
        REFERENCES Usuarios (id)
        ON DELETE CASCADE,
        
    CONSTRAINT fk_voluntario
        FOREIGN KEY (voluntarioId)
        REFERENCES Usuarios (id)
        ON DELETE SET NULL
);
```

> **Credenciales por defecto:** Usuario `admin` / Contraseña `admin123`

---

## 📦 Dependencias (`pom.xml`)

El proyecto utiliza las siguientes librerías principales gestionadas por Maven:

| Librería | Propósito |
| :--- | :--- |
| `org.postgresql:postgresql` | Conexión con PostgreSQL (JDBC Driver) |
| `com.itextpdf:itextpdf` | Generación de reportes en formato PDF (iText 5) |

Verifica que la dependencia de **iText 5** esté declarada en tu archivo `pom.xml` para evitar errores de compilación:

```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.5.13.3</version> 
</dependency>
```

---

## 🚀 Compilación y Ejecución

1. **Abrir el proyecto:** Importa la carpeta raíz del proyecto (`voluntariado`) como un proyecto Maven en tu IDE de preferencia.
2. **Instalar dependencias y compilar:** Tu IDE descargará las dependencias automáticamente, o puedes hacerlo desde la terminal en la raíz del proyecto ejecutando:

```bash
mvn clean install
```

3. **Ejecutar:** Inicia la clase principal (`Main`) desde tu IDE o mediante Maven una vez configuradas las credenciales de conexión a PostgreSQL en el código fuente.
