CREATE TABLE IF NOT EXISTS musica (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    artista TEXT NOT NULL,
    genero TEXT,
    duracao INTEGER NOT NULL,
    data_lancamento TEXT NOT NULL,
    data_cadastro TEXT DEFAULT (date('now'))
);
