package com.danielsmanioto.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonAlias("data")
    private LocalDate dataConta;

    @JsonAlias("informacao_pagamento")
    private String informacao;

    public ContasPagarDTO() {
    }

    public ContasPagarDTO(Long id, Long centroCustoId, BigDecimal valorPrevisto, BigDecimal valorPago,
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
}
