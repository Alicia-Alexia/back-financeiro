package com.sop.Financeiro.repository;

import com.sop.Financeiro.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByEmpenhoId(Long empenhoId);
    boolean existsByNumeroPagamento(String numeroPagamento);
}
