CREATE TABLE log_cargo (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
xcar_seq  NUMBER, xcar_descricao VARCHAR2(60),car_seq  NUMBER, car_descricao VARCHAR2(60));
					 
CREATE SEQUENCE log_CARGO_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_cargo_auid AFTER INSERT OR UPDATE OR DELETE ON adm_cargo
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_cargo(id,data,operacao,usuario,ip,
        car_seq,car_descricao) VALUES
        (log_cargo_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.car_seq,:new.car_descricao);
      ELSIF (UPDATING) THEN
        INSERT INTO log_cargo(id,data,operacao,usuario,ip,
        xcar_seq,xcar_descricao,
        car_seq,car_descricao) VALUES
        (log_cargo_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.car_seq,:old.car_descricao,
        :new.car_seq,:new.car_descricao);
      ELSE
        INSERT INTO log_cargo(id,data,operacao,usuario,ip,
        car_seq,car_descricao) VALUES
        (log_cargo_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.car_seq,:old.car_descricao);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_cargo_funcionario (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
  xcfn_car_seq NUMBER, xcfn_fun_codigo NUMBER, xcfn_data_exercicio DATE, xcfn_data_desligamento DATE, xcfn_data_posse DATE,
  xcfn_situacao CHAR(1), xcfn_presidente CHAR(1), xcfn_diretor_geral CHAR(1), xcfn_responsavel_orcamento CHAR(1), xcfn_chefe_sepo CHAR(1),
  cfn_car_seq NUMBER, cfn_fun_codigo NUMBER, cfn_data_exercicio DATE, cfn_data_desligamento DATE, cfn_data_posse DATE,
cfn_situacao CHAR(1), cfn_presidente CHAR(1), cfn_diretor_geral CHAR(1), cfn_responsavel_orcamento CHAR(1), cfn_chefe_sepo CHAR(1));
					 
