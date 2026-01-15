# Kafka Consumer - Contas a Pagar

Este projeto é um consumidor Kafka desenvolvido em Java 17 que recebe mensagens de um tópico Kafka e insere os registros no banco de dados PostgreSQL na tabela `contas_pagar`.

## 📋 Pré-requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose (para ambiente local)

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Kafka** - Para consumir mensagens do Kafka
- **Spring Data JPA** - Para persistência no banco de dados
- **PostgreSQL** - Banco de dados
- **Lombok** - Para reduzir código boilerplate
- **Jackson** - Para deserialização de JSON

## 📊 Estrutura da Tabela

```sql
CREATE TABLE contas_pagar (
    id BIGSERIAL PRIMARY KEY,
    centro_custo_id BIGINT NOT NULL,
    valor_previsto DECIMAL(15,2),
    valor_pago DECIMAL(15,2),
    status VARCHAR(50),
    data_conta DATE,
    informacao VARCHAR(500)
);
```

## 🔧 Configuração

### 1. Subir o ambiente local (Kafka + PostgreSQL)

```bash
docker-compose up -d
```

Isso irá iniciar:
- Zookeeper (porta 2181)
- Kafka (porta 9092)
- PostgreSQL (porta 5432)

### 2. Configurar o application.properties

O arquivo `src/main/resources/application.properties` já contém as configurações padrão:

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=contas-pagar-consumer-group
kafka.topic.contas-pagar=contas-pagar-topic

spring.datasource.url=jdbc:postgresql://localhost:5432/contaspagar
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 3. Compilar o projeto

```bash
mvn clean install
```

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

Ou executar o JAR:

```bash
java -jar target/kafka-consumer-contas-pagar-1.0.0.jar
```

## 📨 Formato da Mensagem Kafka

O consumidor espera mensagens em formato JSON com a seguinte estrutura:

```json
{
  "centro_custo_id": 1,
  "valor_previsto": 1500.50,
  "valor_pago": 1500.50,
  "status": "PAGO",
  "data_conta": "2024-01-15",
  "informacao": "Pagamento de fornecedor XYZ"
}
```

## 🧪 Testando o Consumidor

### 1. Criar o tópico Kafka

```bash
docker exec -it kafka kafka-topics --create \
  --topic contas-pagar-topic \
  --bootstrap-server localhost:9092 \
  --partitions 1 \
  --replication-factor 1
```

### 2. Produzir mensagens de teste

```bash
docker exec -it kafka kafka-console-producer \
  --topic contas-pagar-topic \
  --bootstrap-server localhost:9092
```

Cole a mensagem JSON de exemplo:

```json
{"centro_custo_id": 100, "valor_previsto": 2500.00, "valor_pago": 2500.00, "status": "PAGO", "data_conta": "2024-01-15", "informacao": "Teste de pagamento"}
```

### 3. Verificar no banco de dados

```bash
docker exec -it postgres psql -U postgres -d contaspagar
```

```sql
SELECT * FROM contas_pagar;
```

## 🏗️ Estrutura do Projeto

```
src/main/java/com/danielsmanioto/kafka/consumer/
├── KafkaConsumerApplication.java    # Classe principal
├── config/
│   └── KafkaConsumerConfig.java     # Configuração do Kafka
├── consumer/
│   └── ContasPagarConsumer.java     # Consumidor Kafka
├── dto/
│   └── ContasPagarDTO.java          # DTO para mensagens
├── entity/
│   └── ContasPagar.java             # Entidade JPA
└── repository/
    └── ContasPagarRepository.java   # Repositório JPA
```

## 📝 Funcionalidades

- ✅ Consumo de mensagens do tópico Kafka
- ✅ Deserialização automática de JSON para objetos Java
- ✅ Persistência no banco de dados PostgreSQL
- ✅ Confirmação manual de mensagens (manual commit)
- ✅ Tratamento de erros com logs
- ✅ Reprocessamento em caso de falha

## 🔍 Logs

A aplicação registra:
- Mensagens recebidas do Kafka
- Registros inseridos no banco de dados (com ID)
- Erros durante o processamento

## 🛑 Parar o ambiente

```bash
docker-compose down
```

Para remover os volumes também:

```bash
docker-compose down -v
```

## 📄 Licença

Este projeto está sob a licença MIT.