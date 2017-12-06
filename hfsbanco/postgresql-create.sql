-- Database: hfsbanco

-- DROP DATABASE hfsbanco;

CREATE DATABASE hfsbanco
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

	   
CREATE TABLE public.adm_perfil
(
  prf_seq bigint NOT NULL,
  prf_descricao character varying(255),
  prf_geral character(1) DEFAULT 'N'::bpchar,
  prf_administrador character(1) DEFAULT 'N'::bpchar,
  CONSTRAINT adm_perfil_pkey PRIMARY KEY (prf_seq),
  CONSTRAINT adm_perfil_un UNIQUE (prf_descricao)
);
	   
CREATE TABLE public.adm_pagina
(
  pag_seq bigint NOT NULL,
  pag_url character varying(255) NOT NULL,
  pag_mb character varying(255),
  CONSTRAINT adm_pagina_pkey PRIMARY KEY (pag_seq),
  CONSTRAINT adm_pagina_un UNIQUE (pag_url)
);

CREATE TABLE public.adm_funcionalidade
(
  fun_seq bigint NOT NULL,
  fun_pag_seq bigint NOT NULL,
  fun_descricao character varying(255) NOT NULL,
  CONSTRAINT adm_funcionalidade_pkey PRIMARY KEY (fun_seq),
  CONSTRAINT adm_funcionalidade_fun_pag_seq_fkey FOREIGN KEY (fun_pag_seq)
      REFERENCES public.adm_pagina (pag_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionalidade_un UNIQUE (fun_descricao)
);

CREATE TABLE public.adm_funcionalidade_pagina
(
  fpg_fun_seq bigint NOT NULL,
  fpg_pag_seq bigint NOT NULL,
  CONSTRAINT adm_funcionalidade_pagina_pkey PRIMARY KEY (fpg_fun_seq, fpg_pag_seq),
  CONSTRAINT adm_funcionalidade_pagina_fpg_fun_seq_fkey FOREIGN KEY (fpg_fun_seq)
      REFERENCES public.adm_funcionalidade (fun_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionalidade_pagina_fpg_pag_seq_fkey FOREIGN KEY (fpg_pag_seq)
      REFERENCES public.adm_pagina (pag_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.adm_funcionalidade_perfil
(
  fpl_fun_seq bigint NOT NULL,
  fpl_prf_seq bigint NOT NULL,
  CONSTRAINT adm_funcionalidade_perfil_pkey PRIMARY KEY (fpl_fun_seq, fpl_prf_seq),
  CONSTRAINT adm_funcionalidade_perfil_fpl_fun_seq_fkey FOREIGN KEY (fpl_fun_seq)
      REFERENCES public.adm_funcionalidade (fun_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionalidade_perfil_fpl_prf_seq_fkey FOREIGN KEY (fpl_prf_seq)
      REFERENCES public.adm_perfil (prf_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.adm_log_coluna
(
  col_nome character varying(30) NOT NULL,
  col_nome_exibicao character varying(100) NOT NULL,
  col_comando character varying(4000),
  CONSTRAINT adm_log_coluna_pkey PRIMARY KEY (col_nome)
);

CREATE TABLE public.adm_menu
(
  mnu_seq bigint NOT NULL,
  mnu_descricao character varying(255) NOT NULL,
  mnu_pai_seq bigint,
  mnu_fun_seq bigint,
  mnu_ordem numeric(3,0),
  CONSTRAINT adm_menu_pkey PRIMARY KEY (mnu_seq),
  CONSTRAINT adm_menu_mnu_fun_seq_fkey FOREIGN KEY (mnu_fun_seq)
      REFERENCES public.adm_funcionalidade (fun_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_menu_mnu_pai_seq_fkey FOREIGN KEY (mnu_pai_seq)
      REFERENCES public.adm_menu (mnu_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_menu_un UNIQUE (mnu_descricao)
);

CREATE TABLE public.adm_pagina_perfil
(
  pgl_pag_seq bigint NOT NULL,
  pgl_prf_seq bigint NOT NULL,
  CONSTRAINT adm_pagina_perfil_pkey PRIMARY KEY (pgl_pag_seq, pgl_prf_seq),
  CONSTRAINT adm_pagina_perfil_pgl_pag_seq_fkey FOREIGN KEY (pgl_pag_seq)
      REFERENCES public.adm_pagina (pag_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_pagina_perfil_pgl_prf_seq_fkey FOREIGN KEY (pgl_prf_seq)
      REFERENCES public.adm_perfil (prf_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.adm_parametro_categoria
(
  pmc_seq bigint NOT NULL,
  pmc_descricao character varying(100) NOT NULL,
  pmc_ordem numeric(2,0),
  CONSTRAINT adm_parametro_categoria_pkey PRIMARY KEY (pmc_seq),
  CONSTRAINT adm_parametro_categoria_un UNIQUE (pmc_descricao)
);

CREATE TABLE public.adm_parametro
(
  par_seq bigint NOT NULL,
  par_valor character varying(4000),
  par_descricao character varying(255) NOT NULL,
  par_codigo character varying(255) NOT NULL,
  par_pmc_seq bigint NOT NULL,
  CONSTRAINT adm_parametro_pkey PRIMARY KEY (par_seq),
  CONSTRAINT adm_parametro_par_pmc_seq_fkey FOREIGN KEY (par_pmc_seq)
      REFERENCES public.adm_parametro_categoria (pmc_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_parametro_un UNIQUE (par_descricao),
  CONSTRAINT uk_adm_parametro UNIQUE (par_codigo)
);

CREATE TABLE public.adm_usuario
(
  usu_matricula bigint NOT NULL,
  usu_ip character varying(15) NOT NULL,
  usu_login character varying(60) NOT NULL,
  usu_nome character varying(60) NOT NULL,
  usu_data timestamp(0) without time zone NOT NULL,
  usu_cpf numeric(11,0),
  usu_email character varying(255),
  usu_ldap_dn character varying(255),
  usu_senha character varying(128),
  CONSTRAINT adm_usuario_pkey PRIMARY KEY (usu_matricula, usu_ip)
);

CREATE TABLE public.adm_setor
(
  set_sigla character varying(15) NOT NULL,
  set_nome character varying(50),
  set_pai character varying(15),
  set_tipo character varying(20),
  CONSTRAINT adm_setor_pkey PRIMARY KEY (set_sigla)
);

CREATE TABLE public.adm_cargo
(
  car_seq bigint NOT NULL,
  car_descricao character varying(60) NOT NULL,
  CONSTRAINT adm_cargo_pkey PRIMARY KEY (car_seq),
  CONSTRAINT adm_cargo_un UNIQUE (car_descricao)
);

CREATE TABLE public.adm_cargo_perfil
(
  cgp_car_seq bigint NOT NULL,
  cgp_prf_seq bigint NOT NULL,
  CONSTRAINT adm_cargo_perfil_pkey PRIMARY KEY (cgp_car_seq, cgp_prf_seq),
  CONSTRAINT adm_cargo_perfil_cgp_car_seq_fkey FOREIGN KEY (cgp_car_seq)
      REFERENCES public.adm_cargo (car_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_cargo_perfil_cgp_prf_seq_fkey FOREIGN KEY (cgp_prf_seq)
      REFERENCES public.adm_perfil (prf_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.adm_funcionario
(
  fun_codigo bigint NOT NULL,
  fun_nome character varying(60) NOT NULL,
  fun_cpf numeric(11,0),
  fun_email character varying(100),
  fun_telefone character varying(20),
  fun_celular character varying(20),
  fun_set_sigla character varying(15) NOT NULL,
  fun_car_seq bigint NOT NULL,
  fun_data_admissao date,
  fun_data_saida date,
  fun_ativo character(1),
  CONSTRAINT adm_funcionario_pkey PRIMARY KEY (fun_codigo),
  CONSTRAINT adm_funcionario_fun_car_seq_fkey FOREIGN KEY (fun_car_seq)
      REFERENCES public.adm_cargo (car_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionario_fun_set_sigla_fkey FOREIGN KEY (fun_set_sigla)
      REFERENCES public.adm_setor (set_sigla) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionario_un UNIQUE (fun_nome)
);

CREATE TABLE public.adm_funcionario_perfil
(
  usp_prf_seq bigint NOT NULL,
  usp_fun_codigo bigint NOT NULL,
  CONSTRAINT adm_funcionario_perfil_pkey PRIMARY KEY (usp_prf_seq, usp_fun_codigo),
  CONSTRAINT adm_funcionario_perfil_usp_fun_codigo_fkey FOREIGN KEY (usp_fun_codigo)
      REFERENCES public.adm_funcionario (fun_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_funcionario_perfil_usp_prf_seq_fkey FOREIGN KEY (usp_prf_seq)
      REFERENCES public.adm_perfil (prf_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.adm_cargo_funcionario
(
  cfn_data_exercicio timestamp(0) without time zone,
  cfn_fun_codigo bigint NOT NULL,
  cfn_car_seq bigint NOT NULL,
  cfn_data_desligamento timestamp(0) without time zone,
  cfn_data_posse timestamp(0) without time zone,
  cfn_situacao character varying(1),
  cfn_presidente character(1),
  cfn_diretor_geral character varying(1),
  cfn_responsavel_orcamento character(1),
  cfn_chefe_sepo character(1),
  CONSTRAINT adm_cargo_funcionario_pkey PRIMARY KEY (cfn_car_seq, cfn_fun_codigo),
  CONSTRAINT adm_cargo_funcionario_cfn_car_seq_fkey FOREIGN KEY (cfn_car_seq)
      REFERENCES public.adm_cargo (car_seq) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adm_cargo_funcionario_cfn_fun_codigo_fkey FOREIGN KEY (cfn_fun_codigo)
      REFERENCES public.adm_funcionario (fun_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
