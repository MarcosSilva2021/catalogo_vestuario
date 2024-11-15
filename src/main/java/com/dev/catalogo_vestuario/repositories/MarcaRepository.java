package com.dev.catalogo_vestuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.catalogo_vestuario.models.Marca;

public interface MarcaRepository extends JpaRepository<Marca, String>{
    

}
