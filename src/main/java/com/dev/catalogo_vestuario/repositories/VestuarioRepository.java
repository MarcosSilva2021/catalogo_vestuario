package com.dev.catalogo_vestuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.catalogo_vestuario.models.Vestuario;

public interface VestuarioRepository extends JpaRepository<Vestuario, String>{

}
