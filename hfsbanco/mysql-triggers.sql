DELETE FROM log_ADM_PARAMETRO;
DELETE FROM log_ADM_PARAMETRO_CATEGORIA;
DELETE FROM log_ADM_PAGINA_PERFIL;
DELETE FROM log_ADM_MENU;
DELETE FROM log_ADM_LOG_COLUNA;
DELETE FROM log_ADM_FUNCIONARIO_PERFIL;
DELETE FROM log_ADM_FUNCIONARIO;
DELETE FROM log_ADM_FUNCIONALIDADE_PERFIL;
DELETE FROM log_ADM_FUNCIONALIDADE_PAGINA;
DELETE FROM log_ADM_FUNCIONALIDADE;
DELETE FROM log_ADM_PAGINA;
DELETE FROM log_ADM_CARGO_PERFIL;
DELETE FROM log_ADM_CARGO_FUNCIONARIO;
DELETE FROM log_ADM_CARGO;
DELETE FROM log_ADM_PERFIL;
DELETE FROM log_ADM_SETOR;



CREATE TABLE hfsbanco.log_adm_cargo (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xcar_seq bigint,xcar_descricao varchar(60),
   car_seq bigint,car_descricao varchar(60));

call CreateSequence('log_adm_cargo_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_cargo_ai
AFTER INSERT ON adm_cargo
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo(id,data,operacao,usuario,ip,
        car_seq,car_descricao) VALUES
        (NextVal('log_adm_cargo_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.car_seq,new.car_descricao);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_au
AFTER UPDATE ON adm_cargo
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo(id,data,operacao,usuario,ip,
        xcar_seq,xcar_descricao,
        car_seq,car_descricao) VALUES
        (NextVal('log_adm_cargo_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.car_seq,old.car_descricao,
        new.car_seq,new.car_descricao);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_ad
AFTER DELETE ON adm_cargo
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo(id,data,operacao,usuario,ip,
        car_seq,car_descricao) VALUES
        (NextVal('log_adm_cargo_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.car_seq,old.car_descricao);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_cargo_funcionario (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xcfn_car_seq bigint,xcfn_fun_codigo bigint,xcfn_data_exercicio datetime,xcfn_data_desligamento datetime,xcfn_data_posse datetime,xcfn_situacao varchar(1),xcfn_presidente char(1),xcfn_diretor_geral
varchar(1),xcfn_responsavel_orcamento char(1),xcfn_chefe_sepo char(1),
   cfn_car_seq bigint,cfn_fun_codigo bigint,cfn_data_exercicio datetime,cfn_data_desligamento datetime,cfn_data_posse datetime,cfn_situacao varchar(1),cfn_presidente char(1),cfn_diretor_geral varchar(
1),cfn_responsavel_orcamento char(1),cfn_chefe_sepo char(1));

call CreateSequence('log_adm_cargo_funcionario_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_cargo_funcionario_ai
AFTER INSERT ON adm_cargo_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
        cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (NextVal('log_adm_cargo_funcionario_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.cfn_car_seq,new.cfn_fun_codigo,new.cfn_data_exercicio,new.cfn_data_desligamento,new.cfn_data_posse,new.cfn_situacao,new.cfn_presidente,new.cfn_diretor_geral,new.cfn_responsavel_orcamento,new.cfn_chefe_sepo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_funcionario_au
AFTER UPDATE ON adm_cargo_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
        xcfn_car_seq,xcfn_fun_codigo,xcfn_data_exercicio,xcfn_data_desligamento,xcfn_data_posse,xcfn_situacao,xcfn_presidente,xcfn_diretor_geral,xcfn_responsavel_orcamento,xcfn_chefe_sepo,
        cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (NextVal('log_adm_cargo_funcionario_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.cfn_car_seq,old.cfn_fun_codigo,old.cfn_data_exercicio,old.cfn_data_desligamento,old.cfn_data_posse,old.cfn_situacao,old.cfn_presidente,old.cfn_diretor_geral,old.cfn_responsavel_orcamento,old.cfn_chefe_sepo,
        new.cfn_car_seq,new.cfn_fun_codigo,new.cfn_data_exercicio,new.cfn_data_desligamento,new.cfn_data_posse,new.cfn_situacao,new.cfn_presidente,new.cfn_diretor_geral,new.cfn_responsavel_orcamento,new.cfn_chefe_sepo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_funcionario_ad
AFTER DELETE ON adm_cargo_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
        cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (NextVal('log_adm_cargo_funcionario_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.cfn_car_seq,old.cfn_fun_codigo,old.cfn_data_exercicio,old.cfn_data_desligamento,old.cfn_data_posse,old.cfn_situacao,old.cfn_presidente,old.cfn_diretor_geral,old.cfn_responsavel_orcamento,old.cfn_chefe_sepo);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_cargo_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xcgp_car_seq bigint,xcgp_prf_seq bigint,
   cgp_car_seq bigint,cgp_prf_seq bigint);

call CreateSequence('log_adm_cargo_perfil_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_cargo_perfil_ai
AFTER INSERT ON adm_cargo_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
        cgp_car_seq,cgp_prf_seq) VALUES
        (NextVal('log_adm_cargo_perfil_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.cgp_car_seq,new.cgp_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_perfil_au
AFTER UPDATE ON adm_cargo_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
        xcgp_car_seq,xcgp_prf_seq,
        cgp_car_seq,cgp_prf_seq) VALUES
        (NextVal('log_adm_cargo_perfil_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.cgp_car_seq,old.cgp_prf_seq,
        new.cgp_car_seq,new.cgp_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_cargo_perfil_ad
AFTER DELETE ON adm_cargo_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
        cgp_car_seq,cgp_prf_seq) VALUES
        (NextVal('log_adm_cargo_perfil_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.cgp_car_seq,old.cgp_prf_seq);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_funcionalidade (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xfun_seq bigint,xfun_pag_seq bigint,xfun_descricao varchar(255),
   fun_seq bigint,fun_pag_seq bigint,fun_descricao varchar(255));

call CreateSequence('log_adm_funcionalidade_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_ai
AFTER INSERT ON adm_funcionalidade
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade(id,data,operacao,usuario,ip,
        fun_seq,fun_pag_seq,fun_descricao) VALUES
        (NextVal('log_adm_funcionalidade_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.fun_seq,new.fun_pag_seq,new.fun_descricao);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_au
AFTER UPDATE ON adm_funcionalidade
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade(id,data,operacao,usuario,ip,
        xfun_seq,xfun_pag_seq,xfun_descricao,
        fun_seq,fun_pag_seq,fun_descricao) VALUES
        (NextVal('log_adm_funcionalidade_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fun_seq,old.fun_pag_seq,old.fun_descricao,
        new.fun_seq,new.fun_pag_seq,new.fun_descricao);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_ad
AFTER DELETE ON adm_funcionalidade
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade(id,data,operacao,usuario,ip,
        fun_seq,fun_pag_seq,fun_descricao) VALUES
        (NextVal('log_adm_funcionalidade_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fun_seq,old.fun_pag_seq,old.fun_descricao);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_funcionalidade_pagina (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xfpg_fun_seq bigint,xfpg_pag_seq bigint,
   fpg_fun_seq bigint,fpg_pag_seq bigint);

call CreateSequence('log_adm_funcionalidade_pagina_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_pagina_ai
AFTER INSERT ON adm_funcionalidade_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
        fpg_fun_seq,fpg_pag_seq) VALUES
        (NextVal('log_adm_funcionalidade_pagina_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.fpg_fun_seq,new.fpg_pag_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_pagina_au
AFTER UPDATE ON adm_funcionalidade_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
        xfpg_fun_seq,xfpg_pag_seq,
        fpg_fun_seq,fpg_pag_seq) VALUES
        (NextVal('log_adm_funcionalidade_pagina_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fpg_fun_seq,old.fpg_pag_seq,
        new.fpg_fun_seq,new.fpg_pag_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_pagina_ad
AFTER DELETE ON adm_funcionalidade_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
        fpg_fun_seq,fpg_pag_seq) VALUES
        (NextVal('log_adm_funcionalidade_pagina_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fpg_fun_seq,old.fpg_pag_seq);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_funcionalidade_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xfpl_fun_seq bigint,xfpl_prf_seq bigint,
   fpl_fun_seq bigint,fpl_prf_seq bigint);

call CreateSequence('log_adm_funcionalidade_perfil_seq', 1, 1);

DELIMITER $$
CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_perfil_ai
AFTER INSERT ON adm_funcionalidade_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
        fpl_fun_seq,fpl_prf_seq) VALUES
        (NextVal('log_adm_funcionalidade_perfil_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.fpl_fun_seq,new.fpl_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_perfil_au
AFTER UPDATE ON adm_funcionalidade_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
        xfpl_fun_seq,xfpl_prf_seq,
        fpl_fun_seq,fpl_prf_seq) VALUES
        (NextVal('log_adm_funcionalidade_perfil_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fpl_fun_seq,old.fpl_prf_seq,
        new.fpl_fun_seq,new.fpl_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionalidade_perfil_ad
AFTER DELETE ON adm_funcionalidade_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
        fpl_fun_seq,fpl_prf_seq) VALUES
        (NextVal('log_adm_funcionalidade_perfil_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fpl_fun_seq,old.fpl_prf_seq);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_funcionario (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xfun_codigo bigint,xfun_nome varchar(60),xfun_cpf decimal(11,0),xfun_email varchar(100),xfun_telefone varchar(20),xfun_celular varchar(20),xfun_set_sigla varchar(15),xfun_car_seq bigint,xfun_data_admissao datetime,xfun_data_saida datetime,xfun_ativo char(1),
   fun_codigo bigint,fun_nome varchar(60),fun_cpf decimal(11,0),fun_email varchar(100),fun_telefone varchar(20),fun_celular varchar(20),fun_set_sigla varchar(15),fun_car_seq bigint,fun_data_admissao datetime,fun_data_saida datetime,fun_ativo char(1));

call CreateSequence('log_adm_funcionario_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_ai
AFTER INSERT ON adm_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario(id,data,operacao,usuario,ip,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (NextVal('log_adm_funcionario_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.fun_codigo,new.fun_nome,new.fun_cpf,new.fun_email,new.fun_telefone,new.fun_celular,new.fun_set_sigla,new.fun_car_seq,new.fun_data_admissao,new.fun_data_saida,new.fun_ativo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_au
AFTER UPDATE ON adm_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario(id,data,operacao,usuario,ip,
        xfun_codigo,xfun_nome,xfun_cpf,xfun_email,xfun_telefone,xfun_celular,xfun_set_sigla,xfun_car_seq,xfun_data_admissao,xfun_data_saida,xfun_ativo,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (NextVal('log_adm_funcionario_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fun_codigo,old.fun_nome,old.fun_cpf,old.fun_email,old.fun_telefone,old.fun_celular,old.fun_set_sigla,old.fun_car_seq,old.fun_data_admissao,old.fun_data_saida,old.fun_ativo,
        new.fun_codigo,new.fun_nome,new.fun_cpf,new.fun_email,new.fun_telefone,new.fun_celular,new.fun_set_sigla,new.fun_car_seq,new.fun_data_admissao,new.fun_data_saida,new.fun_ativo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_ad
AFTER DELETE ON adm_funcionario
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario(id,data,operacao,usuario,ip,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (NextVal('log_adm_funcionario_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.fun_codigo,old.fun_nome,old.fun_cpf,old.fun_email,old.fun_telefone,old.fun_celular,old.fun_set_sigla,old.fun_car_seq,old.fun_data_admissao,old.fun_data_saida,old.fun_ativo);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_funcionario_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xusp_prf_seq bigint,xusp_fun_codigo bigint,
   usp_prf_seq bigint,usp_fun_codigo bigint);

call CreateSequence('log_adm_funcionario_perfil_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_perfil_ai
AFTER INSERT ON adm_funcionario_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
        usp_prf_seq,usp_fun_codigo) VALUES
        (NextVal('log_adm_funcionario_perfil_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.usp_prf_seq,new.usp_fun_codigo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_perfil_au
AFTER UPDATE ON adm_funcionario_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
        xusp_prf_seq,xusp_fun_codigo,
        usp_prf_seq,usp_fun_codigo) VALUES
        (NextVal('log_adm_funcionario_perfil_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.usp_prf_seq,old.usp_fun_codigo,
        new.usp_prf_seq,new.usp_fun_codigo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_funcionario_perfil_ad
AFTER DELETE ON adm_funcionario_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
        usp_prf_seq,usp_fun_codigo) VALUES
        (NextVal('log_adm_funcionario_perfil_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.usp_prf_seq,old.usp_fun_codigo);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_log_coluna (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xcol_nome varchar(30),xcol_nome_exibicao varchar(100),xcol_comando varchar(4000),
   col_nome varchar(30),col_nome_exibicao varchar(100),col_comando varchar(4000));

call CreateSequence('log_adm_log_coluna_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_log_coluna_ai
AFTER INSERT ON adm_log_coluna
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_log_coluna(id,data,operacao,usuario,ip,
        col_nome,col_nome_exibicao,col_comando) VALUES
        (NextVal('log_adm_log_coluna_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.col_nome,new.col_nome_exibicao,new.col_comando);
END$$

CREATE TRIGGER hfsbanco.trg_adm_log_coluna_au
AFTER UPDATE ON adm_log_coluna
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_log_coluna(id,data,operacao,usuario,ip,
        xcol_nome,xcol_nome_exibicao,xcol_comando,
        col_nome,col_nome_exibicao,col_comando) VALUES
        (NextVal('log_adm_log_coluna_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.col_nome,old.col_nome_exibicao,old.col_comando,
        new.col_nome,new.col_nome_exibicao,new.col_comando);
END$$

CREATE TRIGGER hfsbanco.trg_adm_log_coluna_ad
AFTER DELETE ON adm_log_coluna
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_log_coluna(id,data,operacao,usuario,ip,
        col_nome,col_nome_exibicao,col_comando) VALUES
        (NextVal('log_adm_log_coluna_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.col_nome,old.col_nome_exibicao,old.col_comando);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_menu (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xmnu_seq bigint,xmnu_descricao varchar(255),xmnu_pai_seq bigint,xmnu_fun_seq bigint,xmnu_ordem decimal(3,0),
   mnu_seq bigint,mnu_descricao varchar(255),mnu_pai_seq bigint,mnu_fun_seq bigint,mnu_ordem decimal(3,0));

call CreateSequence('log_adm_menu_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_menu_ai
AFTER INSERT ON adm_menu
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_menu(id,data,operacao,usuario,ip,
        mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (NextVal('log_adm_menu_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.mnu_seq,new.mnu_descricao,new.mnu_pai_seq,new.mnu_fun_seq,new.mnu_ordem);
END$$

CREATE TRIGGER hfsbanco.trg_adm_menu_au
AFTER UPDATE ON adm_menu
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_menu(id,data,operacao,usuario,ip,
        xmnu_seq,xmnu_descricao,xmnu_pai_seq,xmnu_fun_seq,xmnu_ordem,
        mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (NextVal('log_adm_menu_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.mnu_seq,old.mnu_descricao,old.mnu_pai_seq,old.mnu_fun_seq,old.mnu_ordem,
        new.mnu_seq,new.mnu_descricao,new.mnu_pai_seq,new.mnu_fun_seq,new.mnu_ordem);
END$$

CREATE TRIGGER hfsbanco.trg_adm_menu_ad
AFTER DELETE ON adm_menu
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_menu(id,data,operacao,usuario,ip,
        mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (NextVal('log_adm_menu_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.mnu_seq,old.mnu_descricao,old.mnu_pai_seq,old.mnu_fun_seq,old.mnu_ordem);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_pagina (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xpag_seq bigint,xpag_url varchar(255),xpag_mb varchar(255),
   pag_seq bigint,pag_url varchar(255),pag_mb varchar(255));

call CreateSequence('log_adm_pagina_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_pagina_ai
AFTER INSERT ON adm_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina(id,data,operacao,usuario,ip,
        pag_seq,pag_url,pag_mb) VALUES
        (NextVal('log_adm_pagina_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.pag_seq,new.pag_url,new.pag_mb);
END$$

CREATE TRIGGER hfsbanco.trg_adm_pagina_au
AFTER UPDATE ON adm_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina(id,data,operacao,usuario,ip,
        xpag_seq,xpag_url,xpag_mb,
        pag_seq,pag_url,pag_mb) VALUES
        (NextVal('log_adm_pagina_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pag_seq,old.pag_url,old.pag_mb,
        new.pag_seq,new.pag_url,new.pag_mb);
END$$

CREATE TRIGGER hfsbanco.trg_adm_pagina_ad
AFTER DELETE ON adm_pagina
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina(id,data,operacao,usuario,ip,
        pag_seq,pag_url,pag_mb) VALUES
        (NextVal('log_adm_pagina_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pag_seq,old.pag_url,old.pag_mb);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_pagina_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xpgl_pag_seq bigint,xpgl_prf_seq bigint,
   pgl_pag_seq bigint,pgl_prf_seq bigint);

call CreateSequence('log_adm_pagina_perfil_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_pagina_perfil_ai
AFTER INSERT ON adm_pagina_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
        pgl_pag_seq,pgl_prf_seq) VALUES
        (NextVal('log_adm_pagina_perfil_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.pgl_pag_seq,new.pgl_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_pagina_perfil_au
AFTER UPDATE ON adm_pagina_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
        xpgl_pag_seq,xpgl_prf_seq,
        pgl_pag_seq,pgl_prf_seq) VALUES
        (NextVal('log_adm_pagina_perfil_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pgl_pag_seq,old.pgl_prf_seq,
        new.pgl_pag_seq,new.pgl_prf_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_pagina_perfil_ad
AFTER DELETE ON adm_pagina_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
        pgl_pag_seq,pgl_prf_seq) VALUES
        (NextVal('log_adm_pagina_perfil_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pgl_pag_seq,old.pgl_prf_seq);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_parametro (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xpar_seq bigint,xpar_valor varchar(4000),xpar_descricao varchar(255),xpar_codigo varchar(255),xpar_pmc_seq bigint,
   par_seq bigint,par_valor varchar(4000),par_descricao varchar(255),par_codigo varchar(255),par_pmc_seq bigint);

call CreateSequence('log_adm_parametro_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_parametro_ai
AFTER INSERT ON adm_parametro
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro(id,data,operacao,usuario,ip,
        par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (NextVal('log_adm_parametro_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.par_seq,new.par_valor,new.par_descricao,new.par_codigo,new.par_pmc_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_parametro_au
AFTER UPDATE ON adm_parametro
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro(id,data,operacao,usuario,ip,
        xpar_seq,xpar_valor,xpar_descricao,xpar_codigo,xpar_pmc_seq,
        par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (NextVal('log_adm_parametro_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.par_seq,old.par_valor,old.par_descricao,old.par_codigo,old.par_pmc_seq,
        new.par_seq,new.par_valor,new.par_descricao,new.par_codigo,new.par_pmc_seq);
END$$

CREATE TRIGGER hfsbanco.trg_adm_parametro_ad
AFTER DELETE ON adm_parametro
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro(id,data,operacao,usuario,ip,
        par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (NextVal('log_adm_parametro_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.par_seq,old.par_valor,old.par_descricao,old.par_codigo,old.par_pmc_seq);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_parametro_categoria (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xpmc_seq bigint,xpmc_descricao varchar(100),xpmc_ordem decimal(2,0),
   pmc_seq bigint,pmc_descricao varchar(100),pmc_ordem decimal(2,0));

call CreateSequence('log_adm_parametro_categoria_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_parametro_categoria_ai
AFTER INSERT ON adm_parametro_categoria
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
        pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (NextVal('log_adm_parametro_categoria_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.pmc_seq,new.pmc_descricao,new.pmc_ordem);
END$$

CREATE TRIGGER hfsbanco.trg_adm_parametro_categoria_au
AFTER UPDATE ON adm_parametro_categoria
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
        xpmc_seq,xpmc_descricao,xpmc_ordem,
        pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (NextVal('log_adm_parametro_categoria_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pmc_seq,old.pmc_descricao,old.pmc_ordem,
        new.pmc_seq,new.pmc_descricao,new.pmc_ordem);
END$$

CREATE TRIGGER hfsbanco.trg_adm_parametro_categoria_ad
AFTER DELETE ON adm_parametro_categoria
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
        pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (NextVal('log_adm_parametro_categoria_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.pmc_seq,old.pmc_descricao,old.pmc_ordem);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xprf_seq bigint,xprf_descricao varchar(255),xprf_geral char(1),xprf_administrador char(1),
   prf_seq bigint,prf_descricao varchar(255),prf_geral char(1),prf_administrador char(1));

call CreateSequence('log_adm_perfil_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_perfil_ai
AFTER INSERT ON adm_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_perfil(id,data,operacao,usuario,ip,
        prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (NextVal('log_adm_perfil_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.prf_seq,new.prf_descricao,new.prf_geral,new.prf_administrador);
END$$

CREATE TRIGGER hfsbanco.trg_adm_perfil_au
AFTER UPDATE ON adm_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_perfil(id,data,operacao,usuario,ip,
        xprf_seq,xprf_descricao,xprf_geral,xprf_administrador,
        prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (NextVal('log_adm_perfil_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.prf_seq,old.prf_descricao,old.prf_geral,old.prf_administrador,
        new.prf_seq,new.prf_descricao,new.prf_geral,new.prf_administrador);
END$$

CREATE TRIGGER hfsbanco.trg_adm_perfil_ad
AFTER DELETE ON adm_perfil
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_perfil(id,data,operacao,usuario,ip,
        prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (NextVal('log_adm_perfil_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.prf_seq,old.prf_descricao,old.prf_geral,old.prf_administrador);
END$$

DELIMITER ;

/*--------------------------------------------------------------*/

CREATE TABLE hfsbanco.log_adm_setor (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       TIMESTAMP(0),
   operacao   char(1),
   ip         varchar(50),
   xset_sigla varchar(15),xset_nome varchar(50),xset_pai varchar(15),xset_tipo varchar(20),
   set_sigla varchar(15),set_nome varchar(50),set_pai varchar(15),set_tipo varchar(20));

call CreateSequence('log_adm_setor_seq', 1, 1);

DELIMITER $$

CREATE TRIGGER hfsbanco.trg_adm_setor_ai
AFTER INSERT ON adm_setor
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_setor(id,data,operacao,usuario,ip,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (NextVal('log_adm_setor_seq'), CURRENT_TIMESTAMP(), 'I', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        new.set_sigla,new.set_nome,new.set_pai,new.set_tipo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_setor_au
AFTER UPDATE ON adm_setor
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_setor(id,data,operacao,usuario,ip,
        xset_sigla,xset_nome,xset_pai,xset_tipo,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (NextVal('log_adm_setor_seq'), CURRENT_TIMESTAMP(), 'U', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.set_sigla,old.set_nome,old.set_pai,old.set_tipo,
        new.set_sigla,new.set_nome,new.set_pai,new.set_tipo);
END$$

CREATE TRIGGER hfsbanco.trg_adm_setor_ad
AFTER DELETE ON adm_setor
FOR EACH ROW
BEGIN
        INSERT INTO hfsbanco.log_adm_setor(id,data,operacao,usuario,ip,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (NextVal('log_adm_setor_seq'), CURRENT_TIMESTAMP(), 'D', ifnull(pkg_adm_obter_usuario(), USER()),
        ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),
        old.set_sigla,old.set_nome,old.set_pai,old.set_tipo);
END$$

DELIMITER ;

