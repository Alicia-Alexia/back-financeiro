package com.sop.Financeiro.service;

import com.sop.Financeiro.dtos.PagamentoDTO;
import com.sop.Financeiro.model.Empenho;
import com.sop.Financeiro.model.Pagamento;
import com.sop.Financeiro.repository.EmpenhoRepository;
import com.sop.Financeiro.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final EmpenhoRepository empenhoRepository;

    public List<PagamentoDTO> listarTodos() {
        return pagamentoRepository.findAll().stream()
                .map(PagamentoDTO::fronEntity)
                .collect(Collectors.toList());
    }

    public PagamentoDTO buscarPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + id));
        return  PagamentoDTO.fronEntity(pagamento);
    }

    @Transactional
    public PagamentoDTO criar(PagamentoDTO dto) {
        if (pagamentoRepository.existsByNumeroPagamento(dto.getNumeroPagamento())) {
            throw new IllegalArgumentException("Número de pagamento já cadastrado.");
        }

        Empenho empenho = empenhoRepository.findById(dto.getEmpenhoId())
                .orElseThrow(() -> new EntityNotFoundException("Empenho não encontrado com ID: " + dto.getEmpenhoId()));

        Pagamento pagamento = new Pagamento();
        pagamento.setNumeroPagamento(dto.getNumeroPagamento());
        pagamento.setDataPagamento(dto.getDataPagamento());
        pagamento.setValorPagamento(dto.getValorPagamento());
        pagamento.setObservacao(dto.getObservacao());
        pagamento.setEmpenho(empenho);

        return PagamentoDTO.fronEntity(pagamentoRepository.save(pagamento));
    }

    @Transactional
    public PagamentoDTO atualizar(Long id, PagamentoDTO dto) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + id));

        if (!pagamento.getNumeroPagamento().equals(dto.getNumeroPagamento()) &&
                pagamentoRepository.existsByNumeroPagamento(dto.getNumeroPagamento())) {
            throw new IllegalArgumentException("Número de pagamento já está em uso.");
        }

        pagamento.setNumeroPagamento(dto.getNumeroPagamento());
        pagamento.setDataPagamento(dto.getDataPagamento());
        pagamento.setValorPagamento(dto.getValorPagamento());
        pagamento.setObservacao(dto.getObservacao());

        return PagamentoDTO.fronEntity(pagamentoRepository.save(pagamento));
    }

    @Transactional
    public void excluir(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + id));
        pagamentoRepository.delete(pagamento);
    }

    public List<PagamentoDTO> buscarPorEmpenhoId(Long empenhoId) {
        List<Pagamento> pagamentos = pagamentoRepository.findByEmpenhoId(empenhoId);
        return pagamentos.stream()
                .map(PagamentoDTO::fronEntity)
                .toList();
    }
}

