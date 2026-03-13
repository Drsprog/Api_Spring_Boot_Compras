<h1>🛒 API de Compras</h1>
<h3>Backend desarrollado con Spring Boot</h3>

<p>
API REST para la gestión de compras desarrollada con Java y Spring Boot.
</p>

---

## 🚀 Información del Proyecto

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(220px,1fr)); gap:15px;">

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 📦 Proyecto
API REST para gestión de compras

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 💻 Lenguaje
Java

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### ⚙️ Framework
Spring Boot

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 🗄 Base de Datos
Oracle Database

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 🧰 Build Tool
Maven

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

</div>

</div>

---

## 🧠 Arquitectura del Proyecto

<div style="display:grid; grid-template-columns: repeat(auto-fit, minmax(220px,1fr)); gap:15px;">

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 🌐 Controller
Expone los endpoints REST y gestiona las peticiones HTTP.

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 🧩 Service
Contiene la lógica de negocio de la aplicación.

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 🗃 Repository
Gestiona la persistencia de datos usando Spring Data JPA.

</div>

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

### 📄 Entity
Representa los modelos de datos almacenados en la base de datos.

</div>

</div>

---

## 🔗 Endpoints

<div style="border:1px solid #30363d; padding:15px; border-radius:10px">

| Método | Endpoint | Descripción |
|------|------|------|
| GET | /compras | Obtener todas las compras |
| GET | /compras/{id} | Obtener compra por ID |
| POST | /compras | Registrar compra |
| PUT | /compras/{id} | Actualizar compra |
| DELETE | /compras/{id} | Eliminar compra |

</div>

---

## ▶️ Ejecutar el proyecto

```bash
git clone https://github.com/Drsprog/Api_Spring_Boot_Compras.git
cd Api_Spring_Boot_Compras
mvn spring-boot:run
