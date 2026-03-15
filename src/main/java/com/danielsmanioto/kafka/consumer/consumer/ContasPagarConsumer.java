package com.danielsmanioto.kafka.consumer.consumer;

import com.danielsmanioto.kafka.consumer.dto.ContasPagarDTO;
import com.danielsmanioto.kafka.consumer.entity.ContasPagar;
import com.danielsmanioto.kafka.consumer.repository.ContasPagarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContasPagarConsumer {

    private static final Logger log = LoggerFactory.getLogger(ContasPagarConsumer.class);

    private final ContasPagarRepository repository;
    private final ObjectMapper objectMapper;

    public ContasPagarConsumer(ContasPagarRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.topic.contas-pagar}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message, Acknowledgment acknowledgment) {
        try {
            log.info("Mensagem recebida: {}", message);
            
            ContasPagarDTO dto = objectMapper.readValue(message, ContasPagarDTO.class);
            
            ContasPagar entity = ContasPagar.builder()
                .centroCustoId(dto.getCentroCustoId())
                .valorPrevisto(dto.getValorPrevisto())
                .valorPago(dto.getValorPago())
                .status(dto.getStatus())
                .dataConta(dto.getDataConta())
                .informacao(dto.getInformacao())
                .build();
            
            ContasPagar saved = repository.save(entity);
            log.info("Registro inserido no banco de dados com ID: {}", saved.getId());
            
            acknowledgment.acknowledge();
            
        } catch (Exception e) {
            log.error("Erro ao processar mensagem: {}", message, e);
            // Não faz acknowledge em caso de erro, permitindo reprocessamento
        }
    }
}
