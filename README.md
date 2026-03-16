# Kafka Consumer - Contas a Pagar

> Consumer Kafka em Java/Spring Boot para leitura de eventos financeiros e persistência no PostgreSQL.

[![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Apache-Kafka-231F20?logo=apachekafka&logoColor=white)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?logo=postgresql&logoColor=white)](https://www.postgresql.org/)

---

## 📑 Sumário

- [Visão geral](#-visão-geral)
- [Infraestrutura oficial](#-infraestrutura-oficial)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Como executar](#-como-executar)
- [Configuração](#-configuração)
- [Formato da mensagem](#-formato-da-mensagem)
- [Teste local](#-teste-local)
- [Funcionalidades](#-funcionalidades)

---

## 🌐 Visão geral

Responsável por consumir mensagens de `contas-pagar-topic` e salvar os dados na tabela `contas_pagar`.

> Este consumer usa o **mesmo banco** do projeto `gerenciador-pessoal` (`gerenciador_pessoal`).

## 🔗 Infraestrutura oficial

Este projeto **não possui infraestrutura própria**. Utilize:

- [danielsmanioto/infra-gerenciador-pessoal](https://github.com/danielsmanioto/infra-gerenciador-pessoal)

## 🗂 Estrutura do projeto

```text
kafka-consumer-contas-pagar/
├── src/
│   ├── main/
│   │   ├── java/com/danielsmanioto/kafka/consumer/
│   │   └── resources/
│   └── test/
│       └── java/com/danielsmanioto/kafka/consumer/
├── pom.xml
└── README.md
```

## 🚀 Como executar

### 1) Pré-requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose

### 2) Subir infraestrutura centralizada

```bash
git clone https://github.com/danielsmanioto/infra-gerenciador-pessoal.git
cd infra-gerenciador-pessoal
docker compose up -d
```

### 3) Compilar e executar consumer

```bash
cd ../kafka-consumer-contas-pagar
mvn clean install
mvn spring-boot:run
```

Ou:

```bash
java -jar target/kafka-consumer-contas-pagar-1.0.0.jar
```

## ⚙️ Configuração

```properties
spring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
spring.kafka.consumer.group-id=${KAFKA_CONSUMER_GROUP_ID:contas-pagar-consumer-group}
kafka.topic.contas-pagar=${KAFKA_TOPIC_CONTAS_PAGAR:contas-pagar-topic}

spring.datasource.url=${GERENCIADOR_DB_URL:jdbc:postgresql://localhost:5432/gerenciador_pessoal}
spring.datasource.username=${GERENCIADOR_DB_USERNAME:gerenciador}
spring.datasource.password=${GERENCIADOR_DB_PASSWORD:gerenciador123}
```

## 📨 Formato da mensagem

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

## 🧪 Teste local

Produza uma mensagem manualmente:

```bash
docker exec -it infra-gerenciador-kafka kafka-console-producer \
  --topic contas-pagar-topic \
  --bootstrap-server localhost:9092
```

Consulte o banco:

```bash
docker exec -it infra-gerenciador-postgres-app psql -U gerenciador -d gerenciador_pessoal
```

```sql
SELECT * FROM contas_pagar;
```

## ✅ Funcionalidades

- Consumo contínuo de mensagens Kafka
- Persistência em PostgreSQL
- Confirmação manual de offset
- Logs para monitoramento de processamento

## 📄 Licença

Este projeto está sob a licença MIT.