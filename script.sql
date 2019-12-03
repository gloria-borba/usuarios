--Criação de tabelas
CREATE TABLE usuario (
cd_usuario NUMBER(8,0) NOT NULL,
nome VARCHAR2(200) NOT NULL,
email VARCHAR2(200) NOT NULL,
senha VARCHAR2(200) NOT null
) 
/ 

CREATE TABLE telefone(
cd_telefone NUMBER(8,0) NOT NULL,
ddd NUMBER(7,0) NOT NULL,
numero VARCHAR2(20) NOT NULL,
tipo VARCHAR2(50) NULL,
cd_usuario NUMBER(8,0) NOT NULL
) 
/

ALTER TABLE telefone
  ADD CONSTRAINT cnt_usuario_fk FOREIGN KEY (
    cd_usuario
  ) REFERENCES usuario (
    cd_usuario
  ) 
/

create sequence seq_usuario
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
nocache
nocycle;
/

create sequence seq_telefone
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
nocache
nocycle;
/

COMMENT ON TABLE telefone IS 'número de telefones de usuários';
/

COMMENT ON TABLE usuario IS 'informações dos usuarios';
/

COMMENT ON COLUMN usuario.cd_usuario IS 'Chave Primaria';
COMMENT ON COLUMN usuario.nome IS 'Nome';
COMMENT ON COLUMN usuario.email IS 'Email';
COMMENT ON COLUMN usuario.senha IS 'senha';
/

COMMENT ON COLUMN telefone.cd_telefone IS 'Chave Primaria';
COMMENT ON COLUMN telefone.ddd IS 'DDD';
COMMENT ON COLUMN telefone.numero IS 'numero';
COMMENT ON COLUMN telefone.tipo IS 'tipo';
COMMENT ON COLUMN telefone.cd_usuario IS 'Chave com usuario';
/

insert into USUARIO values (SEQ_USUARIO.nextval, 'gloria borba', 'gloriaborba@gmail.com', 'teste');
insert into USUARIO values (SEQ_USUARIO.nextval, 'bruno souza', 'brunosouza@gmail.com', '123');
insert into USUARIO values (SEQ_USUARIO.nextval, 'maria barbosa', 'mariazinha@gmail.com', 'senha');
insert into USUARIO values (SEQ_USUARIO.nextval, 'cristiano araujo', 'cristianosilva@gmail.com', '12345678');
insert into USUARIO values (SEQ_USUARIO.nextval, 'ivete sangalo', 'ivetinha@gmail.com', '12349854');
insert into USUARIO values (SEQ_USUARIO.nextval, 'angelina jolie', 'angel@gmail.com', 'casadecampo');
/
alter table USUARIO
add constraint cd_usuario_pk primary key(cd_usuario)
/
alter table TELEFONE
add constraint cd_telefone_pk primary key(cd_telefone);
