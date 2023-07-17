CREATE TABLE usuarios
(
    nome character varying(16) COLLATE pg_catalog."default" NOT NULL,
    email character varying(32) COLLATE pg_catalog."default",
    senha TEXT
);

CREATE TABLE notes
(
    dono character varying(16) COLLATE pg_catalog."default" NOT NULL,
    titulo character varying(16) COLLATE pg_catalog."default" NOT NULL,
    conteudo TEXT
);