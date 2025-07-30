📦 Backend - Sistema de Controle Financeiro
Este é o back-end da aplicação de controle financeiro desenvolvida em Spring Boot, com persistência de dados em PostgreSQL e uso de JPA (Hibernate). Ele expõe uma API RESTful para o gerenciamento das principais entidades financeiras da instituição:

Despesa

Empenho

Pagamento

🧱 Tecnologias Utilizadas
Java 21

Spring Boot 3

Spring Web

Spring Data JPA

Spring Validation

PostgreSQL

Lombok

Flyway (para migrações de banco)

Maven

src/
├── controller/     # Endpoints REST
├── service/        # Regras de negócio
├── repository/     # Acesso ao banco via JPA
├── model/          # Entidades JPA
├── dtos/           # Data Transfer Objects
└── migrations/     # Scripts de criação do banco (Flyway)

🧾 Entidades Principais
Despesa: representa o processo financeiro da instituição

Empenho: representa o compromisso da instituição com uma despesa

Pagamento: representa a efetivação de um empenho

As entidades possuem relacionamentos encadeados e as exclusões são protegidas por regras de integridade. Por exemplo, não é possível excluir um empenho com pagamentos associados.

🔄 Endpoints REST
Alguns exemplos:

Despesas
GET /api/despesa — Listar despesas

POST /api/despesa — Cadastrar despesa

PUT /api/despesa/{id} — Atualizar despesa

DELETE /api/despesa/{id} — Remover despesa

Empenhos
GET /api/empenho

POST /api/empenho

DELETE /api/empenho/{id} (só se não tiver pagamentos)

Pagamentos
GET /api/pagamento/empenho/{id} — Buscar pagamentos por empenho

🛠️ Como executar localmente
Pré-requisitos
Java 21

Maven

PostgreSQL

Configuração do banco (exemplo)
spring.datasource.url=jdbc:postgresql://localhost:5432/financeiro
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

