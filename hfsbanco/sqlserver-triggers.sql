CREATE TABLE dbo.log_adm_cargo (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xcar_seq bigint,xcar_descricao nvarchar(60),
   car_seq bigint,car_descricao nvarchar(60));

CREATE SEQUENCE dbo.log_adm_cargo_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_cargo_auid
ON dbo.adm_cargo
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_cargo(id,data,operacao,usuario,ip,
	 car_seq,car_descricao)
	 SELECT NEXT VALUE FOR log_adm_cargo_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 car_seq,car_descricao FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.car_seq = DELETED.car_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_cargo(id,data,operacao,usuario,ip,
	 xcar_seq,xcar_descricao,
	 car_seq,car_descricao)
	 SELECT NEXT VALUE FOR log_adm_cargo_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select car_seq from deleted),(select car_descricao from deleted),
	 car_seq,car_descricao FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.car_seq = DELETED.car_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_cargo(id,data,operacao,usuario,ip,
	 car_seq,car_descricao)
	 SELECT NEXT VALUE FOR log_adm_cargo_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 car_seq,car_descricao FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.car_seq = INSERTED.car_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_cargo_funcionario (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xcfn_car_seq bigint,xcfn_fun_codigo bigint,xcfn_data_exercicio datetime,xcfn_data_desligamento datetime,xcfn_data_posse datetime,xcfn_situacao char(1),xcfn_presidente char(1),xcfn_diretor_geral char(1),xcfn_responsavel_orcamento char(1),xcfn_chefe_sepo char(1),
   cfn_car_seq bigint,cfn_fun_codigo bigint,cfn_data_exercicio datetime,cfn_data_desligamento datetime,cfn_data_posse datetime,cfn_situacao char(1),cfn_presidente char(1),cfn_diretor_geral char(1),cfn_responsavel_orcamento char(1),cfn_chefe_sepo char(1));

CREATE SEQUENCE dbo.log_adm_cargo_funcionario_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_cargo_funcionario_auid
ON dbo.adm_cargo_funcionario
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo)
	 SELECT NEXT VALUE FOR log_adm_cargo_funcionario_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.cfn_car_seq = DELETED.cfn_car_seq AND INSERTED.cfn_fun_codigo = DELETED.cfn_fun_codigo);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
	 xcfn_car_seq,xcfn_fun_codigo,xcfn_data_exercicio,xcfn_data_desligamento,xcfn_data_posse,xcfn_situacao,xcfn_presidente,xcfn_diretor_geral,xcfn_responsavel_orcamento,xcfn_chefe_sepo,
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo)
	 SELECT NEXT VALUE FOR log_adm_cargo_funcionario_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select cfn_car_seq from deleted),(select cfn_fun_codigo from deleted),(select cfn_data_exercicio from deleted),(select cfn_data_desligamento from deleted),(select cfn_data_posse from deleted),(select cfn_situacao from deleted),(select cfn_presidente from deleted),(select cfn_diretor_geral from deleted),(select cfn_responsavel_orcamento from deleted),(select cfn_chefe_sepo from deleted),
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.cfn_car_seq = DELETED.cfn_car_seq AND INSERTED.cfn_fun_codigo = DELETED.cfn_fun_codigo);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo)
	 SELECT NEXT VALUE FOR log_adm_cargo_funcionario_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 cfn_car_seq,cfn_fun_codigo,cfn_data_exercicio,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.cfn_car_seq = INSERTED.cfn_car_seq AND DELETED.cfn_fun_codigo = INSERTED.cfn_fun_codigo);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_cargo_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xcgp_car_seq bigint,xcgp_prf_seq bigint,
   cgp_car_seq bigint,cgp_prf_seq bigint);

