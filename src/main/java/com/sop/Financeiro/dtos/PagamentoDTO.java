package com.sop.Financeiro.dtos;

import com.sop.Financeiro.model.Pagamento;
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
public class PagamentoDTO {
    private long id;
    private String numeroPagamento;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String observacao;
    private Long empenhoId; // Referência ao Empenho relacionado

    public static PagamentoDTO fronEntity(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getNumeroPagamento(),
                pagamento.getDataPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getObservacao(),
                pagamento.getId()
        );
    }
}