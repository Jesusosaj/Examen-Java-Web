# 🍃 Seminario de Java Web – Examen Final

**Estudiante:** Jesús Alexander Sosa Jiménez  
**Matrícula:** `2022100436`

---

## 🔗 Endpoints HTTP REST API

---

### 📥 GET

🔹 **Listar todos los productos**  
`GET /productos/listar`  

http://localhost:8080/productos/listar


🔹 **Buscar producto por ID**  
`GET /productos/listar/{id}`  

http://localhost:8080/productos/listar/{id}


🔹 **Ver estadísticas generales**  

```json
{
  "totalProductos": 100,
  "promedioPrecios": 4.2,
  "cantidadDisponibles": 2,
  "cantidadNoDisponibles": 1
}
```

`GET /productos/estadisticas`  

http://localhost:8080/productos/estadisticas


🔹 **Verificar disponibilidad de producto por ID**  
`GET /productos/verificar/disponibilidad/{id}`  

http://localhost:8080/productos/verificar/disponibilidad/{id}


---

### 📤 POST 

🔹 **Crear un nuevo producto**  
`POST /productos/crear`  

📦 **Body JSON**:
```json
{
  "codigo": 100,
  "nombre": "Tomate",
  "descripcion": "Es una fruta.",
  "cantidadStock": 1,
  "precioUnitario": 1.2
}
```

http://localhost:8080/productos/crear


### ✏️ PUT 

🔹 **Actualizar un producto**  
`PUT /productos/actualizar/{id}`  

📦 **Body JSON**:
```json
{
  "nombre": "Tomate",
  "descripcion": "Es una fruta.",
  "cantidadStock": 1,
  "precioUnitario": 1.2
}
```

http://localhost:8080/productos/actualizar/{id}

### ❌ DELETE 

🔹 **Eliminar todos los productos**  
`DELETE /productos/eliminar`  

http://localhost:8080/productos/eliminar

🔹 **Eliminar producto por ID**  
`DELETE /productos/eliminar/{id}`  

http://localhost:8080/productos/eliminar/{id}
