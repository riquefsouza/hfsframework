create table ADM_PERFIL
(
  prf_seq           BIGINT not null,
  prf_descricao     VARCHAR(255),
  prf_geral         CHAR(1) default 'N',
  prf_administrador CHAR(1) default 'N'
);
alter table ADM_PERFIL
  add primary key (PRF_SEQ);
alter table ADM_PERFIL
  add constraint ADM_PERFIL_UN unique (PRF_DESCRICAO);

create table ADM_PAGINA
(
  pag_seq BIGINT not null,
  pag_url VARCHAR(255) not null,
  pag_mb  VARCHAR(255)
);
alter table ADM_PAGINA
  add primary key (PAG_SEQ);
alter table ADM_PAGINA
  add constraint ADM_PAGINA_UN unique (PAG_URL);

create table ADM_FUNCIONALIDADE
(
  fun_seq       BIGINT not null,
  fun_pag_seq   BIGINT not null,
  fun_descricao VARCHAR(255) not null
);
alter table ADM_FUNCIONALIDADE
  add primary key (FUN_SEQ);
alter table ADM_FUNCIONALIDADE
  add constraint ADM_FUNCIONALIDADE_UN unique (FUN_DESCRICAO);
alter table ADM_FUNCIONALIDADE
  add foreign key (FUN_PAG_SEQ)
  references ADM_PAGINA (PAG_SEQ);


create table ADM_FUNCIONALIDADE_PAGINA
(
  fpg_fun_seq BIGINT not null,
  fpg_pag_seq BIGINT not null
);
alter table ADM_FUNCIONALIDADE_PAGINA
  add primary key (FPG_FUN_SEQ, FPG_PAG_SEQ);
alter table ADM_FUNCIONALIDADE_PAGINA
  add foreign key (FPG_FUN_SEQ)
  references ADM_FUNCIONALIDADE (FUN_SEQ);
alter table ADM_FUNCIONALIDADE_PAGINA
  add foreign key (FPG_PAG_SEQ)
  references ADM_PAGINA (PAG_SEQ);

create table ADM_FUNCIONALIDADE_PERFIL
(
  fpl_fun_seq BIGINT not null,
  fpl_prf_seq BIGINT not null
);
alter table ADM_FUNCIONALIDADE_PERFIL
  add primary key (FPL_FUN_SEQ, FPL_PRF_SEQ);
alter table ADM_FUNCIONALIDADE_PERFIL
  add foreign key (FPL_FUN_SEQ)
  references ADM_FUNCIONALIDADE (FUN_SEQ);
alter table ADM_FUNCIONALIDADE_PERFIL
  add foreign key (FPL_PRF_SEQ)
  references ADM_PERFIL (PRF_SEQ);

create table ADM_LOG_COLUNA
(
  col_nome          VARCHAR(30) not null,
  col_nome_exibicao VARCHAR(100) not null,
  col_comando       VARCHAR(4000)
);
alter table ADM_LOG_COLUNA
  add primary key (COL_NOME);

create table ADM_MENU
(
  mnu_seq       BIGINT not null,
  mnu_descricao VARCHAR(255) not null,
  mnu_pai_seq   BIGINT,
  mnu_fun_seq   BIGINT,
  mnu_ordem     DECIMAL(3)
);
alter table ADM_MENU
  add primary key (MNU_SEQ);
alter table ADM_MENU
  add constraint ADM_MENU_UN unique (MNU_DESCRICAO);
alter table ADM_MENU
  add foreign key (MNU_FUN_SEQ)
  references ADM_FUNCIONALIDADE (FUN_SEQ);
alter table ADM_MENU
  add foreign key (MNU_PAI_SEQ)
  references ADM_MENU (MNU_SEQ);
  
create table ADM_PAGINA_PERFIL
(
  pgl_pag_seq BIGINT not null,
  pgl_prf_seq BIGINT not null
);
alter table ADM_PAGINA_PERFIL
  add primary key (PGL_PAG_SEQ, PGL_PRF_SEQ);
alter table ADM_PAGINA_PERFIL
  add foreign key (PGL_PAG_SEQ)
  references ADM_PAGINA (PAG_SEQ);
alter table ADM_PAGINA_PERFIL
  add foreign key (PGL_PRF_SEQ)
  references ADM_PERFIL (PRF_SEQ);

create table ADM_PARAMETRO_CATEGORIA
(
  pmc_seq       BIGINT not null,
  pmc_descricao VARCHAR(100) not null,
  pmc_ordem     DECIMAL(2)
);
alter table ADM_PARAMETRO_CATEGORIA
  add primary key (PMC_SEQ);
alter table ADM_PARAMETRO_CATEGORIA
  add constraint ADM_PARAMETRO_CATEGORIA_UN unique (PMC_DESCRICAO);

create table ADM_PARAMETRO
(
  par_seq       BIGINT not null,
  par_valor     VARCHAR(4000),
  par_descricao VARCHAR(255) not null,
  par_codigo    VARCHAR(255) not null,
  par_pmc_seq   BIGINT not null
);
alter table ADM_PARAMETRO
  add primary key (PAR_SEQ);
