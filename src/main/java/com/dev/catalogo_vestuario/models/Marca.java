package com.dev.catalogo_vestuario.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity(name = "tb_marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String nome;

}
