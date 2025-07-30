CREATE TABLE despesa (
    id SERIAL PRIMARY KEY,
    numero_protocolo VARCHAR(255) UNIQUE NOT NULL,
    tipo_despesa VARCHAR(255),
    data_protocolo TIMESTAMP,
    data_vencimento DATE,
    credor VARCHAR(255),
    descricao TEXT,
    valor_despesa NUMERIC(15, 2)
);

CREATE TABLE empenho (
    id SERIAL PRIMARY KEY,
    numero_empenho VARCHAR(255) UNIQUE NOT NULL,
    data_empenho DATE,
    valor_empenho NUMERIC(15, 2),
    observacao TEXT,
    despesa_id INTEGER REFERENCES despesa(id) ON DELETE CASCADE
);

CREATE TABLE pagamento (
    id SERIAL PRIMARY KEY,
    numero_pagamento VARCHAR(255) UNIQUE NOT NULL,
    data_pagamento DATE,
    valor_pagamento NUMERIC(15, 2),
    observacao TEXT,
    empenho_id INTEGER REFERENCES empenho(id) ON DELETE CASCADE
);
