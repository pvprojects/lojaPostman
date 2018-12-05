CREATE TABLE IF NOT EXISTS credential (

	codigo 					BIGINT   PRIMARY KEY   NOT NULL,
	customerid      VARCHAR(50)   NOT NULL,
	login 					VARCHAR(50)   UNIQUE NOT NULL,
	password        VARCHAR(150)  NOT NULL,
	created_at 			TIMESTAMP,
	updated_at 			TIMESTAMP

);

CREATE SEQUENCE IF NOT EXISTS credential_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE IF NOT EXISTS permissao (
	codigo BIGINT PRIMARY KEY NOT NULL,
	descricao VARCHAR(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS permissao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE IF NOT EXISTS  credential_permissao (
	codigo_credential BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,
	PRIMARY KEY (codigo_credential, codigo_permissao),
	FOREIGN KEY (codigo_credential) REFERENCES credential(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
);

INSERT INTO credential (codigo, customerid, login, password)
values (nextval('credential_seq'), '12155fd-fsa24f-d21dfa54-fa4fasd4a5f', 'admin@loja.com',
				'$2a$10$AHoPbox7hYpN/tT8ujs1ZegwUtTX1AEWftYhoCJz046SIIU9NSpI2');


INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CUSTOMER_SEARCH');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_CUSTOMER_WRITE');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CUSTOMER_REMOVE');

INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_ADMIN');

-- admin
INSERT INTO credential_permissao (codigo_credential, codigo_permissao) values (1, 4);