CREATE SEQUENCE dbo.log_adm_cargo_perfil_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_cargo_perfil_auid
ON dbo.adm_cargo_perfil
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
	 cgp_car_seq,cgp_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_cargo_perfil_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 cgp_car_seq,cgp_prf_seq FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.cgp_car_seq = DELETED.cgp_car_seq AND INSERTED.cgp_prf_seq = DELETED.cgp_prf_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
	 xcgp_car_seq,xcgp_prf_seq,
	 cgp_car_seq,cgp_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_cargo_perfil_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select cgp_car_seq from deleted),(select cgp_prf_seq from deleted),
	 cgp_car_seq,cgp_prf_seq FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.cgp_car_seq = DELETED.cgp_car_seq AND INSERTED.cgp_prf_seq = DELETED.cgp_prf_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_cargo_perfil(id,data,operacao,usuario,ip,
	 cgp_car_seq,cgp_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_cargo_perfil_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 cgp_car_seq,cgp_prf_seq FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.cgp_car_seq = INSERTED.cgp_car_seq AND DELETED.cgp_prf_seq = INSERTED.cgp_prf_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_funcionalidade (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xfun_seq bigint,xfun_pag_seq bigint,xfun_descricao nvarchar(255),
   fun_seq bigint,fun_pag_seq bigint,fun_descricao nvarchar(255));

CREATE SEQUENCE dbo.log_adm_funcionalidade_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_funcionalidade_auid
ON dbo.adm_funcionalidade
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_funcionalidade(id,data,operacao,usuario,ip,
	 fun_seq,fun_pag_seq,fun_descricao)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fun_seq,fun_pag_seq,fun_descricao FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.fun_seq = DELETED.fun_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_funcionalidade(id,data,operacao,usuario,ip,
	 xfun_seq,xfun_pag_seq,xfun_descricao,
	 fun_seq,fun_pag_seq,fun_descricao)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select fun_seq from deleted),(select fun_pag_seq from deleted),(select fun_descricao from deleted),
	 fun_seq,fun_pag_seq,fun_descricao FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.fun_seq = DELETED.fun_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_funcionalidade(id,data,operacao,usuario,ip,
	 fun_seq,fun_pag_seq,fun_descricao)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fun_seq,fun_pag_seq,fun_descricao FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.fun_seq = INSERTED.fun_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_funcionalidade_pagina (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xfpg_fun_seq bigint,xfpg_pag_seq bigint,
   fpg_fun_seq bigint,fpg_pag_seq bigint);

CREATE SEQUENCE dbo.log_adm_funcionalidade_pagina_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_funcionalidade_pagina_auid
ON dbo.adm_funcionalidade_pagina
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
	 fpg_fun_seq,fpg_pag_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_pagina_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fpg_fun_seq,fpg_pag_seq FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.fpg_fun_seq = DELETED.fpg_fun_seq AND INSERTED.fpg_pag_seq = DELETED.fpg_pag_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
	 xfpg_fun_seq,xfpg_pag_seq,
	 fpg_fun_seq,fpg_pag_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_pagina_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select fpg_fun_seq from deleted),(select fpg_pag_seq from deleted),
	 fpg_fun_seq,fpg_pag_seq FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.fpg_fun_seq = DELETED.fpg_fun_seq AND INSERTED.fpg_pag_seq = DELETED.fpg_pag_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,
	 fpg_fun_seq,fpg_pag_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_pagina_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fpg_fun_seq,fpg_pag_seq FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.fpg_fun_seq = INSERTED.fpg_fun_seq AND DELETED.fpg_pag_seq = INSERTED.fpg_pag_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_funcionalidade_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xfpl_fun_seq bigint,xfpl_prf_seq bigint,
   fpl_fun_seq bigint,fpl_prf_seq bigint);

