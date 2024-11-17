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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/vestuarios")
@Tag(name = "Catalogo_Vestuário - Vestuario", description = "API para gerenciamento de vestuários.")
public class VestuarioController {

    private static final Logger logger = LoggerFactory.getLogger(VestuarioController.class);

    private final VestuarioService service;

    @Autowired
    public VestuarioController(VestuarioService service) {
        this.service = service;
    }

    // Criar um novo vestuário
    @Operation(summary = "Adicionar um novo vestuário", description = "Cria um novo vestuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vestuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PostMapping
    public ResponseEntity<Vestuario> addVestuario(@RequestBody Vestuario vestuario) {
        logger.info("Recebendo solicitação para adicionar novo vestuário: {}", vestuario);
        Vestuario novoVestuario = service.addVestuario(vestuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVestuario);
    }

    // Buscar vestuário por ID
    @Operation(summary = "Buscar uma roupa por ID", description = "Retorna uma vestimenta baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vestuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Vestuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vestuario> findById(
            @Parameter(description = "ID do vestuário a ser buscado", example = "12345") @PathVariable String id) {
        logger.info("Buscando vestuário com ID: {}", id);
        Vestuario vestuario = service.findById(id);
        return ResponseEntity.ok(vestuario);
    }

    // Listar todos os vestuários
    @Operation(summary = "Listar todos os vestuários", description = "Retorna uma lista de todos os vestuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vestuários retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Vestuario>> findAll() {
        logger.info("Buscando todos os vestuários.");
        List<Vestuario> vestuarios = service.findAll();
        return ResponseEntity.ok(vestuarios);
    }

    // Adicionar marca ao vestuário
    @Operation(summary = "Adicionar uma marca ao vestuário", description = "Associa uma marca existente a um vestuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca associada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vestuário ou marca não encontrados")
    })
    @PatchMapping("/{idVestuario}/marca/{idMarca}")
    public ResponseEntity<Vestuario> addMarca(
            @Parameter(description = "ID do vestuário", example = "12345") @PathVariable String idVestuario,
            @Parameter(description = "ID da marca", example = "67890") @PathVariable String idMarca) {
        logger.info("Adicionando marca {} ao vestuário {}", idMarca, idVestuario);
        Vestuario updatedVestuario = service.addMarca(idVestuario, idMarca);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Adicionar categoria ao vestuário
    @Operation(summary = "Adicionar uma categoria ao vestuário", description = "Associa uma categoria existente a um vestuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria associada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vestuário ou categoria não encontrados")
    })
    @PatchMapping("/{idVestuario}/categoria/{idCategoria}")
    public ResponseEntity<Vestuario> addCategoria(
            @Parameter(description = "ID do vestuário", example = "12345") @PathVariable String idVestuario,
            @Parameter(description = "ID da categoria", example = "67890") @PathVariable String idCategoria) {
        logger.info("Adicionando categoria {} ao vestuário {}", idCategoria, idVestuario);
        Vestuario updatedVestuario = service.addCategoria(idVestuario, idCategoria);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Atualizar vestuário
    @Operation(summary = "Atualizar um vestuário", description = "Atualiza as informações de um vestuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vestuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vestuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vestuario> updateVestuario(
            @Parameter(description = "ID do vestuário", example = "12345") @PathVariable String id,
            @RequestBody Vestuario vestuario) {
        logger.info("Atualizando vestuário com ID: {}", id);
        Vestuario updatedVestuario = service.update(id, vestuario);
        return ResponseEntity.ok(updatedVestuario);
    }

    // Deletar vestuário
    @Operation(summary = "Deletar um vestuário", description = "Remove um vestuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vestuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vestuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVestuario(
            @Parameter(description = "ID do vestuário a ser deletado", example = "12345") @PathVariable String id) {
        logger.info("Deletando vestuário com ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}