# Kafka Consumer - Contas a Pagar

Consumidor Kafka em Java/Spring Boot responsável por receber eventos do tópico `contas-pagar-topic` e persisti-los no banco PostgreSQL de contas a pagar.

## 📋 Pré-requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose

## 🔗 Infraestrutura oficial

Este projeto **não mantém mais infraestrutura própria**.

Toda a infraestrutura local foi centralizada em:

- [danielsmanioto/infra-gerenciador-pessoal](https://github.com/danielsmanioto/infra-gerenciador-pessoal)

## 🚀 Como executar

### 1. Suba a infraestrutura centralizada

```bash
git clone https://github.com/danielsmanioto/infra-gerenciador-pessoal.git
cd infra-gerenciador-pessoal
docker compose up -d
```

### 2. Volte para este projeto e compile

```bash
cd ../kafka-consumer-contas-pagar
mvn clean install
```

### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

Ou via JAR:

```bash
java -jar target/kafka-consumer-contas-pagar-1.0.0.jar
```

## ⚙️ Configuração

O arquivo `src/main/resources/application.properties` usa variáveis de ambiente com fallback local:

```properties
spring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
spring.kafka.consumer.group-id=${KAFKA_CONSUMER_GROUP_ID:contas-pagar-consumer-group}
kafka.topic.contas-pagar=${KAFKA_TOPIC_CONTAS_PAGAR:contas-pagar-topic}

spring.datasource.url=${CONTASPAGAR_DB_URL:jdbc:postgresql://localhost:5433/contaspagar}
spring.datasource.username=${CONTASPAGAR_DB_USERNAME:postgres}
spring.datasource.password=${CONTASPAGAR_DB_PASSWORD:postgres}
```

## 📨 Formato da mensagem

O consumidor espera JSON com a seguinte estrutura:

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

## 🧪 Testando localmente

### 1. Produza uma mensagem no tópico

```bash
docker exec -it infra-gerenciador-kafka kafka-console-producer \
  --topic contas-pagar-topic \
  --bootstrap-server localhost:9092
```

### 2. Consulte o banco do consumer

```bash
docker exec -it infra-gerenciador-postgres-consumer psql -U postgres -d contaspagar
```

```sql
SELECT * FROM contas_pagar;
```

## 📝 Funcionalidades

- ✅ Consumo de mensagens do Kafka
- ✅ Persistência em PostgreSQL
- ✅ Commit manual de mensagens
- ✅ Tratamento de erros com logs
- ✅ Reprocessamento em caso de falha

## 🏗️ Estrutura do projeto

```
src/main/java/com/danielsmanioto/kafka/consumer/
├── KafkaConsumerApplication.java
├── config/
├── consumer/
├── dto/
├── entity/
└── repository/
```

## 📄 Licença

Este projeto está sob a licença MIT.