package com.sop.Financeiro.service;


import com.sop.Financeiro.dtos.EmpenhoDTO;
import com.sop.Financeiro.model.Despesa;
import com.sop.Financeiro.model.Empenho;
import com.sop.Financeiro.repository.DespesaRepository;
import com.sop.Financeiro.repository.EmpenhoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpenhoService {

    private final EmpenhoRepository empenhoRepository;
    private final DespesaRepository despesaRepository;

    public List<EmpenhoDTO> listarTodos() {
        return empenhoRepository.findAll().stream()
                .map(EmpenhoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public EmpenhoDTO buscarPorId(Long id) {
        Empenho empenho = empenhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empenho não encontrado com ID: " + id));
        return  EmpenhoDTO.fromEntity(empenho);
    }

    @Transactional
    public EmpenhoDTO criar(EmpenhoDTO dto) {
        if (empenhoRepository.existsByNumeroEmpenho(dto.getNumeroEmpenho())) {
            throw new IllegalArgumentException("Número de empenho já cadastrado.");
        }

        Despesa despesa = despesaRepository.findById(dto.getDespesaId())
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + dto.getDespesaId()));

        Empenho empenho = new Empenho();
        empenho.setNumeroEmpenho(dto.getNumeroEmpenho());
        empenho.setDataEmpenho(dto.getDataEmpenho());
        empenho.setValorEmpenho(dto.getValorEmpenho());
        empenho.setObservacao(dto.getObservacao());
        empenho.setDespesa(despesa);

        return EmpenhoDTO.fromEntity(empenhoRepository.save(empenho));
    }

    @Transactional
    public EmpenhoDTO atualizar(Long id, EmpenhoDTO dto) {
        Empenho empenho = empenhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empenho não encontrado com ID: " + id));

        if (!empenho.getNumeroEmpenho().equals(dto.getNumeroEmpenho()) &&
                empenhoRepository.existsByNumeroEmpenho(dto.getNumeroEmpenho())) {
            throw new IllegalArgumentException("Número de empenho já está em uso.");
        }

        empenho.setNumeroEmpenho(dto.getNumeroEmpenho());
        empenho.setDataEmpenho(dto.getDataEmpenho());
        empenho.setValorEmpenho(dto.getValorEmpenho());
        empenho.setObservacao(dto.getObservacao());

        return EmpenhoDTO.fromEntity(empenhoRepository.save(empenho));
    }

    @Transactional
    public void excluir(Long id) {
        Empenho empenho = empenhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empenho não encontrado com ID: " + id));

        if (empenho.getPagamentos() != null && !empenho.getPagamentos().isEmpty()) {
            throw new IllegalStateException("Não é possível excluir um Empenho com Pagamentos associados.");
        }

        empenhoRepository.delete(empenho);
    }
}
