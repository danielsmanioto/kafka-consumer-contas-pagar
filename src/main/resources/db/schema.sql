-- Tabela contas_pagar
CREATE TABLE IF NOT EXISTS contas_pagar (
    id BIGSERIAL PRIMARY KEY,
    centro_custo_id BIGINT NOT NULL,
    valor_previsto DECIMAL(15,2),
    valor_pago DECIMAL(15,2),
    status VARCHAR(50),
    data_conta DATE,
    informacao VARCHAR(500)
);

-- Índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_contas_pagar_centro_custo ON contas_pagar(centro_custo_id);
CREATE INDEX IF NOT EXISTS idx_contas_pagar_status ON contas_pagar(status);
CREATE INDEX IF NOT EXISTS idx_contas_pagar_data_conta ON contas_pagar(data_conta);
