package com.sop.Financeiro.dtos;

import com.sop.Financeiro.model.Despesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDTO {
    private long id;
    private String numeroProtocolo;
    private String tipoDespesa;
    private LocalDateTime dataProtocolo;
    private LocalDate dataVencimento;
    private String credor;
    private String descricao;
    private BigDecimal valorDespesa;

    public static DespesaDTO fromEntity(Despesa despesa) {
        return new DespesaDTO(
                despesa.getId(),
                despesa.getNumeroProtocolo(),
                despesa.getTipoDespesa(),
                despesa.getDataProtocolo(),
                despesa.getDataVencimento(),
                despesa.getCredor(),
                despesa.getDescricao(),
                despesa.getValorDespesa()
        );
    }
}
