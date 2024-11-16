package com.dev.catalogo_vestuario.controllers;

import com.dev.catalogo_vestuario.models.Marca;
import com.dev.catalogo_vestuario.service.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService service;

    @Autowired
    public MarcaController(MarcaService service) {
        this.service = service;
    }

    // Adicionar uma nova marca
    @PostMapping
    public ResponseEntity<Marca> addMarca(@RequestBody Marca marca) {
        Marca novaMarca = service.addMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
    }

    // Buscar marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable String id) {
        Marca marca = service.findById(id);
        return ResponseEntity.ok(marca);
    }

    // Listar todas as marcas
    @GetMapping
    public ResponseEntity<List<Marca>> findAll() {
        List<Marca> marcas = service.findAll();
        return ResponseEntity.ok(marcas);
    }

    // Atualizar uma marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable String id, @RequestBody Marca marcaToUpdate) {
        Marca updatedMarca = service.update(id, marcaToUpdate);
        return ResponseEntity.ok(updatedMarca);
    }

    // Deletar uma marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