CREATE SEQUENCE log_CARGO_funcionario_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_cargo_funcionario_auid AFTER INSERT OR UPDATE OR DELETE ON adm_cargo_funcionario
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_cargo_funcionario(id,data,operacao,usuario,ip,
        cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (log_cargo_funcionario_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.cfn_data_exercicio,:new.cfn_fun_codigo,:new.cfn_car_seq,:new.cfn_data_desligamento,:new.cfn_data_posse,:new.cfn_situacao,:new.cfn_presidente,:new.cfn_diretor_geral,:new.cfn_responsavel_orcamento,:new.cfn_chefe_sepo);
      ELSIF (UPDATING) THEN
        INSERT INTO log_cargo_funcionario(id,data,operacao,usuario,ip,
        xcfn_data_exercicio,xcfn_fun_codigo,xcfn_car_seq,xcfn_data_desligamento,xcfn_data_posse,xcfn_situacao,xcfn_presidente,xcfn_diretor_geral,xcfn_responsavel_orcamento,xcfn_chefe_sepo,
		cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (log_cargo_funcionario_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.cfn_data_exercicio,:old.cfn_fun_codigo,:old.cfn_car_seq,:old.cfn_data_desligamento,:old.cfn_data_posse,:old.cfn_situacao,:old.cfn_presidente,:old.cfn_diretor_geral,:old.cfn_responsavel_orcamento,:old.cfn_chefe_sepo,
        :new.cfn_data_exercicio,:new.cfn_fun_codigo,:new.cfn_car_seq,:new.cfn_data_desligamento,:new.cfn_data_posse,:new.cfn_situacao,:new.cfn_presidente,:new.cfn_diretor_geral,:new.cfn_responsavel_orcamento,:new.cfn_chefe_sepo);
      ELSE
        INSERT INTO log_cargo_funcionario(id,data,operacao,usuario,ip,
        cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
        (log_cargo_funcionario_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.cfn_data_exercicio,:old.cfn_fun_codigo,:old.cfn_car_seq,:old.cfn_data_desligamento,:old.cfn_data_posse,:old.cfn_situacao,:old.cfn_presidente,:old.cfn_diretor_geral,:old.cfn_responsavel_orcamento,:old.cfn_chefe_sepo);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_cargo_perfil (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XCGP_COD_CARGO NUMBER,XCGP_PRF_SEQ NUMBER,CGP_COD_CARGO NUMBER,CGP_PRF_SEQ NUMBER);
					 
CREATE SEQUENCE log_CARGO_PERFIL_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_cargo_perfil_auid AFTER INSERT OR UPDATE OR DELETE ON adm_cargo_perfil
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_cargo_perfil(id,data,operacao,usuario,ip,
        cgp_cod_cargo,cgp_prf_seq) VALUES
        (log_cargo_perfil_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.cgp_cod_cargo,:new.cgp_prf_seq);
      ELSIF (UPDATING) THEN
        INSERT INTO log_cargo_perfil(id,data,operacao,usuario,ip,
        xcgp_cod_cargo,xcgp_prf_seq,
        cgp_cod_cargo,cgp_prf_seq) VALUES
        (log_cargo_perfil_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.cgp_cod_cargo,:old.cgp_prf_seq,
        :new.cgp_cod_cargo,:new.cgp_prf_seq);
      ELSE
            INSERT INTO log_cargo_perfil(id,data,operacao,usuario,ip,
            cgp_cod_cargo,cgp_prf_seq) VALUES
        (log_cargo_perfil_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.cgp_cod_cargo,:old.cgp_prf_seq);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_funcionalidade (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XFUN_SEQ NUMBER,XFUN_PAG_SEQ NUMBER,XFUN_DESCRICAO VARCHAR2(255),FUN_SEQ NUMBER,FUN_PAG_SEQ NUMBER,FUN_DESCRICAO VARCHAR2(255));
					 
CREATE SEQUENCE log_FUNCIONALIDADE_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_funcionalidade_auid AFTER INSERT OR UPDATE OR DELETE ON adm_funcionalidade
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_funcionalidade(id,data,operacao,usuario,ip,
        fun_seq,fun_pag_seq,fun_descricao) VALUES
        (log_funcionalidade_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.fun_seq,:new.fun_pag_seq,:new.fun_descricao);
      ELSIF (UPDATING) THEN
        INSERT INTO log_funcionalidade(id,data,operacao,usuario,ip,
        xfun_seq,xfun_pag_seq,xfun_descricao,
        fun_seq,fun_pag_seq,fun_descricao) VALUES
        (log_funcionalidade_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fun_seq,:old.fun_pag_seq,:old.fun_descricao,
        :new.fun_seq,:new.fun_pag_seq,:new.fun_descricao);
      ELSE
            INSERT INTO log_funcionalidade(id,data,operacao,usuario,ip,
            fun_seq,fun_pag_seq,fun_descricao) VALUES
        (log_funcionalidade_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fun_seq,:old.fun_pag_seq,:old.fun_descricao);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_funcionalidade_pagina (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XFPG_FUN_SEQ NUMBER,XFPG_PAG_SEQ NUMBER,FPG_FUN_SEQ NUMBER,FPG_PAG_SEQ NUMBER);

CREATE SEQUENCE log_FUNCIONALIDADE_PAGINA_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_funcionalidade_pagina_auid AFTER INSERT OR UPDATE OR DELETE ON adm_funcionalidade_pagina
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_funcionalidade_pagina(id,data,operacao,usuario,ip,
        fpg_fun_seq,fpg_pag_seq) VALUES
        (log_funcionalidade_pagina_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.fpg_fun_seq,:new.fpg_pag_seq);
      ELSIF (UPDATING) THEN
        INSERT INTO log_funcionalidade_pagina(id,data,operacao,usuario,ip,
        xfpg_fun_seq,xfpg_pag_seq,
        fpg_fun_seq,fpg_pag_seq) VALUES
        (log_funcionalidade_pagina_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fpg_fun_seq,:old.fpg_pag_seq,
        :new.fpg_fun_seq,:new.fpg_pag_seq);
      ELSE
            INSERT INTO log_funcionalidade_pagina(id,data,operacao,usuario,ip,
            fpg_fun_seq,fpg_pag_seq) VALUES
        (log_funcionalidade_pagina_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fpg_fun_seq,:old.fpg_pag_seq);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_funcionalidade_perfil (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XFPL_FUN_SEQ NUMBER,XFPL_PRF_SEQ NUMBER,FPL_FUN_SEQ NUMBER,FPL_PRF_SEQ NUMBER);
					 
CREATE SEQUENCE log_FUNCIONALIDADE_PERFIL_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_funcionalidade_perfil_auid AFTER INSERT OR UPDATE OR DELETE ON adm_funcionalidade_perfil
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_funcionalidade_perfil(id,data,operacao,usuario,ip,
        fpl_fun_seq,fpl_prf_seq) VALUES
        (log_funcionalidade_perfil_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.fpl_fun_seq,:new.fpl_prf_seq);
      ELSIF (UPDATING) THEN
        INSERT INTO log_funcionalidade_perfil(id,data,operacao,usuario,ip,
        xfpl_fun_seq,xfpl_prf_seq,
        fpl_fun_seq,fpl_prf_seq) VALUES
        (log_funcionalidade_perfil_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fpl_fun_seq,:old.fpl_prf_seq,
        :new.fpl_fun_seq,:new.fpl_prf_seq);
      ELSE
            INSERT INTO log_funcionalidade_perfil(id,data,operacao,usuario,ip,
            fpl_fun_seq,fpl_prf_seq) VALUES
        (log_funcionalidade_perfil_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fpl_fun_seq,:old.fpl_prf_seq);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_funcionario (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),					 
xfun_codigo NUMBER,xfun_nome VARCHAR2(60),xfun_cpf DECIMAL(11),xfun_email VARCHAR2(100),xfun_telefone VARCHAR2(20),xfun_celular VARCHAR2(20),
xfun_set_sigla VARCHAR2(15),xfun_car_seq NUMBER,xfun_data_admissao DATE,xfun_data_saida DATE,xfun_ativo CHAR(1),
fun_codigo NUMBER,fun_nome VARCHAR2(60),fun_cpf DECIMAL(11),fun_email VARCHAR2(100),fun_telefone VARCHAR2(20),fun_celular VARCHAR2(20),
fun_set_sigla VARCHAR2(15),fun_car_seq NUMBER,fun_data_admissao DATE,fun_data_saida DATE,fun_ativo CHAR(1));
					 
CREATE SEQUENCE log_FUNCIONARIO_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_funcionario_auid AFTER INSERT OR UPDATE OR DELETE ON adm_funcionario
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_funcionario(id,data,operacao,usuario,ip,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (log_funcionario_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.fun_codigo,:new.fun_nome,:new.fun_cpf,:new.fun_email,:new.fun_telefone,:new.fun_celular,:new.fun_set_sigla,:new.fun_car_seq,:new.fun_data_admissao,:new.fun_data_saida,:new.fun_ativo);
      ELSIF (UPDATING) THEN
        INSERT INTO log_funcionario(id,data,operacao,usuario,ip,
        xfun_codigo,xfun_nome,xfun_cpf,xfun_email,xfun_telefone,xfun_celular,xfun_set_sigla,xfun_car_seq,xfun_data_admissao,xfun_data_saida,xfun_ativo,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (log_funcionario_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fun_codigo,:old.fun_nome,:old.fun_cpf,:old.fun_email,:old.fun_telefone,:old.fun_celular,:old.fun_set_sigla,:old.fun_car_seq,:old.fun_data_admissao,:old.fun_data_saida,:old.fun_ativo,
        :new.fun_codigo,:new.fun_nome,:new.fun_cpf,:new.fun_email,:new.fun_telefone,:new.fun_celular,:new.fun_set_sigla,:new.fun_car_seq,:new.fun_data_admissao,:new.fun_data_saida,:new.fun_ativo);
      ELSE
        INSERT INTO log_funcionario(id,data,operacao,usuario,ip,
        fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
        (log_funcionario_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.fun_codigo,:old.fun_nome,:old.fun_cpf,:old.fun_email,:old.fun_telefone,:old.fun_celular,:old.fun_set_sigla,:old.fun_car_seq,:old.fun_data_admissao,:old.fun_data_saida,:old.fun_ativo);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_funcionario_perfil (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XUSP_SEQ NUMBER,XUSP_PRF_SEQ NUMBER,XUSP_FUN_CODIGO NUMBER,USP_SEQ NUMBER,USP_PRF_SEQ NUMBER,USP_FUN_CODIGO NUMBER);
					 
CREATE SEQUENCE log_FUNCIONARIO_PERFIL_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_funcionario_perfil_auid AFTER INSERT OR UPDATE OR DELETE ON adm_funcionario_perfil
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_funcionario_perfil(id,data,operacao,usuario,ip,
        usp_seq,usp_prf_seq,usp_fun_codigo) VALUES
        (log_funcionario_perfil_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.usp_seq,:new.usp_prf_seq,:new.usp_cod_funcionario);
      ELSIF (UPDATING) THEN
        INSERT INTO log_funcionario_perfil(id,data,operacao,usuario,ip,
        xusp_seq,xusp_prf_seq,xusp_fun_codigo
        usp_seq,usp_prf_seq,usp_fun_codigo) VALUES
        (log_funcionario_perfil_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.usp_seq,:old.usp_prf_seq,:old.usp_cod_funcionario,
        :new.usp_seq,:new.usp_prf_seq,:new.usp_cod_funcionario);
      ELSE
        INSERT INTO log_funcionario_perfil(id,data,operacao,usuario,ip,
        usp_seq,usp_prf_seq,usp_fun_codigo) VALUES
        (log_funcionario_perfil_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.usp_seq,:old.usp_prf_seq,:old.usp_cod_funcionario);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_log_coluna (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XCOL_NOME VARCHAR2(30),XCOL_NOME_EXIBICAO VARCHAR2(100),XCOL_COMANDO VARCHAR2(4000),COL_NOME VARCHAR2(30),COL_NOME_EXIBICAO VARCHAR2(100),COL_COMANDO VARCHAR2(4000));
					 
CREATE SEQUENCE log_LOG_COLUNA_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_log_coluna_auid AFTER INSERT OR UPDATE OR DELETE ON adm_log_coluna
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_log_coluna(id,data,operacao,usuario,ip,
        col_nome,col_nome_exibicao,col_comando) VALUES
        (log_log_coluna_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.col_nome,:new.col_nome_exibicao,:new.col_comando);
      ELSIF (UPDATING) THEN
        INSERT INTO log_log_coluna(id,data,operacao,usuario,ip,
        xcol_nome,xcol_nome_exibicao,xcol_comando,
        col_nome,col_nome_exibicao,col_comando) VALUES
        (log_log_coluna_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.col_nome,:old.col_nome_exibicao,:old.col_comando,
        :new.col_nome,:new.col_nome_exibicao,:new.col_comando);
      ELSE
            INSERT INTO log_log_coluna(id,data,operacao,usuario,ip,
            col_nome,col_nome_exibicao,col_comando) VALUES
        (log_log_coluna_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.col_nome,:old.col_nome_exibicao,:old.col_comando);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_menu (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
					 XMNU_SEQ NUMBER,XMNU_DESCRICAO VARCHAR2(255),XMNU_PAI_SEQ NUMBER,XMNU_FUN_SEQ NUMBER,XMNU_ORDEM NUMBER(3,0),
					 MNU_SEQ NUMBER,MNU_DESCRICAO VARCHAR2(255),MNU_PAI_SEQ NUMBER,MNU_FUN_SEQ NUMBER,MNU_ORDEM NUMBER(3,0));
					 
CREATE SEQUENCE log_MENU_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_menu_auid AFTER INSERT OR UPDATE OR DELETE ON adm_menu
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_menu(id,data,operacao,usuario,ip,
        mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (log_menu_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.mnu_seq,:new.mnu_descricao,:new.mnu_pai_seq,:new.mnu_fun_seq,:new.mnu_ordem);
      ELSIF (UPDATING) THEN
        INSERT INTO log_menu(id,data,operacao,usuario,ip,
        xmnu_seq,xmnu_descricao,xmnu_pai_seq,xmnu_fun_seq,xmnu_ordem,
        mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (log_menu_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.mnu_seq,:old.mnu_descricao,:old.mnu_pai_seq,:old.mnu_fun_seq,:old.mnu_ordem,
        :new.mnu_seq,:new.mnu_descricao,:new.mnu_pai_seq,:new.mnu_fun_seq,:new.mnu_ordem);
      ELSE
            INSERT INTO log_menu(id,data,operacao,usuario,ip,
            mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (log_menu_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.mnu_seq,:old.mnu_descricao,:old.mnu_pai_seq,:old.mnu_fun_seq,:old.mnu_ordem);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_pagina (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XPAG_SEQ NUMBER,XPAG_URL VARCHAR2(255),XPAG_MB VARCHAR2(255),PAG_SEQ NUMBER,PAG_URL VARCHAR2(255),PAG_MB VARCHAR2(255));
					 
CREATE SEQUENCE log_PAGINA_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_pagina_auid AFTER INSERT OR UPDATE OR DELETE ON adm_pagina
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_pagina(id,data,operacao,usuario,ip,
        pag_seq,pag_url,pag_mb) VALUES
        (log_pagina_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.pag_seq,:new.pag_url,:new.pag_mb);
      ELSIF (UPDATING) THEN
        INSERT INTO log_pagina(id,data,operacao,usuario,ip,
        xpag_seq,xpag_url,xpag_mb,
        pag_seq,pag_url,pag_mb) VALUES
        (log_pagina_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pag_seq,:old.pag_url,:old.pag_mb,
        :new.pag_seq,:new.pag_url,:new.pag_mb);
      ELSE
            INSERT INTO log_pagina(id,data,operacao,usuario,ip,
            pag_seq,pag_url,pag_mb) VALUES
        (log_pagina_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pag_seq,:old.pag_url,:old.pag_mb);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_pagina_perfil (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),XPGL_PAG_SEQ NUMBER,XPGL_PRF_SEQ NUMBER,PGL_PAG_SEQ NUMBER,PGL_PRF_SEQ NUMBER);
					 
CREATE SEQUENCE log_PAGINA_PERFIL_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_pagina_perfil_auid AFTER INSERT OR UPDATE OR DELETE ON adm_pagina_perfil
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_pagina_perfil(id,data,operacao,usuario,ip,
        pgl_pag_seq,pgl_prf_seq) VALUES
        (log_pagina_perfil_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.pgl_pag_seq,:new.pgl_prf_seq);
      ELSIF (UPDATING) THEN
        INSERT INTO log_pagina_perfil(id,data,operacao,usuario,ip,
        xpgl_pag_seq,xpgl_prf_seq,
        pgl_pag_seq,pgl_prf_seq) VALUES
        (log_pagina_perfil_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pgl_pag_seq,:old.pgl_prf_seq,
        :new.pgl_pag_seq,:new.pgl_prf_seq);
      ELSE
            INSERT INTO log_pagina_perfil(id,data,operacao,usuario,ip,
            pgl_pag_seq,pgl_prf_seq) VALUES
        (log_pagina_perfil_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pgl_pag_seq,:old.pgl_prf_seq);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_parametro (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
					 XPAR_SEQ NUMBER,XPAR_VALOR VARCHAR2(4000),XPAR_DESCRICAO VARCHAR2(255),XPAR_CODIGO VARCHAR2(255),XPAR_PMC_SEQ NUMBER,
					 PAR_SEQ NUMBER,PAR_VALOR VARCHAR2(4000),PAR_DESCRICAO VARCHAR2(255),PAR_CODIGO VARCHAR2(255),PAR_PMC_SEQ NUMBER);
					 
CREATE SEQUENCE log_PARAMETRO_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_parametro_auid AFTER INSERT OR UPDATE OR DELETE ON adm_parametro
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_parametro(id,data,operacao,usuario,ip,
        par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (log_parametro_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.par_seq,:new.par_valor,:new.par_descricao,:new.par_codigo,:new.par_pmc_seq);
      ELSIF (UPDATING) THEN
        INSERT INTO log_parametro(id,data,operacao,usuario,ip,
        xpar_seq,xpar_valor,xpar_descricao,xpar_codigo,xpar_pmc_seq,
        par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (log_parametro_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.par_seq,:old.par_valor,:old.par_descricao,:old.par_codigo,:old.par_pmc_seq,
        :new.par_seq,:new.par_valor,:new.par_descricao,:new.par_codigo,:new.par_pmc_seq);
      ELSE
            INSERT INTO log_parametro(id,data,operacao,usuario,ip,
            par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (log_parametro_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.par_seq,:old.par_valor,:old.par_descricao,:old.par_codigo,:old.par_pmc_seq);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_parametro_categoria (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
					 XPMC_SEQ NUMBER,XPMC_DESCRICAO VARCHAR2(100),XPMC_ORDEM NUMBER(2,0),
					 PMC_SEQ NUMBER,PMC_DESCRICAO VARCHAR2(100),PMC_ORDEM NUMBER(2,0));
					 
CREATE SEQUENCE log_PARAMETRO_CATEGORIA_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_parametro_categoria_auid AFTER INSERT OR UPDATE OR DELETE ON adm_parametro_categoria
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_parametro_categoria(id,data,operacao,usuario,ip,
        pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (log_parametro_categoria_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.pmc_seq,:new.pmc_descricao,:new.pmc_ordem);
      ELSIF (UPDATING) THEN
        INSERT INTO log_parametro_categoria(id,data,operacao,usuario,ip,
        xpmc_seq,xpmc_descricao,xpmc_ordem,
        pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (log_parametro_categoria_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pmc_seq,:old.pmc_descricao,:old.pmc_ordem,
        :new.pmc_seq,:new.pmc_descricao,:new.pmc_ordem);
      ELSE
            INSERT INTO log_parametro_categoria(id,data,operacao,usuario,ip,
            pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (log_parametro_categoria_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.pmc_seq,:old.pmc_descricao,:old.pmc_ordem);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_perfil (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
					 XPRF_SEQ NUMBER,XPRF_DESCRICAO VARCHAR2(255),XPRF_GERAL CHAR(1),XPRF_ADMINISTRADOR CHAR(1),
					 PRF_SEQ NUMBER,PRF_DESCRICAO VARCHAR2(255),PRF_GERAL CHAR(1),PRF_ADMINISTRADOR CHAR(1));
					 
CREATE SEQUENCE log_PERFIL_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_perfil_auid AFTER INSERT OR UPDATE OR DELETE ON adm_perfil
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_perfil(id,data,operacao,usuario,ip,
        prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (log_perfil_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.prf_seq,:new.prf_descricao,:new.prf_geral,:new.prf_administrador);
      ELSIF (UPDATING) THEN
        INSERT INTO log_perfil(id,data,operacao,usuario,ip,
        xprf_seq,xprf_descricao,xprf_geral,xprf_administrador,
        prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (log_perfil_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.prf_seq,:old.prf_descricao,:old.prf_geral,:old.prf_administrador,
        :new.prf_seq,:new.prf_descricao,:new.prf_geral,:new.prf_administrador);
      ELSE
            INSERT INTO log_perfil(id,data,operacao,usuario,ip,
            prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (log_perfil_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.prf_seq,:old.prf_descricao,:old.prf_geral,:old.prf_administrador);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

CREATE TABLE log_setor (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),
xset_sigla VARCHAR2(15),xset_nome VARCHAR2(50),xset_pai  VARCHAR2(15),xset_tipo VARCHAR2(20),
set_sigla VARCHAR2(15),set_nome VARCHAR2(50),set_pai  VARCHAR2(15),set_tipo VARCHAR2(20));
					 
CREATE SEQUENCE log_setor_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;

CREATE OR REPLACE TRIGGER trg_setor_auid AFTER INSERT OR UPDATE OR DELETE ON adm_setor
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO log_setor(id,data,operacao,usuario,ip,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (log_setor_seq.nextval, SYSDATE, 'I', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :new.set_sigla,:new.set_nome,:new.set_pai,:new.set_tipo);
      ELSIF (UPDATING) THEN
        INSERT INTO log_setor(id,data,operacao,usuario,ip,
        xset_sigla,xset_nome,xset_pai,xset_tipo,,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (log_setor_seq.nextval, SYSDATE, 'U', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.set_sigla,:old.set_nome,:old.set_pai,:old.set_tipo,
        :new.set_sigla,:new.set_nome,:new.set_pai,:new.set_tipo);
      ELSE
        INSERT INTO log_setor(id,data,operacao,usuario,ip,
        set_sigla,set_nome,set_pai,set_tipo) VALUES
        (log_setor_seq.nextval, SYSDATE, 'D', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)),
        :old.set_sigla,:old.set_nome,:old.set_pai,:old.set_tipo);
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;

