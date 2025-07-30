package com.sop.Financeiro.dtos;

import com.sop.Financeiro.model.Empenho;
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
public class EmpenhoDTO {
    private long id;
    private String numeroEmpenho;
    private LocalDate dataEmpenho;
    private BigDecimal valorEmpenho;
    private String observacao;
    private Long despesaId; // Referência Despesa relacionada

    public static EmpenhoDTO fromEntity(Empenho empenho) {
        return new EmpenhoDTO(
                empenho.getId(),
                empenho.getNumeroEmpenho(),
                empenho.getDataEmpenho(),
                empenho.getValorEmpenho(),
                empenho.getObservacao(),
                empenho.getId()
        );
    }
}
