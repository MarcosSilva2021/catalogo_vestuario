package com.dev.catalogo_vestuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.catalogo_vestuario.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}