alter table ADM_PARAMETRO
  add constraint ADM_PARAMETRO_UN unique (PAR_DESCRICAO);
alter table ADM_PARAMETRO
  add constraint UK_ADM_PARAMETRO unique (PAR_CODIGO);
alter table ADM_PARAMETRO
  add foreign key (PAR_PMC_SEQ)
  references ADM_PARAMETRO_CATEGORIA (PMC_SEQ);

create table ADM_USUARIO
(
  usu_matricula BIGINT not null,
  usu_ip        VARCHAR(15) not null,
  usu_login     VARCHAR(60) not null,
  usu_nome      VARCHAR(60) not null,
  usu_data      DATETIME not null,
  usu_cpf       DECIMAL(11),
  usu_email     VARCHAR(255),
  usu_ldap_dn   VARCHAR(255),
  usu_senha     VARCHAR(128)
);
alter table ADM_USUARIO
  add primary key (USU_MATRICULA, USU_IP);
  
create table ADM_SETOR
(
  set_sigla VARCHAR(15) not null,
  set_nome VARCHAR(50) not null,
  set_pai  VARCHAR(15),
  set_tipo VARCHAR(20)
);
alter table ADM_SETOR
  add primary key (set_sigla);
  
create table ADM_CARGO
(
  car_seq  BIGINT not null,
  car_descricao VARCHAR(60) not null
);
alter table ADM_CARGO
  add primary key (car_seq);
alter table ADM_CARGO
  add constraint ADM_CARGO_UN unique (car_descricao);

create table ADM_CARGO_PERFIL
(
  cgp_car_seq BIGINT not null,
  cgp_prf_seq   BIGINT not null
);
alter table ADM_CARGO_PERFIL
  add primary key (CGP_CAR_SEQ, CGP_PRF_SEQ);
alter table ADM_CARGO_PERFIL
  add foreign key (CGP_CAR_SEQ)
  references ADM_CARGO (CAR_SEQ);
alter table ADM_CARGO_PERFIL
  add foreign key (CGP_PRF_SEQ)
  references ADM_PERFIL (PRF_SEQ);
  
create table ADM_FUNCIONARIO
(
  fun_codigo	 BIGINT not null,
  fun_nome            VARCHAR(60) not null,
  fun_cpf             DECIMAL(11),
  fun_email           VARCHAR(100),
  fun_telefone        VARCHAR(20),
  fun_celular         VARCHAR(20),
  fun_set_sigla           VARCHAR(15) not null,
  fun_car_seq       BIGINT not null,
  fun_data_admissao   DATETIME(0),
  fun_data_saida      DATETIME(0),
  fun_ativo           CHAR(1)
);
alter table ADM_FUNCIONARIO
  add primary key (fun_codigo);
alter table ADM_FUNCIONARIO
  add constraint ADM_FUNCIONARIO_UN unique (fun_nome);
alter table ADM_FUNCIONARIO
  add foreign key (fun_set_sigla)
  references ADM_SETOR (set_sigla);
alter table ADM_FUNCIONARIO
  add foreign key (fun_car_seq)
  references ADM_CARGO (car_seq);

  
create table ADM_FUNCIONARIO_PERFIL
(
  usp_prf_seq    BIGINT not null,
  usp_fun_codigo BIGINT not null
);
alter table ADM_FUNCIONARIO_PERFIL
  add primary key (USP_PRF_SEQ, USP_FUN_CODIGO);
alter table ADM_FUNCIONARIO_PERFIL
  add foreign key (USP_PRF_SEQ)
  references ADM_PERFIL (PRF_SEQ);
alter table ADM_FUNCIONARIO_PERFIL
  add foreign key (USP_FUN_CODIGO)
  references ADM_FUNCIONARIO (FUN_CODIGO);
  
  
create table ADM_CARGO_FUNCIONARIO
(  
  cfn_car_seq               BIGINT not null,
  cfn_fun_codigo            BIGINT not null,  
  cfn_data_exercicio        DATETIME(0),
  cfn_data_desligamento     DATETIME(0),
  cfn_data_posse            DATETIME(0),
  cfn_situacao              CHAR(1),
  cfn_presidente            CHAR(1),
  cfn_diretor_geral         CHAR(1),
  cfn_responsavel_orcamento CHAR(1),
  cfn_chefe_sepo            CHAR(1)
);
alter table ADM_CARGO_FUNCIONARIO
  add primary key (cfn_car_seq, cfn_fun_codigo);
alter table ADM_CARGO_FUNCIONARIO
  add foreign key (CFN_CAR_SEQ)
  references ADM_CARGO (CAR_SEQ);
alter table ADM_CARGO_FUNCIONARIO
  add foreign key (CFN_FUN_CODIGO)
  references ADM_FUNCIONARIO (FUN_CODIGO);
