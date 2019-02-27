CREATE TABLE public.log_adm_cargo
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xcar_seq bigint,
  xcar_descricao character varying(60),
  car_seq bigint,
  car_descricao character varying(60),
  CONSTRAINT log_adm_cargo_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_cargo_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_cargo_aiud()
  RETURNS trigger AS
$BODY$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO public.log_adm_cargo(id,data,operacao,usuario,ip,
    car_seq,car_descricao) VALUES
    (nextval('public.log_adm_cargo_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    new.car_seq,new.car_descricao);
    RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO public.log_adm_cargo(id,data,operacao,usuario,ip,xcar_seq,xcar_descricao,
    car_seq,car_descricao) VALUES
    (nextval('public.log_adm_cargo_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.car_seq,old.car_descricao,
    new.car_seq,new.car_descricao);
    RETURN NEW;
  ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO public.log_adm_cargo(id,data,operacao,usuario,ip,car_seq,car_descricao)
 VALUES
    (nextval('public.log_adm_cargo_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.car_seq,old.car_descricao);
    RETURN OLD;
  END IF;
  RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_cargo_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_cargo
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_cargo_aiud();
  
  
CREATE TABLE public.log_adm_cargo_funcionario
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xcfn_data_exercicio timestamp without time zone,
  xcfn_fun_codigo bigint,
  xcfn_car_seq bigint,
  xcfn_data_desligamento timestamp without time zone,
  xcfn_data_posse timestamp without time zone,
  xcfn_situacao character varying(1),
  xcfn_presidente character(1),
  xcfn_diretor_geral character varying(1),
  xcfn_responsavel_orcamento character(1),
  xcfn_chefe_sepo character(1),
  cfn_data_exercicio timestamp without time zone,
  cfn_fun_codigo bigint,
  cfn_car_seq bigint,
  cfn_data_desligamento timestamp without time zone,
  cfn_data_posse timestamp without time zone,
  cfn_situacao character varying(1),
  cfn_presidente character(1),
  cfn_diretor_geral character varying(1),
  cfn_responsavel_orcamento character(1),
  cfn_chefe_sepo character(1),
  CONSTRAINT log_adm_cargo_funcionario_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_cargo_funcionario_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_cargo_funcionario_aiud()
  RETURNS trigger AS
$BODY$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO public.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,
    cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
    (nextval('public.log_adm_cargo_funcionario_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    new.cfn_data_exercicio,new.cfn_fun_codigo,new.cfn_car_seq,new.cfn_data_desligamento,new.cfn_data_posse,new.cfn_situacao,new.cfn_presidente,new.cfn_diretor_geral,new.cfn_responsavel_orcamento,new.cfn_chefe_sepo);
    RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO public.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,xcfn_data_exercicio,xcfn_fun_codigo,xcfn_car_seq,xcfn_data_desligamento,xcfn_data_posse,xcfn_situacao,xcfn_presidente,xcfn_diretor_geral,xcfn_responsavel_orcamento,xcfn_chefe_sepo,
    cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo) VALUES
    (nextval('public.log_adm_cargo_funcionario_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.cfn_data_exercicio,old.cfn_fun_codigo,old.cfn_car_seq,old.cfn_data_desligamento,old.cfn_data_posse,old.cfn_situacao,old.cfn_presidente,old.cfn_diretor_geral,old.cfn_responsavel_orcamento,old.cfn_chefe_sepo,
    new.cfn_data_exercicio,new.cfn_fun_codigo,new.cfn_car_seq,new.cfn_data_desligamento,new.cfn_data_posse,new.cfn_situacao,new.cfn_presidente,new.cfn_diretor_geral,new.cfn_responsavel_orcamento,new.cfn_chefe_sepo);
    RETURN NEW;
  ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO public.log_adm_cargo_funcionario(id,data,operacao,usuario,ip,cfn_data_exercicio,cfn_fun_codigo,cfn_car_seq,cfn_data_desligamento,cfn_data_posse,cfn_situacao,cfn_presidente,cfn_diretor_geral,cfn_responsavel_orcamento,cfn_chefe_sepo)
 VALUES
    (nextval('public.log_adm_cargo_funcionario_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.cfn_data_exercicio,old.cfn_fun_codigo,old.cfn_car_seq,old.cfn_data_desligamento,old.cfn_data_posse,old.cfn_situacao,old.cfn_presidente,old.cfn_diretor_geral,old.cfn_responsavel_orcamento,old.cfn_chefe_sepo);
    RETURN OLD;
  END IF;
  RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_cargo_funcionario_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_cargo_funcionario
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_cargo_funcionario_aiud();
  
  
CREATE TABLE public.log_adm_cargo_perfil
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xcgp_car_seq bigint,
  xcgp_prf_seq bigint,
  cgp_car_seq bigint,
  cgp_prf_seq bigint,
  CONSTRAINT log_adm_cargo_perfil_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_cargo_perfil_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_cargo_perfil_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_cargo_perfil(id,data,operacao,usuario,ip,cgp_car_seq,cgp_prf_seq) VALUES
        (nextval('public.log_adm_cargo_perfil_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.cgp_cod_cargo,new.cgp_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_cargo_perfil(id,data,operacao,usuario,ip,xcgp_car_seq,xcgp_prf_seq,cgp_car_seq,cgp_prf_seq) VALUES
        (nextval('public.log_adm_cargo_perfil_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.cgp_cod_cargo,old.cgp_prf_seq,
        new.cgp_cod_cargo,new.cgp_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_cargo_perfil(id,data,operacao,usuario,ip,cgp_car_seq,cgp_prf_seq) VALUES 
        (nextval('public.log_adm_cargo_perfil_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.cgp_cod_cargo,old.cgp_prf_seq);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_cargo_perfil_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_cargo_perfil
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_cargo_perfil_aiud();

CREATE TABLE public.log_adm_funcionalidade
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xfun_seq bigint,
  xfun_pag_seq bigint,
  xfun_descricao character varying(255),
  fun_seq bigint,
  fun_pag_seq bigint,
  fun_descricao character varying(255),
  CONSTRAINT log_adm_funcionalidade_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_funcionalidade_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 4
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_funcionalidade_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_funcionalidade(id,data,operacao,usuario,ip,fun_seq,fun_pag_seq,fun_descricao) VALUES
        (nextval('public.log_adm_funcionalidade_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.fun_seq,new.fun_pag_seq,new.fun_descricao);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_funcionalidade(id,data,operacao,usuario,ip,xfun_seq,xfun_pag_seq,xfun_descricao,fun_seq,fun_pag_seq,fun_descricao) VALUES
        (nextval('public.log_adm_funcionalidade_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fun_seq,old.fun_pag_seq,old.fun_descricao,
        new.fun_seq,new.fun_pag_seq,new.fun_descricao);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_funcionalidade(id,data,operacao,usuario,ip,fun_seq,fun_pag_seq,fun_descricao) VALUES 
        (nextval('public.log_adm_funcionalidade_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fun_seq,old.fun_pag_seq,old.fun_descricao);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_funcionalidade_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_funcionalidade
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_funcionalidade_aiud();


CREATE TABLE public.log_adm_funcionalidade_pagina
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xfpg_fun_seq bigint,
  xfpg_pag_seq bigint,
  fpg_fun_seq bigint,
  fpg_pag_seq bigint,
  CONSTRAINT log_adm_funcionalidade_pagina_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_funcionalidade_pagina_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 4
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_funcionalidade_pagina_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,fpg_fun_seq,fpg_pag_seq) VALUES
        (nextval('public.log_adm_funcionalidade_pagina_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.fpg_fun_seq,new.fpg_pag_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,xfpg_fun_seq,xfpg_pag_seq,fpg_fun_seq,fpg_pag_seq) VALUES
        (nextval('public.log_adm_funcionalidade_pagina_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fpg_fun_seq,old.fpg_pag_seq,
        new.fpg_fun_seq,new.fpg_pag_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_funcionalidade_pagina(id,data,operacao,usuario,ip,fpg_fun_seq,fpg_pag_seq) VALUES 
        (nextval('public.log_adm_funcionalidade_pagina_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fpg_fun_seq,old.fpg_pag_seq);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_funcionalidade_pagina_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_funcionalidade_pagina
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_funcionalidade_pagina_aiud();
  
  
CREATE TABLE public.log_adm_funcionalidade_perfil
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xfpl_fun_seq bigint,
  xfpl_prf_seq bigint,
  fpl_fun_seq bigint,
  fpl_prf_seq bigint,
  CONSTRAINT log_adm_funcionalidade_perfil_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_funcionalidade_perfil_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 4
  CACHE 1
  CYCLE;

CREATE OR REPLACE FUNCTION public.adm_funcionalidade_perfil_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,fpl_fun_seq,fpl_prf_seq) VALUES
        (nextval('public.log_adm_funcionalidade_perfil_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.fpl_fun_seq,new.fpl_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,xfpl_fun_seq,xfpl_prf_seq,fpl_fun_seq,fpl_prf_seq) VALUES
        (nextval('public.log_adm_funcionalidade_perfil_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fpl_fun_seq,old.fpl_prf_seq,
        new.fpl_fun_seq,new.fpl_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_funcionalidade_perfil(id,data,operacao,usuario,ip,fpl_fun_seq,fpl_prf_seq) VALUES 
        (nextval('public.log_adm_funcionalidade_perfil_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.fpl_fun_seq,old.fpl_prf_seq);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_funcionalidade_perfil_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_funcionalidade_perfil
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_funcionalidade_perfil_aiud();
  

CREATE TABLE public.log_adm_funcionario
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xfun_codigo bigint,
  xfun_nome character varying(60),
  xfun_cpf numeric(11,0),
  xfun_email character varying(100),
  xfun_telefone character varying(20),
  xfun_celular character varying(20),
  xfun_set_sigla character varying(15),
  xfun_car_seq bigint,
  xfun_data_admissao date,
  xfun_data_saida date,
  xfun_ativo character(1),
  fun_codigo bigint,
  fun_nome character varying(60),
  fun_cpf numeric(11,0),
  fun_email character varying(100),
  fun_telefone character varying(20),
  fun_celular character varying(20),
  fun_set_sigla character varying(15),
  fun_car_seq bigint,
  fun_data_admissao date,
  fun_data_saida date,
  fun_ativo character(1),
  CONSTRAINT log_adm_funcionario_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_funcionario_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;

CREATE OR REPLACE FUNCTION public.adm_funcionario_aiud()
  RETURNS trigger AS
$BODY$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO public.log_adm_funcionario(id,data,operacao,usuario,ip,
    fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
    (nextval('public.log_adm_funcionario_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    new.fun_codigo,new.fun_nome,new.fun_cpf,new.fun_email,new.fun_telefone,new.fun_celular,new.fun_set_sigla,new.fun_car_seq,new.fun_data_admissao,new.fun_data_saida,new.fun_ativo);
    RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO public.log_adm_funcionario(id,data,operacao,usuario,ip,xfun_codigo,xfun_nome,xfun_cpf,xfun_email,xfun_telefone,xfun_celular,xfun_set_sigla,xfun_car_seq,xfun_data_admissao,xfun_data_saida,xfun_ativo,
    fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo) VALUES
    (nextval('public.log_adm_funcionario_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.fun_codigo,old.fun_nome,old.fun_cpf,old.fun_email,old.fun_telefone,old.fun_celular,old.fun_set_sigla,old.fun_car_seq,old.fun_data_admissao,old.fun_data_saida,old.fun_ativo,
    new.fun_codigo,new.fun_nome,new.fun_cpf,new.fun_email,new.fun_telefone,new.fun_celular,new.fun_set_sigla,new.fun_car_seq,new.fun_data_admissao,new.fun_data_saida,new.fun_ativo);
    RETURN NEW;
  ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO public.log_adm_funcionario(id,data,operacao,usuario,ip,fun_codigo,fun_nome,fun_cpf,fun_email,fun_telefone,fun_celular,fun_set_sigla,fun_car_seq,fun_data_admissao,fun_data_saida,fun_ativo)
 VALUES
    (nextval('public.log_adm_funcionario_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.fun_codigo,old.fun_nome,old.fun_cpf,old.fun_email,old.fun_telefone,old.fun_celular,old.fun_set_sigla,old.fun_car_seq,old.fun_data_admissao,old.fun_data_saida,old.fun_ativo);
    RETURN OLD;
  END IF;
  RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  

CREATE TRIGGER trg_adm_funcionario_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_funcionario
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_funcionario_aiud();
  
  
CREATE TABLE public.log_adm_funcionario_perfil
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xusp_seq bigint,
  xusp_prf_seq bigint,
  xusp_fun_codigo bigint,
  usp_seq bigint,
  usp_prf_seq bigint,
  usp_fun_codigo bigint,
  CONSTRAINT log_adm_funcionario_perfil_pkey PRIMARY KEY (id)
);
 
CREATE SEQUENCE public.log_adm_funcionario_perfil_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
 
CREATE OR REPLACE FUNCTION public.adm_funcionario_perfil_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,usp_seq,usp_prf_seq,usp_fun_codigo) VALUES
        (nextval('public.log_adm_funcionario_perfil_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.usp_seq,new.usp_prf_seq,new.usp_cod_funcionario);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,xusp_seq,xusp_prf_seq,xusp_fun_codigo,usp_seq,usp_prf_seq,usp_fun_codigo) VALUES
        (nextval('public.log_adm_funcionario_perfil_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.usp_seq,old.usp_prf_seq,old.usp_cod_funcionario,
        new.usp_seq,new.usp_prf_seq,new.usp_cod_funcionario);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_funcionario_perfil(id,data,operacao,usuario,ip,usp_seq,usp_prf_seq,usp_fun_codigo) VALUES 
        (nextval('public.log_adm_funcionario_perfil_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.usp_seq,old.usp_prf_seq,old.usp_cod_funcionario);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
 
CREATE TRIGGER trg_adm_funcionario_perfil_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_funcionario_perfil
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_funcionario_perfil_aiud();

  
CREATE TABLE public.log_adm_log_coluna
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xcol_nome character varying(30),
  xcol_nome_exibicao character varying(100),
  xcol_comando character varying(4000),
  col_nome character varying(30),
  col_nome_exibicao character varying(100),
  col_comando character varying(4000),
  CONSTRAINT log_adm_log_coluna_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_log_coluna_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;

CREATE OR REPLACE FUNCTION public.adm_log_coluna_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_log_coluna(id,data,operacao,usuario,ip,col_nome,col_nome_exibicao,col_comando) VALUES
        (nextval('public.log_adm_log_coluna_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.col_nome,new.col_nome_exibicao,new.col_comando);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_log_coluna(id,data,operacao,usuario,ip,xcol_nome,xcol_nome_exibicao,xcol_comando,col_nome,col_nome_exibicao,col_comando) VALUES
        (nextval('public.log_adm_log_coluna_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.col_nome,old.col_nome_exibicao,old.col_comando,
        new.col_nome,new.col_nome_exibicao,new.col_comando);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_log_coluna(id,data,operacao,usuario,ip,col_nome,col_nome_exibicao,col_comando) VALUES 
        (nextval('public.log_adm_log_coluna_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.col_nome,old.col_nome_exibicao,old.col_comando);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_log_coluna_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_log_coluna
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_log_coluna_aiud();

  
CREATE TABLE public.log_adm_menu
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xmnu_seq bigint,
  xmnu_descricao character varying(255),
  xmnu_pai_seq bigint,
  xmnu_fun_seq bigint,
  xmnu_ordem numeric(3,0),
  mnu_seq bigint,
  mnu_descricao character varying(255),
  mnu_pai_seq bigint,
  mnu_fun_seq bigint,
  mnu_ordem numeric(3,0),
  CONSTRAINT log_adm_menu_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_menu_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 12
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_menu_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_menu(id,data,operacao,usuario,ip,mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (nextval('public.log_adm_menu_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.mnu_seq,new.mnu_descricao,new.mnu_pai_seq,new.mnu_fun_seq,new.mnu_ordem);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_menu(id,data,operacao,usuario,ip,xmnu_seq,xmnu_descricao,xmnu_pai_seq,xmnu_fun_seq,xmnu_ordem,mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES
        (nextval('public.log_adm_menu_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.mnu_seq,old.mnu_descricao,old.mnu_pai_seq,old.mnu_fun_seq,old.mnu_ordem,
        new.mnu_seq,new.mnu_descricao,new.mnu_pai_seq,new.mnu_fun_seq,new.mnu_ordem);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_menu(id,data,operacao,usuario,ip,mnu_seq,mnu_descricao,mnu_pai_seq,mnu_fun_seq,mnu_ordem) VALUES 
        (nextval('public.log_adm_menu_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.mnu_seq,old.mnu_descricao,old.mnu_pai_seq,old.mnu_fun_seq,old.mnu_ordem);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_menu_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_menu
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_menu_aiud();

  
CREATE TABLE public.log_adm_pagina
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xpag_seq bigint,
  xpag_url character varying(255),
  xpag_mb character varying(255),
  pag_seq bigint,
  pag_url character varying(255),
  pag_mb character varying(255),
  CONSTRAINT log_adm_pagina_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_pagina_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 5
  CACHE 1
  CYCLE;

CREATE OR REPLACE FUNCTION public.adm_pagina_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_pagina(id,data,operacao,usuario,ip,pag_seq,pag_url,pag_mb) VALUES
        (nextval('public.log_adm_pagina_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.pag_seq,new.pag_url,new.pag_mb);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_pagina(id,data,operacao,usuario,ip,xpag_seq,xpag_url,xpag_mb,pag_seq,pag_url,pag_mb) VALUES
        (nextval('public.log_adm_pagina_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pag_seq,old.pag_url,old.pag_mb,
        new.pag_seq,new.pag_url,new.pag_mb);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_pagina(id,data,operacao,usuario,ip,pag_seq,pag_url,pag_mb) VALUES 
        (nextval('public.log_adm_pagina_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pag_seq,old.pag_url,old.pag_mb);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE TRIGGER trg_adm_pagina_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_pagina
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_pagina_aiud();


CREATE TABLE public.log_adm_pagina_perfil
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xpgl_pag_seq bigint,
  xpgl_prf_seq bigint,
  pgl_pag_seq bigint,
  pgl_prf_seq bigint,
  CONSTRAINT log_adm_pagina_perfil_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_pagina_perfil_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 4
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_pagina_perfil_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_pagina_perfil(id,data,operacao,usuario,ip,pgl_pag_seq,pgl_prf_seq) VALUES
        (nextval('public.log_adm_pagina_perfil_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.pgl_pag_seq,new.pgl_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_pagina_perfil(id,data,operacao,usuario,ip,xpgl_pag_seq,xpgl_prf_seq,pgl_pag_seq,pgl_prf_seq) VALUES
        (nextval('public.log_adm_pagina_perfil_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pgl_pag_seq,old.pgl_prf_seq,
        new.pgl_pag_seq,new.pgl_prf_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_pagina_perfil(id,data,operacao,usuario,ip,pgl_pag_seq,pgl_prf_seq) VALUES 
        (nextval('public.log_adm_pagina_perfil_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pgl_pag_seq,old.pgl_prf_seq);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_pagina_perfil_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_pagina_perfil
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_pagina_perfil_aiud();


CREATE TABLE public.log_adm_parametro
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xpar_seq bigint,
  xpar_valor character varying(4000),
  xpar_descricao character varying(255),
  xpar_codigo character varying(255),
  xpar_pmc_seq bigint,
  par_seq bigint,
  par_valor character varying(4000),
  par_descricao character varying(255),
  par_codigo character varying(255),
  par_pmc_seq bigint,
  CONSTRAINT log_adm_parametro_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_parametro_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_parametro_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_parametro(id,data,operacao,usuario,ip,par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (nextval('public.log_adm_parametro_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.par_seq,new.par_valor,new.par_descricao,new.par_codigo,new.par_pmc_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_parametro(id,data,operacao,usuario,ip,xpar_seq,xpar_valor,xpar_descricao,xpar_codigo,xpar_pmc_seq,par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES
        (nextval('public.log_adm_parametro_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.par_seq,old.par_valor,old.par_descricao,old.par_codigo,old.par_pmc_seq,
        new.par_seq,new.par_valor,new.par_descricao,new.par_codigo,new.par_pmc_seq);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_parametro(id,data,operacao,usuario,ip,par_seq,par_valor,par_descricao,par_codigo,par_pmc_seq) VALUES 
        (nextval('public.log_adm_parametro_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.par_seq,old.par_valor,old.par_descricao,old.par_codigo,old.par_pmc_seq);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_parametro_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_parametro
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_parametro_aiud();

  
CREATE TABLE public.log_adm_parametro_categoria
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xpmc_seq bigint,
  xpmc_descricao character varying(100),
  xpmc_ordem numeric(2,0),
  pmc_seq bigint,
  pmc_descricao character varying(100),
  pmc_ordem numeric(2,0),
  CONSTRAINT log_adm_parametro_categoria_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_parametro_categoria_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_parametro_categoria_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_parametro_categoria(id,data,operacao,usuario,ip,pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (nextval('public.log_adm_parametro_categoria_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.pmc_seq,new.pmc_descricao,new.pmc_ordem);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_parametro_categoria(id,data,operacao,usuario,ip,xpmc_seq,xpmc_descricao,xpmc_ordem,pmc_seq,pmc_descricao,pmc_ordem) VALUES
        (nextval('public.log_adm_parametro_categoria_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pmc_seq,old.pmc_descricao,old.pmc_ordem,
        new.pmc_seq,new.pmc_descricao,new.pmc_ordem);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_parametro_categoria(id,data,operacao,usuario,ip,pmc_seq,pmc_descricao,pmc_ordem) VALUES 
        (nextval('public.log_adm_parametro_categoria_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.pmc_seq,old.pmc_descricao,old.pmc_ordem);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_parametro_categoria_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_parametro_categoria
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_parametro_categoria_aiud();

  
CREATE TABLE public.log_adm_perfil
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xprf_seq bigint,
  xprf_descricao character varying(255),
  xprf_geral character(1),
  xprf_administrador character(1),
  prf_seq bigint,
  prf_descricao character varying(255),
  prf_geral character(1),
  prf_administrador character(1),
  CONSTRAINT log_adm_perfil_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_perfil_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;

CREATE OR REPLACE FUNCTION public.adm_perfil_aiud()
  RETURNS trigger AS
$BODY$ 
    BEGIN
      IF (TG_OP = 'INSERT') THEN
        INSERT INTO public.log_adm_perfil(id,data,operacao,usuario,ip,prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (nextval('public.log_adm_perfil_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        new.prf_seq,new.prf_descricao,new.prf_geral,new.prf_administrador);
        RETURN NEW;
      ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO public.log_adm_perfil(id,data,operacao,usuario,ip,xprf_seq,xprf_descricao,xprf_geral,xprf_administrador,prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES
        (nextval('public.log_adm_perfil_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.prf_seq,old.prf_descricao,old.prf_geral,old.prf_administrador,
        new.prf_seq,new.prf_descricao,new.prf_geral,new.prf_administrador);
        RETURN NEW;
      ELSIF (TG_OP = 'DELETE') THEN
            INSERT INTO public.log_adm_perfil(id,data,operacao,usuario,ip,prf_seq,prf_descricao,prf_geral,prf_administrador) VALUES 
        (nextval('public.log_adm_perfil_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user), 
        coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
        old.prf_seq,old.prf_descricao,old.prf_geral,old.prf_administrador);
        RETURN OLD;
      END IF;
      RETURN NULL;                   
    END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_perfil_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_perfil
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_perfil_aiud();

  
CREATE TABLE public.log_adm_setor
(
  id bigint NOT NULL,
  usuario character varying(30),
  data timestamp(0) without time zone,
  operacao character(1),
  ip character varying(50),
  xset_sigla character varying(15),
  xset_nome character varying(50),
  xset_pai character varying(15),
  xset_tipo character varying(20),
  set_sigla character varying(15),
  set_nome character varying(50),
  set_pai character varying(15),
  set_tipo character varying(20),
  CONSTRAINT log_adm_setor_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.log_adm_setor_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
  
CREATE OR REPLACE FUNCTION public.adm_setor_aiud()
  RETURNS trigger AS
$BODY$
BEGIN
  IF (TG_OP = 'INSERT') THEN
    INSERT INTO public.log_adm_setor(id,data,operacao,usuario,ip,
    set_sigla,set_nome,set_pai,set_tipo) VALUES
    (nextval('public.log_adm_setor_seq'), current_timestamp, 'I', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    new.set_sigla,new.set_nome,new.set_pai,new.set_tipo);
    RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO public.log_adm_setor(id,data,operacao,usuario,ip,xset_sigla,xset_nome,xset_pai,xset_tipo,
    set_sigla,set_nome,set_pai,set_tipo) VALUES
    (nextval('public.log_adm_setor_seq'), current_timestamp, 'U', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.set_sigla,old.set_nome,old.set_pai,old.set_tipo,
    new.set_sigla,new.set_nome,new.set_pai,new.set_tipo);
    RETURN NEW;
  ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO public.log_adm_setor(id,data,operacao,usuario,ip,set_sigla,set_nome,set_pai,set_tipo)
 VALUES
    (nextval('public.log_adm_setor_seq'), current_timestamp, 'D', coalesce(pkg_adm_obter_usuario(), session_user),
    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)),
    old.set_sigla,old.set_nome,old.set_pai,old.set_tipo);
    RETURN OLD;
  END IF;
  RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE TRIGGER trg_adm_setor_auid
  AFTER INSERT OR UPDATE OR DELETE
  ON public.adm_setor
  FOR EACH ROW
  EXECUTE PROCEDURE public.adm_setor_aiud();

  