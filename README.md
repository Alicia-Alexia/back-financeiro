# 💰 Sistema de Controle Financeiro - Back-End

Este é o back-end de um sistema de controle financeiro para instituições públicas, com foco em gerenciar **Despesas**, **Empenhos** e **Pagamentos**. O projeto foi desenvolvido com **Spring Boot** e utiliza **PostgreSQL** como banco de dados, com versionamento via **Flyway**.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway (migrations do banco)
- Lombok
- Maven

---

## 📦 Estrutura do Projeto
src/
├── controller # Endpoints REST
├── service # Regras de negócio
├── dto # Objetos de Transferência de Dados (DTOs)
├── model # Entidades JPA
├── repository # Interfaces de acesso a dados
└── config # Configurações (ex: CORS)


---

## 📚 Entidades e Relacionamentos

### 🔸 Despesa

- `id`: Long
- `numeroProtocolo`: String
- `tipoDespesa`: String
- `dataProtocolo`: LocalDate
- `dataVencimento`: LocalDate
- `credor`: String
- `descricao`: String
- `valorDespesa`: BigDecimal

### 🔸 Empenho

- `id`: Long
- `numeroEmpenho`: String
- `dataEmpenho`: LocalDate
- `valorEmpenho`: BigDecimal
- `observacao`: String
- `despesa`: Despesa (ManyToOne)

### 🔸 Pagamento

- `id`: Long
- `numeroPagamento`: String
- `dataPagamento`: LocalDate
- `valorPagamento`: BigDecimal
- `observacao`: String
- `empenho`: Empenho (ManyToOne)

---

## 🗺️ Diagrama Entidade-Relacionamento

![Diagrama ERD](docs/Diagrama_Financeiro.png)

---

## 🔧 Configurações Iniciais

1. Clone o projeto:
   ```bash
   git clone https://github.com/Alicia-Alexia/back-financeiro.git
2. Configure o banco de dados PostgreSQL com as credenciais em application.properties:
   spring.datasource.url=jdbc:postgresql://localhost:5432/financeiro
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
3. Execute o projeto via sua IDE ou:
   ./mvnw spring-boot:run

📌 Endpoints
| Entidade  | Método | Endpoint                      | Descrição                       |
| --------- | ------ | ----------------------------- | ------------------------------- |
| Despesa   | GET    | `/api/despesa`                | Listar todas as despesas        |
| Despesa   | POST   | `/api/despesa`                | Criar uma nova despesa          |
| Empenho   | GET    | `/api/empenho`                | Listar todos os empenhos        |
| Empenho   | POST   | `/api/empenho`                | Criar novo empenho para despesa |
| Pagamento | GET    | `/api/pagamento`              | Listar todos os pagamentos      |
| Pagamento | GET    | `/api/pagamento/empenho/{id}` | Listar pagamentos por empenho   |
| Pagamento | POST   | `/api/pagamento`              | Criar novo pagamento   

🧪 Dados de Teste
Use os seguintes arquivos JSON para testar a aplicação com dados reais simulados. Você pode utilizar ferramentas como Postman ou curl para enviar os dados às rotas da API.

Entidade	Arquivo JSON	Observação
Despesa	despesas.json	Cadastrar antes de empenhos
Empenho	empenhos.json	Precisa do despesaId correspondente
Pagamento	pagamentos.json	Precisa do empenhoId correspondente
✅ Recomenda-se importar os dados na ordem: Despesas → Empenhos → Pagamentos|

🗃️ Migrations com Flyway
As migrations estão localizadas em:
src/main/resources/db/migration
Elas estão seguindo o padrão: V1__create_despesa_empenho_pagamento.sql](src/main/resources/db/migration/V1__create_despesa_empenho_pagamento.sql

## ⚠️ Pontos de Melhoria

Este projeto está em desenvolvimento inicial e pode ser expandido com as seguintes melhorias:

-  Implementação de **testes unitários e de integração**.
-  Configuração de **deploy automatizado**.
-  Melhoria na **tratativa de erros e validações personalizadas**.
-  Criação de uma **documentação completa da API** .
-  Inclusão de **perfis de ambiente** (`dev`, `prod`) com configurações separadas.