CREATE SEQUENCE dbo.log_adm_funcionalidade_perfil_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_funcionalidade_perfil_auid
ON dbo.adm_funcionalidade_perfil
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
	 fpl_fun_seq,fpl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_perfil_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fpl_fun_seq,fpl_prf_seq FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.fpl_fun_seq = DELETED.fpl_fun_seq AND INSERTED.fpl_prf_seq = DELETED.fpl_prf_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
	 xfpl_fun_seq,xfpl_prf_seq,
	 fpl_fun_seq,fpl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_perfil_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select fpl_fun_seq from deleted),(select fpl_prf_seq from deleted),
	 fpl_fun_seq,fpl_prf_seq FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.fpl_fun_seq = DELETED.fpl_fun_seq AND INSERTED.fpl_prf_seq = DELETED.fpl_prf_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,
	 fpl_fun_seq,fpl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_funcionalidade_perfil_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fpl_fun_seq,fpl_prf_seq FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.fpl_fun_seq = INSERTED.fpl_fun_seq AND DELETED.fpl_prf_seq = INSERTED.fpl_prf_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_funcionario (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xfun_codigo bigint,xfun_nome nvarchar(60),xfun_cpf decimal(11,0),xfun_email nvarchar(100),xfun_telefone nvarchar(20),xfun_celular nvarchar(20),xfun_set_sigla nvarchar(15),xfun_car_seq bigint,xfun_data_admissao datetime,xfun_data_saida datetime,xfun_ativo char(1),
   fun_codigo bigint,fun_nome nvarchar(60),fun_cpf decimal(11,0),fun_email nvarchar(100),fun_telefone nvarchar(20),fun_celular nvarchar(20),fun_set_sigla nvarchar(15),fun_car_seq bigint,fun_data_admissao datetime,fun_data_saida datetime,fun_ativo char(1));

CREATE SEQUENCE dbo.log_adm_funcionario_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_funcionario_auid
ON dbo.adm_funcionario
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_funcionario(id,data,operacao,usuario,ip,
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.fun_codigo = DELETED.fun_codigo);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_funcionario(id,data,operacao,usuario,ip,
	 xfun_codigo,xfun_nome,xfun_cpf,xfun_email,xfun_telefone,xfun_celular,xfun_set_sigla,xfun_car_seq,xfun_data_admissao,xfun_data_saida,xfun_ativo,
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select fun_codigo from deleted),(select fun_nome from deleted),(select fun_cpf from deleted),(select fun_email from deleted),(select fun_telefone from deleted),(select fun_celular from deleted),(select fun_set_sigla from deleted),(select fun_car_seq from deleted),(select fun_data_admissao from deleted),(select fun_data_saida from deleted),(select fun_ativo from deleted),
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.fun_codigo = DELETED.fun_codigo);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_funcionario(id,data,operacao,usuario,ip,
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.fun_codigo = INSERTED.fun_codigo);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_funcionario_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xusp_prf_seq bigint,xusp_fun_codigo bigint,
   usp_prf_seq bigint,usp_fun_codigo bigint);

CREATE SEQUENCE dbo.log_adm_funcionario_perfil_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_funcionario_perfil_auid
ON dbo.adm_funcionario_perfil
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
	 usp_prf_seq,usp_fun_codigo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_perfil_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 usp_prf_seq,usp_fun_codigo FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.usp_fun_codigo = DELETED.usp_fun_codigo AND INSERTED.usp_prf_seq = DELETED.usp_prf_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
	 xusp_prf_seq,xusp_fun_codigo,
	 usp_prf_seq,usp_fun_codigo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_perfil_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select usp_prf_seq from deleted),(select usp_fun_codigo from deleted),
	 usp_prf_seq,usp_fun_codigo FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.usp_fun_codigo = DELETED.usp_fun_codigo AND INSERTED.usp_prf_seq = DELETED.usp_prf_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,
	 usp_prf_seq,usp_fun_codigo)
	 SELECT NEXT VALUE FOR log_adm_funcionario_perfil_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 usp_prf_seq,usp_fun_codigo FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.usp_fun_codigo = INSERTED.usp_fun_codigo AND DELETED.usp_prf_seq = INSERTED.usp_prf_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_log_coluna (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xcol_nome nvarchar(30),xcol_nome_exibicao nvarchar(100),xcol_comando nvarchar(4000),
   col_nome nvarchar(30),col_nome_exibicao nvarchar(100),col_comando nvarchar(4000));

