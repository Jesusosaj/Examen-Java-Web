package com.examen.jesus.sosa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.examen.jesus.sosa.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.jesus.sosa.models.EstadisticaModel;
import com.examen.jesus.sosa.models.ProductoModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("productos")
public class ProductoRestController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("listar")
    public ResponseEntity<List<ProductoModel>> ListadoProductos() {
        if(!productoRepository.findAll().isEmpty()){
            return ResponseEntity.ok(productoRepository.findAll());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productoRepository.findAll());
        }
    }

    @GetMapping("estadisticas")
    public ResponseEntity<?> EstadisticasTotales() {
        List<ProductoModel> productos = productoRepository.findAll();

        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay productos registrados.");
        }

        Integer totalProductos = productos.size();
        BigDecimal sumaPrecios = BigDecimal.ZERO;
        Integer disponibles = 0;
        Integer agotados = 0;

        for (ProductoModel producto : productos) {
            sumaPrecios = sumaPrecios.add(producto.getPrecioUnitario() != null ? producto.getPrecioUnitario() : BigDecimal.ZERO);

            if (producto.getEstado() != null && producto.getEstado()) {
                disponibles++;
            } else {
                agotados++;
            }
        }

        BigDecimal promedioPrecios = sumaPrecios.divide(BigDecimal.valueOf(totalProductos), 2, BigDecimal.ROUND_HALF_UP);

        EstadisticaModel estadisticas = new EstadisticaModel();
        estadisticas.setTotalProductos(totalProductos);
        estadisticas.setPromedioPrecios(promedioPrecios);
        estadisticas.setProductosDisponibles(disponibles);
        estadisticas.setProductoNoDisponibles(agotados);

        return ResponseEntity.ok(estadisticas);
    }
    

    @GetMapping("listar/{id}")
    public ResponseEntity<?> ListadoProductoPorId(@PathVariable Integer id) {
        Optional<ProductoModel> find = productoRepository.findById(id);

        if(find.isPresent()){
            ProductoModel productoExistente = find.get();
            return ResponseEntity.ok(productoExistente);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe");
    }
    
    @GetMapping("verificar/disponibilidad/{id}")
    public ResponseEntity<?> VerificarDisponibilidadProducto(@PathVariable Integer id) {
        Optional<ProductoModel> find = productoRepository.findById(id);

        if(find.isPresent()){
            ProductoModel productoExistente = find.get();

            if(!productoExistente.getEstado()){
                return ResponseEntity.ok("Esta disponible con un total de stock de "+productoExistente.getCantidadStock() + " productos");
            }

            return ResponseEntity.ok("Esta agotado");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe");
    }
    

    @PostMapping("crear")
    public ResponseEntity<?> CreacionProducto(@RequestBody ProductoModel producto) {
        Optional<ProductoModel> find = productoRepository.findById(producto.getCodigo());
        
        if(find.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Existe un conflicto ya que el producto ya existe");
        }

        if(producto.getPrecioUnitario() == null || producto.getPrecioUnitario().compareTo(BigDecimal.ZERO) <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El precio no puede ser 0 ni negativo.");
        }

        if(producto.getCantidadStock() == 0){producto.setEstado(false);}else{producto.setEstado(true);}

        productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }
    
    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> ActualizarProducto(@PathVariable Integer id, @RequestBody ProductoModel producto) {
        Optional<ProductoModel> find = productoRepository.findById(id);
        if(find.isPresent()){
            ProductoModel productoExistente = find.get();

            productoExistente.setNombre(producto.getNombre());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
            productoExistente.setCantidadStock(producto.getCantidadStock());

            if(producto.getCantidadStock() == 0){productoExistente.setEstado(false);}else{productoExistente.setEstado(true);}

            productoRepository.save(productoExistente);

            return ResponseEntity.ok("Se actualizo correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto que intenta actualizar no existe");
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<?> EliminarProductos() {
        productoRepository.deleteAll();
        return ResponseEntity.ok("Se elimino todos los productos");
    }

    
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> EliminarProductosPorId(@PathVariable Integer id) {
        Optional<ProductoModel> find = productoRepository.findById(id);

        if(find.isPresent()){
            productoRepository.deleteById(id);
            return ResponseEntity.ok("El producto se elimino con exito.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe.");
    }
}