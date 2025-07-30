package com.sop.Financeiro.service;

import com.sop.Financeiro.dtos.DespesaDTO;
import com.sop.Financeiro.model.Despesa;
import com.sop.Financeiro.repository.DespesaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public List<DespesaDTO> listarTodas() {
        return despesaRepository.findAll().stream()
                .map(DespesaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public DespesaDTO buscarPorId(Long id) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + id));
        return  DespesaDTO.fromEntity(despesa);
    }

    @Transactional
    public DespesaDTO criar(DespesaDTO dto) {
        if (despesaRepository.existsByNumeroProtocolo(dto.getNumeroProtocolo())) {
            throw new IllegalArgumentException("Número de protocolo já cadastrado.");
        }

        Despesa novaDespesa = new Despesa();
        novaDespesa.setNumeroProtocolo(dto.getNumeroProtocolo());
        novaDespesa.setTipoDespesa(dto.getTipoDespesa());
        novaDespesa.setDataProtocolo(dto.getDataProtocolo());
        novaDespesa.setDataVencimento(dto.getDataVencimento());
        novaDespesa.setCredor(dto.getCredor());
        novaDespesa.setDescricao(dto.getDescricao());
        novaDespesa.setValorDespesa(dto.getValorDespesa());

        return  DespesaDTO.fromEntity(despesaRepository.save(novaDespesa));
    }

    @Transactional
    public DespesaDTO atualizar(Long id, DespesaDTO dto) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + id));

        if (!despesa.getNumeroProtocolo().equals(dto.getNumeroProtocolo()) &&
                despesaRepository.existsByNumeroProtocolo(dto.getNumeroProtocolo())) {
            throw new IllegalArgumentException("Número de protocolo já está em uso.");
        }

        despesa.setNumeroProtocolo(dto.getNumeroProtocolo());
        despesa.setTipoDespesa(dto.getTipoDespesa());
        despesa.setDataProtocolo(dto.getDataProtocolo());
        despesa.setDataVencimento(dto.getDataVencimento());
        despesa.setCredor(dto.getCredor());
        despesa.setDescricao(dto.getDescricao());
        despesa.setValorDespesa(dto.getValorDespesa());

        return DespesaDTO.fromEntity(despesaRepository.save(despesa));
    }

    @Transactional
    public void excluir(Long id) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Despesa não encontrada com ID: " + id));

        if (despesa.getEmpenhos() != null && !despesa.getEmpenhos().isEmpty()) {
            throw new IllegalStateException("Não é possível excluir uma Despesa com Empenhos associados.");
        }

        despesaRepository.delete(despesa);
    }
}
