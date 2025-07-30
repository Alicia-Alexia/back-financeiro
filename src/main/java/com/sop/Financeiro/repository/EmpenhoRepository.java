package com.sop.Financeiro.repository;

import com.sop.Financeiro.model.Empenho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmpenhoRepository extends JpaRepository<Empenho, Long> {
    boolean existsByNumeroEmpenho(String numeroEmpenho);
}
