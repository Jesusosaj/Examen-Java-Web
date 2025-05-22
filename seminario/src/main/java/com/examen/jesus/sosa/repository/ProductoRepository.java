package com.examen.jesus.sosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.jesus.sosa.models.ProductoModel;

public interface ProductoRepository extends JpaRepository<ProductoModel, Integer>{}