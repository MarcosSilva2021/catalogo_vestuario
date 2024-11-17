package com.dev.catalogo_vestuario.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.catalogo_vestuario.models.Categoria;
import com.dev.catalogo_vestuario.repositories.CategoriaRepository;
import com.dev.catalogo_vestuario.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria findById(String uuid) {
        logger.info("Buscando Categoria com ID: {}", uuid);
        return repository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Categoria com ID " + uuid + " não encontrada!"));
    }

    @Override
    public Categoria addCategoria(Categoria categoria) {
        logger.info("Adicionando nova marca: {}", categoria);
        return repository.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        logger.info("Buscando todas as categorias.");
        return repository.findAll();
    }

    @Override
    public Categoria update(String id, Categoria categoriaToUpdate) {
                logger.info("Atualizando categoria com ID: {}", id);

        Categoria existingCategoria = repository.findById(String.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("categoria com ID " + id + " não encontrada!"));

        // Atualizar os atributos necessários
        existingCategoria.setNome(categoriaToUpdate.getNome());        

        Categoria updatedCategoria = repository.save(existingCategoria);
        logger.info("Categoria com ID {} atualizada com sucesso: {}", id, updatedCategoria);

        return updatedCategoria;
    }

    @Override
    public void delete(String uuid) {
        logger.info("Deletando Categoria com ID: {}", uuid);
        Categoria existingCategoria = repository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Categoria com ID " + uuid + " não encontrada!"));
        repository.delete(existingCategoria);
        logger.info("Categoria com ID {} deletada com sucesso.", uuid);
    }

}
