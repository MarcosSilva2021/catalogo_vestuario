package com.dev.catalogo_vestuario.service;

import java.util.List;

import com.dev.catalogo_vestuario.models.Marca;

public interface MarcaService {

    Marca findById(String uuid);

    Marca addMarca(Marca marca);    

    List<Marca> findAll();
    
    Marca update(String id, Marca userToUpdate);

    void delete( String uuid) ;    

}
