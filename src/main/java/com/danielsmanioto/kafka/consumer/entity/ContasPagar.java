package com.danielsmanioto.kafka.consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "centro_custo_id", nullable = false)
    private Long centroCustoId;

    @Column(name = "valor_previsto", precision = 15, scale = 2)
    private BigDecimal valorPrevisto;

    @Column(name = "valor_pago", precision = 15, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "data_conta")
    private LocalDate dataConta;

    @Column(name = "informacao", length = 500)
    private String informacao;
}