CREATE SEQUENCE dbo.log_adm_log_coluna_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_log_coluna_auid
ON dbo.adm_log_coluna
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_log_coluna(id,data,operacao,usuario,ip,
	 col_nome,col_nome_exibicao,col_comando)
	 SELECT NEXT VALUE FOR log_adm_log_coluna_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 col_nome,col_nome_exibicao,col_comando FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.col_nome = DELETED.col_nome);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_log_coluna(id,data,operacao,usuario,ip,
	 xcol_nome,xcol_nome_exibicao,xcol_comando,
	 col_nome,col_nome_exibicao,col_comando)
	 SELECT NEXT VALUE FOR log_adm_log_coluna_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select col_nome from deleted),(select col_nome_exibicao from deleted),(select col_comando from deleted),
	 col_nome,col_nome_exibicao,col_comando FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.col_nome = DELETED.col_nome);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_log_coluna(id,data,operacao,usuario,ip,
	 col_nome,col_nome_exibicao,col_comando)
	 SELECT NEXT VALUE FOR log_adm_log_coluna_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 col_nome,col_nome_exibicao,col_comando FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.col_nome = INSERTED.col_nome);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_menu (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xmnu_seq bigint,xmnu_descricao nvarchar(255),xmnu_pai_seq bigint,xmnu_fun_seq bigint,xmnu_ordem decimal(3,0),
   mnu_seq bigint,mnu_descricao nvarchar(255),mnu_pai_seq bigint,mnu_fun_seq bigint,mnu_ordem decimal(3,0));

CREATE SEQUENCE dbo.log_adm_menu_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_menu_auid
ON dbo.adm_menu
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_menu(id,data,operacao,usuario,ip,
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem)
	 SELECT NEXT VALUE FOR log_adm_menu_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.mnu_seq = DELETED.mnu_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_menu(id,data,operacao,usuario,ip,
	 xmnu_seq,xmnu_descricao,xmnu_pai_seq,xmnu_fun_seq,xmnu_ordem,
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem)
	 SELECT NEXT VALUE FOR log_adm_menu_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select mnu_seq from deleted),(select mnu_descricao from deleted),(select mnu_pai_seq from deleted),(select mnu_fun_seq from deleted),(select mnu_ordem from deleted),
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.mnu_seq = DELETED.mnu_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_menu(id,data,operacao,usuario,ip,
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem)
	 SELECT NEXT VALUE FOR log_adm_menu_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.mnu_seq = INSERTED.mnu_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_pagina (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xpag_seq bigint,xpag_url nvarchar(255),xpag_mb nvarchar(255),
   pag_seq bigint,pag_url nvarchar(255),pag_mb nvarchar(255));

CREATE SEQUENCE dbo.log_adm_pagina_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_pagina_auid
ON dbo.adm_pagina
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_pagina(id,data,operacao,usuario,ip,
	 pag_seq,pag_url,pag_mb)
	 SELECT NEXT VALUE FOR log_adm_pagina_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pag_seq,pag_url,pag_mb FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.pag_seq = DELETED.pag_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_pagina(id,data,operacao,usuario,ip,
	 xpag_seq,xpag_url,xpag_mb,
	 pag_seq,pag_url,pag_mb)
	 SELECT NEXT VALUE FOR log_adm_pagina_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select pag_seq from deleted),(select pag_url from deleted),(select pag_mb from deleted),
	 pag_seq,pag_url,pag_mb FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.pag_seq = DELETED.pag_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_pagina(id,data,operacao,usuario,ip,
	 pag_seq,pag_url,pag_mb)
	 SELECT NEXT VALUE FOR log_adm_pagina_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pag_seq,pag_url,pag_mb FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.pag_seq = INSERTED.pag_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_pagina_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xpgl_pag_seq bigint,xpgl_prf_seq bigint,
   pgl_pag_seq bigint,pgl_prf_seq bigint);

