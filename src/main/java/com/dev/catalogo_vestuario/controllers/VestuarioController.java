package com.dev.catalogo_vestuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.catalogo_vestuario.models.Vestuario;
import com.dev.catalogo_vestuario.service.VestuarioService;

@RestController
@RequestMapping("/vestuarios")
public class VestuarioController {
    private VestuarioService service;

    @Autowired
    public VestuarioController(VestuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Vestuario> addVestuario(@RequestBody Vestuario vestuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addVestuario(vestuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vestuario> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vestuario>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    public ResponseEntity<Vestuario> addMarca(@PathVariable String idVestuario, @PathVariable String idMarca){
        return ResponseEntity.ok(service.addMarca(idVestuario,idMarca));
    }

    

}
