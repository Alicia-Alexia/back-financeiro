package com.sop.Financeiro.repository;

import com.sop.Financeiro.model.Empenho;
import com.sop.Financeiro.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmpenhoRepository extends JpaRepository<Empenho, Long> {
    Optional<Empenho> findByNumeroProtocolo(String numeroProtocolo);
    boolean existsByNumeroEmpenho(String numeroEmpenho);
}
