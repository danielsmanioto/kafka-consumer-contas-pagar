package com.danielsmanioto.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagarDTO {

    private Long id;

    @JsonProperty("centro_custo_id")
    private Long centroCustoId;

    @JsonProperty("valor_previsto")
    private BigDecimal valorPrevisto;

    @JsonProperty("valor_pago")
    private BigDecimal valorPago;

    private String status;

    @JsonProperty("data_conta")
    private LocalDate dataConta;

    private String informacao;
}
