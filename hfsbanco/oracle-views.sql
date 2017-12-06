CREATE OR REPLACE VIEW VW_ADM_LOG_DADOS AS
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'CAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo Funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo Perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'FUN_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'FUN_CODIGO' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario Perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log Coluna' entidade, 'adm_log_coluna' tabela, 'COL_NOME' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'PAG_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina Perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro Categoria' entidade, 'adm_parametro_categoria' tabela, 'PMC_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'PRF_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
    FROM log_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'SET_SIGLA' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero
FROM log_categoria t;

CREATE OR REPLACE VIEW VW_ADM_LOG AS
SELECT ROWNUM ID,
          V.USUARIO,
          V.DATA,
          V.OPERACAO,
          V.IP,
          V.ENTIDADE,
          V.TABELA,
          V.CHAVE,
          V.DATANUMERO
         FROM ( SELECT DISTINCT T.USUARIO,
                  T.DATA,
                  T.OPERACAO,
                  T.IP,
                  T.ENTIDADE,
                  T.TABELA,
                  T.CHAVE,
                  T.DATANUMERO
                 FROM VW_ADM_LOG_DADOS T
                ORDER BY T.DATA DESC) V;

CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR_DADOS AS
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'car_seq' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'seq' coluna, to_char(t.xcar_seq) valorAnterior, to_char(t.car_seq) valor
FROM log_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'car_seq' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'descricao' coluna, to_char(t.xcar_descricao) valorAnterior, to_char(t.car_descricao) valor
FROM log_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'car seq' coluna, to_char(t.xcfn_car_seq) valorAnterior, to_char(t.cfn_car_seq) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'fun codigo' coluna, to_char(t.xcfn_fun_codigo) valorAnterior, to_char(t.cfn_fun_codigo) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'data exercicio' coluna, to_char(t.xcfn_data_exercicio) valorAnterior, to_char(t.cfn_data_exercicio) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'data desligamento' coluna, to_char(t.xcfn_data_desligamento) valorAnterior, to_char(t.cfn_data_desligamento) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'data posse' coluna, to_char(t.xcfn_data_posse) valorAnterior, to_char(t.cfn_data_posse) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'situacao' coluna, to_char(t.xcfn_situacao) valorAnterior, to_char(t.cfn_situacao) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'presidente' coluna, to_char(t.xcfn_presidente) valorAnterior, to_char(t.cfn_presidente) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'diretor geral' coluna, to_char(t.xcfn_diretor_geral) valorAnterior, to_char(t.cfn_diretor_geral) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'responsavel orcamento' coluna, to_char(t.xcfn_responsavel_orcamento) valorAnterior, to_char(t.cfn_responsavel_orcamento) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'chefe sepo' coluna, to_char(t.xcfn_chefe_sepo) valorAnterior, to_char(t.cfn_chefe_sepo) valor
FROM log_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo Perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'cod cargo' coluna, to_char(t.xcgp_cod_cargo) valorAnterior, to_char(t.cgp_cod_cargo) valor
    FROM log_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo Perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'prf seq' coluna, to_char(t.xcgp_prf_seq) valorAnterior, to_char(t.cgp_prf_seq) valor
    FROM log_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'FUN_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xfun_seq) valorAnterior, to_char(t.fun_seq) valor
    FROM log_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'FUN_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'pag seq' coluna, to_char(t.xfun_pag_seq) valorAnterior, to_char(t.fun_pag_seq) valor
    FROM log_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'FUN_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'descricao' coluna, to_char(t.xfun_descricao) valorAnterior, to_char(t.fun_descricao) valor
    FROM log_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'fun seq' coluna, to_char(t.xfpg_fun_seq) valorAnterior, to_char(t.fpg_fun_seq) valor
    FROM log_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'pag seq' coluna, to_char(t.xfpg_pag_seq) valorAnterior, to_char(t.fpg_pag_seq) valor
    FROM log_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'fun seq' coluna, to_char(t.xfpl_fun_seq) valorAnterior, to_char(t.fpl_fun_seq) valor
    FROM log_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade Perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'prf seq' coluna, to_char(t.xfpl_prf_seq) valorAnterior, to_char(t.fpl_prf_seq) valor
    FROM log_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'codigo' coluna, to_char(t.xfun_codigo) valorAnterior, to_char(t.fun_codigo) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'nome' coluna, to_char(t.xfun_nome) valorAnterior, to_char(t.fun_nome) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'cpf' coluna, to_char(t.xfun_cpf) valorAnterior, to_char(t.fun_cpf) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'email' coluna, to_char(t.xfun_email) valorAnterior, to_char(t.fun_email) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'telefone' coluna, to_char(t.xfun_telefone) valorAnterior, to_char(t.fun_telefone) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'celular' coluna, to_char(t.xfun_celular) valorAnterior, to_char(t.fun_celular) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'set sigla' coluna, to_char(t.xfun_set_sigla) valorAnterior, to_char(t.fun_set_sigla) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'car seq' coluna, to_char(t.xfun_car_seq) valorAnterior, to_char(t.fun_car_seq) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'data admissao' coluna, to_char(t.xfun_data_admissao) valorAnterior, to_char(t.fun_data_admissao) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'data saida' coluna, to_char(t.xfun_data_saida) valorAnterior, to_char(t.fun_data_saida) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'ativo' coluna, to_char(t.xfun_ativo) valorAnterior, to_char(t.fun_ativo) valor
