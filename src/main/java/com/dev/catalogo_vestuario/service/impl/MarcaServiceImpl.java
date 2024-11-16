package com.dev.catalogo_vestuario.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.catalogo_vestuario.models.Marca;
import com.dev.catalogo_vestuario.repositories.MarcaRepository;
import com.dev.catalogo_vestuario.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaServiceImpl.class);

    private final MarcaRepository repository;

    @Autowired
    public MarcaServiceImpl(MarcaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Marca addMarca(Marca marca) {
        logger.info("Adicionando nova marca: {}", marca);
        return repository.save(marca);
    }

    @Override
    public Marca findById(String uuid) {
        logger.info("Buscando marca com ID: {}", uuid);
        return repository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Marca com ID " + uuid + " não encontrada!"));
    }

    @Override
    public List<Marca> findAll() {
        logger.info("Buscando todas as marcas.");
        return repository.findAll();
    }

    @Override
    public void delete(String uuid) {
        logger.info("Deletando marca com ID: {}", uuid);
        Marca existingMarca = repository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Marca com ID " + uuid + " não encontrada!"));
        repository.delete(existingMarca);
        logger.info("Marca com ID {} deletada com sucesso.", uuid);
    }

    @Override
    public Marca update(String id, Marca marcaToUpdate) {
        logger.info("Atualizando marca com ID: {}", id);

        Marca existingMarca = repository.findById(String.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Marca com ID " + id + " não encontrada!"));

        // Atualizar os atributos necessários
        existingMarca.setNome(marcaToUpdate.getNome());        

        Marca updatedMarca = repository.save(existingMarca);
        logger.info("Marca com ID {} atualizada com sucesso: {}", id, updatedMarca);

        return updatedMarca;
    }
}
