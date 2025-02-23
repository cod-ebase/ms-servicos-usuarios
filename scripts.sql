-- Tabela Usuario
CREATE TABLE Usuario (
    usuario_id SERIAL PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    perfil VARCHAR(50),
    sexo CHAR(1),
    foto TEXT,
    data_de_nascimento DATE,
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Localizacao
CREATE TABLE Localizacao (
    localicao_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    endereco VARCHAR(100),
    cidade VARCHAR(20),
    pais VARCHAR(20),
    latitude DECIMAL(9, 6),
    longitude DECIMAL(9, 6),
    numero VARCHAR(20),
    complementar VARCHAR(10),
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Produto (Hotel)
CREATE TABLE Produto (
    produto_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    tipo VARCHAR(50),
    preco DECIMAL(10, 2),
    categoria VARCHAR(100),
    dimensao VARCHAR(50),
    usuario_id_parceiro INT REFERENCES Usuario(usuario_id),
    localicao_id INT REFERENCES Localizacao(localicao_id),
    numero_de_ocupantes INT,
    esta_disponivel BOOLEAN DEFAULT TRUE,
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Produto_Foto
CREATE TABLE Produto_Foto (
    id_produto INT REFERENCES Produto(produto_id),
    foto TEXT,
    PRIMARY KEY (id_produto, foto)
);

-- Tabela Servico
CREATE TABLE Servico (
    servico_id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);

-- Tabela Produto_Servico
CREATE TABLE Produto_Servico (
    produto_id INT REFERENCES Produto(produto_id),
    servico_id INT REFERENCES Servico(servico_id),
    PRIMARY KEY (produto_id, servico_id)
);

-- Tabela Pagamento
CREATE TABLE Pagamento (
    pagamento_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cartao VARCHAR(16) NOT NULL,
    data_valida DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL
);

-- Tabela Reservas
CREATE TABLE Reservas (
    reserva_id SERIAL PRIMARY KEY,
    data_de_entrada DATE NOT NULL,
    data_de_saida DATE NOT NULL,
    numeros_de_ocupantes INT NOT NULL,
    tipo_de_ocupante VARCHAR(50) NOT NULL,
    numero_de_dias INT NOT NULL,
    usuario_id INT REFERENCES Usuario(usuario_id),
    produto_id INT REFERENCES Produto(produto_id),
    pagamento_id INT REFERENCES Pagamento(pagamento_id),
    observacao TEXT,
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);