CREATE SEQUENCE dbo.log_adm_pagina_perfil_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_pagina_perfil_auid
ON dbo.adm_pagina_perfil
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
	 pgl_pag_seq,pgl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_pagina_perfil_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pgl_pag_seq,pgl_prf_seq FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.pgl_pag_seq = DELETED.pgl_pag_seq AND INSERTED.pgl_prf_seq = DELETED.pgl_prf_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
	 xpgl_pag_seq,xpgl_prf_seq,
	 pgl_pag_seq,pgl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_pagina_perfil_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select pgl_pag_seq from deleted),(select pgl_prf_seq from deleted),
	 pgl_pag_seq,pgl_prf_seq FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.pgl_pag_seq = DELETED.pgl_pag_seq AND INSERTED.pgl_prf_seq = DELETED.pgl_prf_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_pagina_perfil(id,data,operacao,usuario,ip,
	 pgl_pag_seq,pgl_prf_seq)
	 SELECT NEXT VALUE FOR log_adm_pagina_perfil_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pgl_pag_seq,pgl_prf_seq FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.pgl_pag_seq = INSERTED.pgl_pag_seq AND DELETED.pgl_prf_seq = INSERTED.pgl_prf_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_parametro (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xpar_seq bigint,xpar_valor nvarchar(4000),xpar_descricao nvarchar(255),xpar_codigo nvarchar(255),xpar_pmc_seq bigint,
   par_seq bigint,par_valor nvarchar(4000),par_descricao nvarchar(255),par_codigo nvarchar(255),par_pmc_seq bigint);

CREATE SEQUENCE dbo.log_adm_parametro_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_parametro_auid
ON dbo.adm_parametro
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_parametro(id,data,operacao,usuario,ip,
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq)
	 SELECT NEXT VALUE FOR log_adm_parametro_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.par_seq = DELETED.par_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_parametro(id,data,operacao,usuario,ip,
	 xpar_seq,xpar_valor,xpar_descricao,xpar_codigo,xpar_pmc_seq,
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq)
	 SELECT NEXT VALUE FOR log_adm_parametro_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select par_seq from deleted),(select par_valor from deleted),(select par_descricao from deleted),(select par_codigo from deleted),(select par_pmc_seq from deleted),
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.par_seq = DELETED.par_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_parametro(id,data,operacao,usuario,ip,
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq)
	 SELECT NEXT VALUE FOR log_adm_parametro_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.par_seq = INSERTED.par_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_parametro_categoria (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xpmc_seq bigint,xpmc_descricao nvarchar(100),xpmc_ordem decimal(2,0),
   pmc_seq bigint,pmc_descricao nvarchar(100),pmc_ordem decimal(2,0));

CREATE SEQUENCE dbo.log_adm_parametro_categoria_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_parametro_categoria_auid
ON dbo.adm_parametro_categoria
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
	 pmc_seq,pmc_descricao,pmc_ordem)
	 SELECT NEXT VALUE FOR log_adm_parametro_categoria_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pmc_seq,pmc_descricao,pmc_ordem FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.pmc_seq = DELETED.pmc_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
	 xpmc_seq,xpmc_descricao,xpmc_ordem,
	 pmc_seq,pmc_descricao,pmc_ordem)
	 SELECT NEXT VALUE FOR log_adm_parametro_categoria_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select pmc_seq from deleted),(select pmc_descricao from deleted),(select pmc_ordem from deleted),
	 pmc_seq,pmc_descricao,pmc_ordem FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.pmc_seq = DELETED.pmc_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_parametro_categoria(id,data,operacao,usuario,ip,
	 pmc_seq,pmc_descricao,pmc_ordem)
	 SELECT NEXT VALUE FOR log_adm_parametro_categoria_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 pmc_seq,pmc_descricao,pmc_ordem FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.pmc_seq = INSERTED.pmc_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_perfil (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xprf_seq bigint,xprf_descricao nvarchar(255),xprf_geral char(1),xprf_administrador char(1),
   prf_seq bigint,prf_descricao nvarchar(255),prf_geral char(1),prf_administrador char(1));

