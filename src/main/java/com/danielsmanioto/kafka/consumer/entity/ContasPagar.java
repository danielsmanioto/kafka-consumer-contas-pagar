package com.danielsmanioto.kafka.consumer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
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

    @Column(name = "informacao_pagamento", length = 500)
    private String informacao;

    public ContasPagar() {
    }

    public ContasPagar(Long id, Long centroCustoId, BigDecimal valorPrevisto, BigDecimal valorPago,
                       String status, LocalDate dataConta, String informacao) {
        this.id = id;
        this.centroCustoId = centroCustoId;
        this.valorPrevisto = valorPrevisto;
        this.valorPago = valorPago;
        this.status = status;
        this.dataConta = dataConta;
        this.informacao = informacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Long centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataConta() {
        return dataConta;
    }

    public void setDataConta(LocalDate dataConta) {
        this.dataConta = dataConta;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long centroCustoId;
        private BigDecimal valorPrevisto;
        private BigDecimal valorPago;
        private String status;
        private LocalDate dataConta;
        private String informacao;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder centroCustoId(Long centroCustoId) {
            this.centroCustoId = centroCustoId;
            return this;
        }

        public Builder valorPrevisto(BigDecimal valorPrevisto) {
            this.valorPrevisto = valorPrevisto;
            return this;
        }

        public Builder valorPago(BigDecimal valorPago) {
            this.valorPago = valorPago;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder dataConta(LocalDate dataConta) {
            this.dataConta = dataConta;
            return this;
        }

        public Builder informacao(String informacao) {
            this.informacao = informacao;
            return this;
        }

        public ContasPagar build() {
            return new ContasPagar(id, centroCustoId, valorPrevisto, valorPago, status, dataConta, informacao);
        }
    }
}
