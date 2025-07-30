package com.sop.Financeiro.repository;

import com.sop.Financeiro.model.Despesa;
import com.sop.Financeiro.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Optional<Despesa> findByNumeroProtocolo(String numeroProtocolo);
    boolean existsByNumeroProtocolo(String numeroProtocolo);
}