CREATE SEQUENCE dbo.log_adm_perfil_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_perfil_auid
ON dbo.adm_perfil
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_perfil(id,data,operacao,usuario,ip,
	 prf_seq,prf_descricao,prf_geral,prf_administrador)
	 SELECT NEXT VALUE FOR log_adm_perfil_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 prf_seq,prf_descricao,prf_geral,prf_administrador FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.prf_seq = DELETED.prf_seq);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_perfil(id,data,operacao,usuario,ip,
	 xprf_seq,xprf_descricao,xprf_geral,xprf_administrador,
	 prf_seq,prf_descricao,prf_geral,prf_administrador)
	 SELECT NEXT VALUE FOR log_adm_perfil_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select prf_seq from deleted),(select prf_descricao from deleted),(select prf_geral from deleted),(select prf_administrador from deleted),
	 prf_seq,prf_descricao,prf_geral,prf_administrador FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.prf_seq = DELETED.prf_seq);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_perfil(id,data,operacao,usuario,ip,
	 prf_seq,prf_descricao,prf_geral,prf_administrador)
	 SELECT NEXT VALUE FOR log_adm_perfil_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 prf_seq,prf_descricao,prf_geral,prf_administrador FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.prf_seq = INSERTED.prf_seq);
END
GO


/*--------------------------------------------------------------*/

CREATE TABLE dbo.log_adm_setor (
   id         BIGINT not null primary key,
   usuario    varchar(30),
   data       datetime,
   operacao   char(1),
   ip         nvarchar(50),
   xset_sigla nvarchar(15),xset_nome nvarchar(50),xset_pai nvarchar(15),xset_tipo nvarchar(20),
   set_sigla nvarchar(15),set_nome nvarchar(50),set_pai nvarchar(15),set_tipo nvarchar(20));

CREATE SEQUENCE dbo.log_adm_setor_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;

CREATE TRIGGER dbo.trg_adm_setor_auid
ON dbo.adm_setor
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	 -- AFTER INSERT
	 INSERT INTO dbo.log_adm_setor(id,data,operacao,usuario,ip,
	 set_sigla,set_nome,set_pai,set_tipo)
	 SELECT NEXT VALUE FOR log_adm_setor_seq, CURRENT_TIMESTAMP, 'I', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 set_sigla,set_nome,set_pai,set_tipo FROM INSERTED
	 WHERE NOT EXISTS (SELECT * FROM DELETED WHERE INSERTED.set_sigla = DELETED.set_sigla);
	 -- AFTER UPDATE
	 INSERT INTO dbo.log_adm_setor(id,data,operacao,usuario,ip,
	 xset_sigla,xset_nome,xset_pai,xset_tipo,
	 set_sigla,set_nome,set_pai,set_tipo)
	 SELECT NEXT VALUE FOR log_adm_setor_seq, CURRENT_TIMESTAMP, 'U', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 (select set_sigla from deleted),(select set_nome from deleted),(select set_pai from deleted),(select set_tipo from deleted),
	 set_sigla,set_nome,set_pai,set_tipo FROM INSERTED
	 WHERE EXISTS (SELECT * FROM DELETED WHERE INSERTED.set_sigla = DELETED.set_sigla);
	 -- AFTER DELETE
	 INSERT INTO dbo.log_adm_setor(id,data,operacao,usuario,ip,
	 set_sigla,set_nome,set_pai,set_tipo)
	 SELECT NEXT VALUE FOR log_adm_setor_seq, CURRENT_TIMESTAMP, 'D', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),
	 isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'))),
	 set_sigla,set_nome,set_pai,set_tipo FROM DELETED
	 WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE DELETED.set_sigla = INSERTED.set_sigla);
END
GO


/*--------------------------------------------------------------*/

