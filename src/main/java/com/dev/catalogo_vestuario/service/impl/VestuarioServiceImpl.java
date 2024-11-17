package com.dev.catalogo_vestuario.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dev.catalogo_vestuario.models.Categoria;
import com.dev.catalogo_vestuario.models.Marca;
import com.dev.catalogo_vestuario.models.Vestuario;
import com.dev.catalogo_vestuario.repositories.CategoriaRepository;
import com.dev.catalogo_vestuario.repositories.MarcaRepository;
import com.dev.catalogo_vestuario.repositories.VestuarioRepository;
import com.dev.catalogo_vestuario.service.VestuarioService;

@Service
public class VestuarioServiceImpl implements VestuarioService {

    private static final Logger logger = LoggerFactory.getLogger(VestuarioServiceImpl.class);

    private final VestuarioRepository vestRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository catRepository;

    public VestuarioServiceImpl(VestuarioRepository vestRepository, MarcaRepository marcaRepository,
                                 CategoriaRepository catRepository) {
        this.vestRepository = vestRepository;
        this.marcaRepository = marcaRepository;
        this.catRepository = catRepository;
    }

    @Override
    public Vestuario findById(String uuid) {
        logger.info("Buscando vestuário com ID: {}", uuid);
        return vestRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Vestuário com ID " + uuid + " não encontrado!"));
    }

    @Override
    public Vestuario addVestuario(Vestuario vestuario) {
        logger.info("Adicionando novo vestuário: {}", vestuario);
        Vestuario vestuarioParaAdd = new Vestuario(
                vestuario.getNome(),
                vestuario.getDescricao(),
                vestuario.getUrlDaImagem()
        );
        return vestRepository.save(vestuarioParaAdd);
    }

    @Override
    public List<Vestuario> findAll() {
        logger.info("Buscando todos os vestuários.");
        return vestRepository.findAll();
    }

    @Override
    public Vestuario update(String id, Vestuario vestuarioToUpdate) {
        logger.info("Atualizando vestuário com ID: {}", id);

        Vestuario existingVestuario = vestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vestuário com ID " + id + " não encontrado!"));

        // Atualiza os campos necessários
        existingVestuario.setNome(vestuarioToUpdate.getNome());
        existingVestuario.setDescricao(vestuarioToUpdate.getDescricao());
        existingVestuario.setUrlDaImagem(vestuarioToUpdate.getUrlDaImagem());

        Vestuario updatedVestuario = vestRepository.save(existingVestuario);
        logger.info("Vestuário com ID {} atualizado com sucesso.", id);
        return updatedVestuario;
    }

    @Override
    public void delete(String uuid) {
        logger.info("Deletando vestuário com ID: {}", uuid);

        Vestuario existingVestuario = vestRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Vestuário com ID " + uuid + " não encontrado!"));

        vestRepository.delete(existingVestuario);
        logger.info("Vestuário com ID {} deletado com sucesso.", uuid);
    }

    @Override
    public Vestuario addMarca(String idVestuario, String idMarca) {
        logger.info("Associando marca {} ao vestuário {}", idMarca, idVestuario);

        Vestuario vestuario = vestRepository.findById(idVestuario)
                .orElseThrow(() -> new NoSuchElementException("Vestuário com ID " + idVestuario + " não encontrado!"));
        Marca marca = marcaRepository.findById(idMarca)
                .orElseThrow(() -> new NoSuchElementException("Marca com ID " + idMarca + " não encontrada!"));

        vestuario.addMarca(marca);
        Vestuario updatedVestuario = vestRepository.save(vestuario);

        logger.info("Marca {} associada ao vestuário {} com sucesso.", idMarca, idVestuario);
        return updatedVestuario;
    }

    @Override
    public Vestuario addCategoria(String idVestuario, String idCategoria) {
        logger.info("Associando categoria {} ao vestuário {}", idCategoria, idVestuario);

        Vestuario vestuario = vestRepository.findById(idVestuario)
                .orElseThrow(() -> new NoSuchElementException("Vestuário com ID " + idVestuario + " não encontrado!"));
        Categoria categoria = catRepository.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("Categoria com ID " + idCategoria + " não encontrada!"));

        vestuario.addCategoria(categoria);
        Vestuario updatedVestuario = vestRepository.save(vestuario);

        logger.info("Categoria {} associada ao vestuário {} com sucesso.", idCategoria, idVestuario);
        return updatedVestuario;
    }
}