FROM log_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario Perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'prf seq' coluna, to_char(t.xusp_prf_seq) valorAnterior, to_char(t.usp_prf_seq) valor
    FROM log_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario Perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'cod funcionario' coluna, to_char(t.xusp_cod_funcionario) valorAnterior, to_char(t.usp_cod_funcionario) valor
    FROM log_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log Coluna' entidade, 'adm_log_coluna' tabela, 'COL_NOME' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'nome' coluna, to_char(t.xcol_nome) valorAnterior, to_char(t.col_nome) valor
    FROM log_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log Coluna' entidade, 'adm_log_coluna' tabela, 'COL_NOME' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'nome exibicao' coluna, to_char(t.xcol_nome_exibicao) valorAnterior, to_char(t.col_nome_exibicao) valor
    FROM log_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log Coluna' entidade, 'adm_log_coluna' tabela, 'COL_NOME' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'comando' coluna, to_char(t.xcol_comando) valorAnterior, to_char(t.col_comando) valor
    FROM log_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xmnu_seq) valorAnterior, to_char(t.mnu_seq) valor
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'descricao' coluna, to_char(t.xmnu_descricao) valorAnterior, to_char(t.mnu_descricao) valor
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'pai seq' coluna, to_char(t.xmnu_pai_seq) valorAnterior, to_char(t.mnu_pai_seq) valor
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'fun seq' coluna, to_char(t.xmnu_fun_seq) valorAnterior, to_char(t.mnu_fun_seq) valor
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'MNU_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'ordem' coluna, to_char(t.xmnu_ordem) valorAnterior, to_char(t.mnu_ordem) valor
    FROM log_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'PAG_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xpag_seq) valorAnterior, to_char(t.pag_seq) valor
    FROM log_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'PAG_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'url' coluna, to_char(t.xpag_url) valorAnterior, to_char(t.pag_url) valor
    FROM log_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'PAG_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'mb' coluna, to_char(t.xpag_mb) valorAnterior, to_char(t.pag_mb) valor
    FROM log_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina Perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'pag seq' coluna, to_char(t.xpgl_pag_seq) valorAnterior, to_char(t.pgl_pag_seq) valor
    FROM log_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina Perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'prf seq' coluna, to_char(t.xpgl_prf_seq) valorAnterior, to_char(t.pgl_prf_seq) valor
    FROM log_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xpar_seq) valorAnterior, to_char(t.par_seq) valor
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'valor' coluna, to_char(t.xpar_valor) valorAnterior, to_char(t.par_valor) valor
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'descricao' coluna, to_char(t.xpar_descricao) valorAnterior, to_char(t.par_descricao) valor
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'codigo' coluna, to_char(t.xpar_codigo) valorAnterior, to_char(t.par_codigo) valor
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'PAR_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'pmc seq' coluna, to_char(t.xpar_pmc_seq) valorAnterior, to_char(t.par_pmc_seq) valor
    FROM log_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro Categoria' entidade, 'adm_parametro_categoria' tabela, 'PMC_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xpmc_seq) valorAnterior, to_char(t.pmc_seq) valor
    FROM log_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro Categoria' entidade, 'adm_parametro_categoria' tabela, 'PMC_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'descricao' coluna, to_char(t.xpmc_descricao) valorAnterior, to_char(t.pmc_descricao) valor
    FROM log_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro Categoria' entidade, 'adm_parametro_categoria' tabela, 'PMC_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'ordem' coluna, to_char(t.xpmc_ordem) valorAnterior, to_char(t.pmc_ordem) valor
    FROM log_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'PRF_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'seq' coluna, to_char(t.xprf_seq) valorAnterior, to_char(t.prf_seq) valor
    FROM log_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'PRF_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'descricao' coluna, to_char(t.xprf_descricao) valorAnterior, to_char(t.prf_descricao) valor
    FROM log_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'PRF_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'geral' coluna, to_char(t.xprf_geral) valorAnterior, to_char(t.prf_geral) valor
    FROM log_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'PRF_SEQ' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero, 'administrador' coluna, to_char(t.xprf_administrador) valorAnterior, to_char(t.prf_administrador) valor
    FROM log_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'sigla' coluna, to_char(t.xset_sigla) valorAnterior, to_char(t.set_sigla) valor
FROM log_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'nome' coluna, to_char(t.xset_nome) valorAnterior, to_char(t.set_nome) valor
FROM log_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'pai' coluna, to_char(t.xset_pai) valorAnterior, to_char(t.set_pai) valor
FROM log_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, to_number(to_char(t.data,'YYYYMMDDHH24MISS')) dataNumero,
'tipo' coluna, to_char(t.xset_tipo) valorAnterior, to_char(t.set_tipo) valor
FROM log_setor t;


CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR AS
SELECT ROWNUM ID,
    V.USUARIO,
    V.DATA,
    V.OPERACAO,
    V.IP,
    V.ENTIDADE,
    V.TABELA,
    V.CHAVE,
    V.DATANUMERO,
    V.COLUNA,
    V.VALORANTERIOR,
    V.VALOR
   FROM ( SELECT DISTINCT T.USUARIO,
            T.DATA,
            T.OPERACAO,
            T.IP,
            T.ENTIDADE,
            T.TABELA,
            T.CHAVE,
            T.DATANUMERO,
            T.COLUNA,
            T.VALORANTERIOR,
            T.VALOR
           FROM VW_ADM_LOG_VALOR_DADOS T
          ORDER BY T.DATA DESC) V;

