SISTEMA DE GESTIÓN DE VOLUNTARIADO COMUNITARIO

Proyecto desarrollado en Java bajo el patrón de arquitectura Modelo-Vista-Controlador (MVC), utilizando PostgreSQL como sistema de gestión de bases de datos.

1. REQUISITOS DEL SISTEMA

Para descargar, compilar y ejecutar este proyecto, necesitas tener instalado el siguiente software:

Java Development Kit (JDK) 17 o superior.

Apache Maven (para la gestión de dependencias y compilación).

PostgreSQL Server (Versión 12 o superior).

NetBeans o IDE compatible con proyectos Maven.

2. CONFIGURACIÓN DE LA BASE DE DATOS (POSTGRESQL)

El proyecto requiere una base de datos específica llamada voluntariado_db.

2.1. Creación de la Base de Datos

Ejecuta las siguientes sentencias en tu cliente SQL (pgAdmin o terminal psql):

-- Crear la base de datos (si no existe)
CREATE DATABASE voluntariado_db;



2.2. Script de Creación de Tablas

Una vez conectado a voluntariado_db, ejecuta el siguiente script para crear las tablas Usuarios y Solicitudes (incluyendo la columna categoria y las Claves Foráneas) e insertar el usuario administrador inicial.

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


3 Verificación de Dependencias (pom.xml)

El proyecto se basa en las siguientes dependencias Maven, necesarias para la conectividad y la generación de reportes:

| Librería | Propósito |
| org.postgresql:postgresql | Conexión con PostgreSQL (JDBC Driver) |
| com.itextpdf:itextpdf | Generación de reportes PDF (iText 5) |

Asegúrate de que tu pom.xml contenga la dependencia de iText 5 para evitar errores de compilación:

<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.5.13.3</version> 
</dependency>



3.3. Compilación y Ejecución

Abrir Proyecto: Abre la carpeta del proyecto (voluntariado) en tu IDE (NetBeans, etc.).

Descargar Dependencias: Tu IDE o Maven compilará y descargará automáticamente las dependencias. Si usas la terminal, ejecuta:

mvn clean install
