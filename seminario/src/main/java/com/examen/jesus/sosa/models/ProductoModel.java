package com.examen.jesus.sosa.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "productos")
public class ProductoModel {
    @Id
    @Column(name="codigo")
    public Integer codigo;
    @Column(name="n_producto", nullable = false,length = 255)
    public String nombre;
    @Column(name="d_producto", nullable = false, length = 255)
    public String descripcion;
    @Column(name="c_stock", nullable = false)
    public Integer cantidadStock;
    @Column(name="p_unitario", nullable = false)
    public BigDecimal precioUnitario;
    @Column(name="estado", nullable = true)
    public Boolean estado;

    public ProductoModel(){}

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}