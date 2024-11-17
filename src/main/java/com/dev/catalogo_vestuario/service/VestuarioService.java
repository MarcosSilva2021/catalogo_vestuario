package com.dev.catalogo_vestuario.service;

import java.util.List;

import com.dev.catalogo_vestuario.models.Vestuario;

public interface VestuarioService {

    Vestuario findById(String uuid);

    Vestuario addVestuario(Vestuario vestuario);    

    List<Vestuario> findAll();
    
    Vestuario update(String id, Vestuario vestuarioToUpdate);

    void delete( String uuid) ;

    // Associa uma marca a um vestuario
    Vestuario adddMarca(String idVestuario, String idMarca);

    // Associa uma Categoria a um vestuario
    Vestuario addCategoria(String idVestuario, String idCategoria);
}
