package com.sop.Financeiro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroPagamento;

    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "empenho_id")
    private Empenho empenho;
}
