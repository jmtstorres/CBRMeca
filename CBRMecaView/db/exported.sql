--
-- File generated with SQLiteStudio v3.0.7 on qua nov 2 17:07:19 2016
--
-- Text encoding used: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Funcao
CREATE TABLE Funcao (
    id_funcao         INTEGER PRIMARY KEY AUTOINCREMENT
                              NOT NULL,
    str_funcao_verbo  STRING  NOT NULL,
    str_funcao_objeto STRING  NOT NULL
);


-- Table: Atributo
CREATE TABLE Atributo (
    id_atributo  INTEGER       PRIMARY KEY AUTOINCREMENT
                               NOT NULL
                               UNIQUE,
    str_atributo STRING (1024) NOT NULL
);


-- Table: Palavra
CREATE TABLE Palavra (
    id_palavra  INTEGER      PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
    str_palavra STRING (128) NOT NULL,
    next_val    BIGINT
);


-- Table: ItemAvaliacao
CREATE TABLE ItemAvaliacao (
    id_item             INTEGER      PRIMARY KEY AUTOINCREMENT
                                     NOT NULL,
    id_funcao_elementar INTEGER      NOT NULL,
    id_conceito         INTEGER      NOT NULL,
    int_peso            INTEGER      NOT NULL,
    str_atributo        STRING (256) NOT NULL,
    FOREIGN KEY (
        id_conceito
    )
    REFERENCES ImagemConceito,
    FOREIGN KEY (
        id_funcao_elementar
    )
    REFERENCES ImagemConceito,
    FOREIGN KEY (
        id_conceito
    )
    REFERENCES ImagemConceito
);


-- Table: Caso
CREATE TABLE Caso (
    id_caso         INTEGER       PRIMARY KEY AUTOINCREMENT
                                  UNIQUE
                                  NOT NULL,
    str_titulo      STRING (256)  UNIQUE
                                  NOT NULL,
    str_descricao   STRING (2048) NOT NULL,
    id_funcao_geral INTEGER       NOT NULL
                                  REFERENCES Funcao (id_funcao) ON DELETE CASCADE
                                                                ON UPDATE CASCADE,
    id_tipo         INTEGER       NOT NULL,
    int_ano         INTEGER       NOT NULL
);


-- Table: ID_SEQ
CREATE TABLE ID_SEQ (
    next_val BIGINT
);


-- Table: Solucao
CREATE TABLE Solucao (
    id_solucao  INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    id_caso     INTEGER REFERENCES Funcao
                        NOT NULL,
    id_funcao   INTEGER NOT NULL
                        REFERENCES Funcao (id_funcao),
    id_conceito INTEGER REFERENCES Conceito (id_conceito) 
                        NOT NULL
);


-- Table: ImagemConceito
CREATE TABLE ImagemConceito (
    id_imagem           INTEGER       PRIMARY KEY AUTOINCREMENT
                                      NOT NULL,
    dir_imagem_conceito STRING (2048) NOT NULL
);


-- Table: hibernate_sequences
CREATE TABLE hibernate_sequences (
    sequence_name VARCHAR NOT NULL,
    next_val      BIGINT,
    PRIMARY KEY (
        sequence_name
    )
);


-- Table: hibernate_sequence
CREATE TABLE hibernate_sequence (
    next_val BIGINT
);


-- Table: Conceito
CREATE TABLE Conceito (
    id_conceito   INTEGER      PRIMARY KEY AUTOINCREMENT,
    str_conceito  STRING (128) UNIQUE,
    img_conceito  INTEGER,
    desc_conceito STRING,
    FOREIGN KEY (
        img_conceito
    )
    REFERENCES ImagemConceito (id_imagem) 
);


-- Table: FuncaoCaso
CREATE TABLE FuncaoCaso (
    id_funcao_caso   INTEGER PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
    id_funcao_pai    INTEGER NOT NULL,
    id_funcao        INTEGER REFERENCES Funcao (id_funcao),
    bol_elementar    BOOLEAN NOT NULL,
    tipo_funcao      INTEGER DEFAULT (0),
    tipo_efeito      INTEGER DEFAULT (0),
    tipo_escopo      INTEGER DEFAULT (0),
    tipo_necessidade INTEGER DEFAULT (0) 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
