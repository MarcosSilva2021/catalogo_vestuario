package com.dev.catalogo_vestuario.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.catalogo_vestuario.models.Vestuario;
import com.dev.catalogo_vestuario.service.VestuarioService;

@RestController
@RequestMapping("/vestuarios")
public class VestuarioController {

    private static final Logger logger = LoggerFactory.getLogger(VestuarioController.class);

    private final VestuarioService service;

    @Autowired
    public VestuarioController(VestuarioService service) {
        this.service = service;
    }

    // Criar um novo vestuário
    @PostMapping
    public ResponseEntity<Vestuario> addVestuario(@RequestBody Vestuario vestuario) {
        logger.info("Recebendo solicitação para adicionar novo vestuário: {}", vestuario);
        Vestuario novoVestuario = service.addVestuario(vestuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVestuario);
    }

    // Buscar vestuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vestuario> findById(@PathVariable String id) {
        logger.info("Buscando vestuário com ID: {}", id);
        Vestuario vestuario = service.findById(id);
        return ResponseEntity.ok(vestuario);
    }

    // Listar todos os vestuários
    @GetMapping
    public ResponseEntity<List<Vestuario>> findAll() {
        logger.info("Buscando todos os vestuários.");
        List<Vestuario> vestuarios = service.findAll();
        return ResponseEntity.ok(vestuarios);
    }

    // Adicionar marca ao vestuário
    @PatchMapping("/{idVestuario}/marca/{idMarca}")
    public ResponseEntity<Vestuario> addMarca(@PathVariable String idVestuario, @PathVariable String idMarca) {
        logger.info("Adicionando marca {} ao vestuário {}", idMarca, idVestuario);
        Vestuario updatedVestuario = service.adddMarca(idVestuario, idMarca);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Adicionar categoria ao vestuário
    @PatchMapping("/{idVestuario}/categoria/{idCategoria}")
    public ResponseEntity<Vestuario> addCategoria(@PathVariable String idVestuario, @PathVariable String idCategoria) {
        logger.info("Adicionando categoria {} ao vestuário {}", idCategoria, idVestuario);
        Vestuario updatedVestuario = service.addCategoria(idVestuario, idCategoria);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Atualizar vestuário
    @PutMapping("/{id}")
    public ResponseEntity<Vestuario> updateVestuario(@PathVariable String id, @RequestBody Vestuario vestuario) {
        logger.info("Atualizando vestuário com ID: {}", id);
        Vestuario updatedVestuario = service.update(id, vestuario);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Deletar vestuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVestuario(@PathVariable String id) {
        logger.info("Deletando vestuário com ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}