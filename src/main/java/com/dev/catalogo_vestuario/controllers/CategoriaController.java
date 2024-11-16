package com.dev.catalogo_vestuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.catalogo_vestuario.models.Categoria;
import com.dev.catalogo_vestuario.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // Adicionar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = service.addCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable String id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoria = service.findAll();
        return ResponseEntity.ok(categoria);
    }

    // Atualizar uma categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable String id, @RequestBody Categoria categoriaToUpdate) {
        Categoria updatedCategoria = service.update(id, categoriaToUpdate);
        return ResponseEntity.ok(updatedCategoria);
    }

    // Deletar uma Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
