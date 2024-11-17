package com.dev.catalogo_vestuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.catalogo_vestuario.models.Categoria;
import com.dev.catalogo_vestuario.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Catalogo_Vestuário - Categoria", description = "API para gerenciamento de categorias.")
public class CategoriaController {

    private final CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // Adicionar uma nova categoria
    @Operation(summary = "Adicionar uma nova categoria", description = "Cria uma nova categoria no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PostMapping
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = service.addCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    // Buscar categoria por ID
    @Operation(summary = "Buscar uma categoria por ID", description = "Retorna uma categoria com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(
            @Parameter(description = "ID da categoria a ser buscada", example = "12345") 
            @PathVariable String id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    // Listar todas as categorias
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = service.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Atualizar uma categoria
    @Operation(summary = "Atualizar uma categoria", description = "Atualiza as informações de uma categoria existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(
            @Parameter(description = "ID da categoria a ser atualizada", example = "12345") 
            @PathVariable String id,
            @RequestBody Categoria categoriaToUpdate) {
        Categoria updatedCategoria = service.update(id, categoriaToUpdate);
        return ResponseEntity.ok(updatedCategoria);
    }

    // Deletar uma categoria
    @Operation(summary = "Deletar uma categoria", description = "Remove uma categoria do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(
            @Parameter(description = "ID da categoria a ser deletada", example = "12345") 
            @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}