package com.dev.catalogo_vestuario.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.catalogo_vestuario.models.Marca;
import com.dev.catalogo_vestuario.repositories.MarcaRepository;

@Service
public class MarcaService {

    //comicação com bd
    private MarcaRepository repository;

    @Autowired
    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public Marca addMarca(Marca marca){
        return repository.save(marca);
    }

    public Marca findById(String uuid){
        return repository.findById(uuid).get();
    }

    public List<Marca> findAll(){
        return repository.findAll();
    }

    public void delete( String uuid) {
        // Verifica se o usuário existe
        Marca existingMarca = repository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Marca não encontrada !"));

        // deletar o usuário
        repository.delete(existingMarca);
    }

    

}
