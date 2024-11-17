package com.dev.catalogo_vestuario.controllers;

import com.dev.catalogo_vestuario.models.Marca;
import com.dev.catalogo_vestuario.service.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/marca")
@Tag(name = "Catalogo_Vestuário - Marca", description = "API para gerenciamento de marcas.")
public class MarcaController {

    private final MarcaService service;

    @Autowired
    public MarcaController(MarcaService service) {
        this.service = service;
    }

    // Adicionar uma nova marca
    @Operation(summary = "Adicionar uma nova marca", description = "Cria uma nova marca no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PostMapping
    public ResponseEntity<Marca> addMarca(
            @RequestBody Marca marca) {
        Marca novaMarca = service.addMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
    }

    // Buscar marca por ID
    @Operation(summary = "Buscar uma marca por ID", description = "Retorna uma marca baseada no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(
            @Parameter(description = "ID da marca a ser buscada", example = "12345") 
            @PathVariable String id) {
        Marca marca = service.findById(id);
        return ResponseEntity.ok(marca);
    }

    // Listar todas as marcas
    @Operation(summary = "Listar todas as marcas", description = "Retorna uma lista de todas as marcas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Marca>> findAll() {
        List<Marca> marcas = service.findAll();
        return ResponseEntity.ok(marcas);
    }

    // Atualizar uma marca
    @Operation(summary = "Atualizar uma marca", description = "Atualiza as informações de uma marca existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(
            @Parameter(description = "ID da marca a ser atualizada", example = "12345") 
            @PathVariable String id,
            @RequestBody Marca marcaToUpdate) {
        Marca updatedMarca = service.update(id, marcaToUpdate);
        return ResponseEntity.ok(updatedMarca);
    }

    // Deletar uma marca
    @Operation(summary = "Deletar uma marca", description = "Remove uma marca do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(
            @Parameter(description = "ID da marca a ser deletada", example = "12345") 
            @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}