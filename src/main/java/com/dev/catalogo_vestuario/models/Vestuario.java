package com.dev.catalogo_vestuario.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name= "tb_vestuario")
public class Vestuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String nome;
    private String descricao;
    private String urlDaImagem;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_vestuario_categoria",
        joinColumns = @JoinColumn(name= "vestuario_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_vestuario_marca",
        joinColumns = @JoinColumn(name= "vestuario_id"),
        inverseJoinColumns = @JoinColumn(name = "marca_id"))
    private List<Marca> marcas = new ArrayList<>();
    
    public Vestuario(String nome, String descricao, String urlDaImagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.urlDaImagem = urlDaImagem;
    }

    public void addCategoria(Categoria categoria){
        this.categorias.add(categoria);
    }

    public void addMarca(Marca marca){
        this.marcas.add(marca);
    } 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vestuario other = (Vestuario) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}
