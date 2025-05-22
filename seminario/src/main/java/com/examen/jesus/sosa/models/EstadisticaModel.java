package com.examen.jesus.sosa.models;

import java.math.BigDecimal;

public class EstadisticaModel {
    public Integer totalProductos;
    public BigDecimal promedioPrecios;
    public Integer productosDisponibles;
    public Integer productoNoDisponibles;

    public EstadisticaModel(){}

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public BigDecimal getPromedioPrecios() {
        return promedioPrecios;
    }

    public Integer getProductosDisponibles() {
        return productosDisponibles;
    }

    public Integer getProductoNoDisponibles() {
        return productoNoDisponibles;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }

    public void setPromedioPrecios(BigDecimal promedioPrecios) {
        this.promedioPrecios = promedioPrecios;
    }

    public void setProductosDisponibles(Integer productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public void setProductoNoDisponibles(Integer productoNoDisponibles) {
        this.productoNoDisponibles = productoNoDisponibles;
    }

    
}
