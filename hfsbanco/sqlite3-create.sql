create table ADM_PERFIL
(
  prf_seq           BIGINT not null,
  prf_descricao     VARCHAR(255),
  prf_geral         CHAR(1) default 'N',
  prf_administrador CHAR(1) default 'N',
  primary key (PRF_SEQ)
);
CREATE UNIQUE INDEX ADM_PERFIL_UN ON ADM_PERFIL(prf_descricao);

create table ADM_PAGINA
(
  pag_seq BIGINT not null,
  pag_url VARCHAR(255) not null,
  pag_mb  VARCHAR(255),
  primary key (PAG_SEQ)
);
CREATE UNIQUE INDEX ADM_PAGINA_UN ON ADM_PAGINA(PAG_URL);

create table ADM_FUNCIONALIDADE
(
  fun_seq       BIGINT not null,
  fun_pag_seq   BIGINT not null,
  fun_descricao VARCHAR(255) not null,
  primary key (FUN_SEQ),
  foreign key (FUN_PAG_SEQ) references ADM_PAGINA (PAG_SEQ)
);
CREATE UNIQUE INDEX ADM_FUNCIONALIDADE_UN ON ADM_FUNCIONALIDADE(FUN_DESCRICAO);

create table ADM_FUNCIONALIDADE_PAGINA
(
  fpg_fun_seq BIGINT not null,
  fpg_pag_seq BIGINT not null,
  primary key (FPG_FUN_SEQ, FPG_PAG_SEQ),
  foreign key (FPG_FUN_SEQ) references ADM_FUNCIONALIDADE (FUN_SEQ),
  foreign key (FPG_PAG_SEQ) references ADM_PAGINA (PAG_SEQ)
);

create table ADM_FUNCIONALIDADE_PERFIL
(
  fpl_fun_seq BIGINT not null,
  fpl_prf_seq BIGINT not null,
  primary key (FPL_FUN_SEQ, FPL_PRF_SEQ),
  foreign key (FPL_FUN_SEQ) references ADM_FUNCIONALIDADE (FUN_SEQ),
  foreign key (FPL_PRF_SEQ) references ADM_PERFIL (PRF_SEQ)
);

create table ADM_LOG_COLUNA
(
  col_nome          VARCHAR(30) not null,
  col_nome_exibicao VARCHAR(100) not null,
  col_comando       VARCHAR(4000),
  primary key (COL_NOME)
);

create table ADM_MENU
(
  mnu_seq       BIGINT not null,
  mnu_descricao VARCHAR(255) not null,
  mnu_pai_seq   BIGINT,
  mnu_fun_seq   BIGINT,
  mnu_ordem     DECIMAL(3),
  primary key (MNU_SEQ),
  foreign key (MNU_FUN_SEQ) references ADM_FUNCIONALIDADE (FUN_SEQ),
  foreign key (MNU_PAI_SEQ) references ADM_MENU (MNU_SEQ)
);
CREATE UNIQUE INDEX ADM_MENU_UN ON ADM_MENU(MNU_DESCRICAO);
  
create table ADM_PAGINA_PERFIL
(
  pgl_pag_seq BIGINT not null,
  pgl_prf_seq BIGINT not null,
  primary key (PGL_PAG_SEQ, PGL_PRF_SEQ),
  foreign key (PGL_PAG_SEQ) references ADM_PAGINA (PAG_SEQ),
  foreign key (PGL_PRF_SEQ) references ADM_PERFIL (PRF_SEQ)
);

create table ADM_PARAMETRO_CATEGORIA
(
  pmc_seq       BIGINT not null,
  pmc_descricao VARCHAR(100) not null,
  pmc_ordem     DECIMAL(2),
  primary key (PMC_SEQ)
);
CREATE UNIQUE INDEX ADM_PARAMETRO_CATEGORIA_UN ON ADM_PARAMETRO_CATEGORIA(PMC_DESCRICAO);

create table ADM_PARAMETRO
(
  par_seq       BIGINT not null,
  par_valor     VARCHAR(4000),
  par_descricao VARCHAR(255) not null,
  par_codigo    VARCHAR(255) not null,
  par_pmc_seq   BIGINT not null,
  primary key (PAR_SEQ),
  foreign key (PAR_PMC_SEQ) references ADM_PARAMETRO_CATEGORIA (PMC_SEQ)
);
CREATE UNIQUE INDEX ADM_PARAMETRO_UN ON ADM_PARAMETRO(PAR_DESCRICAO);
CREATE UNIQUE INDEX ADM_PARAMETRO_UN2 ON ADM_PARAMETRO(PAR_CODIGO);

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
  usu_senha     VARCHAR(128),
  primary key (USU_MATRICULA, USU_IP)
);
  
create table ADM_SETOR
(
  set_sigla VARCHAR(15) not null,
  set_nome VARCHAR(50) not null,
  set_pai  VARCHAR(15),
  set_tipo VARCHAR(20),
  primary key (set_sigla)
);
  
create table ADM_CARGO
(
  car_seq  BIGINT not null,
  car_descricao VARCHAR(60) not null,
  primary key (car_seq)
);
CREATE UNIQUE INDEX ADM_CARGO_UN ON ADM_CARGO(car_descricao);

create table ADM_CARGO_PERFIL
(
  cgp_car_seq BIGINT not null,
  cgp_prf_seq   BIGINT not null,
  primary key (CGP_CAR_SEQ, CGP_PRF_SEQ),
  foreign key (CGP_CAR_SEQ) references ADM_CARGO (CAR_SEQ),
  foreign key (CGP_PRF_SEQ) references ADM_PERFIL (PRF_SEQ)
);
  
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
  fun_data_admissao   DATETIME,
  fun_data_saida      DATETIME,
  fun_ativo           CHAR(1),
  primary key (fun_codigo),
  foreign key (fun_set_sigla) references ADM_SETOR (set_sigla),
  foreign key (fun_car_seq) references ADM_CARGO (car_seq)
);
CREATE UNIQUE INDEX ADM_FUNCIONARIO_UN ON ADM_FUNCIONARIO(fun_nome);
  
create table ADM_FUNCIONARIO_PERFIL
(
  usp_prf_seq    BIGINT not null,
  usp_fun_codigo BIGINT not null,
  primary key (USP_PRF_SEQ, USP_FUN_CODIGO),
  foreign key (USP_PRF_SEQ) references ADM_PERFIL (PRF_SEQ),
  foreign key (USP_FUN_CODIGO) references ADM_FUNCIONARIO (FUN_CODIGO)
);
  
create table ADM_CARGO_FUNCIONARIO
(  
  cfn_car_seq               BIGINT not null,
  cfn_fun_codigo            BIGINT not null,  
  cfn_data_exercicio        DATETIME,
  cfn_data_desligamento     DATETIME,
  cfn_data_posse            DATETIME,
  cfn_situacao              CHAR(1),
  cfn_presidente            CHAR(1),
  cfn_diretor_geral         CHAR(1),
  cfn_responsavel_orcamento CHAR(1),
  cfn_chefe_sepo            CHAR(1),
  primary key (cfn_car_seq, cfn_fun_codigo),
  foreign key (CFN_CAR_SEQ) references ADM_CARGO (CAR_SEQ),
  foreign key (CFN_FUN_CODIGO) references ADM_FUNCIONARIO (FUN_CODIGO)
);
