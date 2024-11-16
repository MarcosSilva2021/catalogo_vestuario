package com.dev.catalogo_vestuario.service;

import java.util.List;

import com.dev.catalogo_vestuario.models.Categoria;

public interface CategoriaService {

    Categoria findById(String uuid);

    Categoria addMarca(Categoria categoria);    

    List<Categoria> findAll();
    
    Categoria update(String id, Categoria userToUpdate);

    void delete( String uuid) ;
}
