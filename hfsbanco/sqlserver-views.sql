CREATE VIEW VW_ADM_LOG_DADOS AS
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'car_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'fun_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log coluna' entidade, 'adm_log_coluna' tabela, 'col_nome' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'pag_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero
    FROM log_adm_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro categoria' entidade, 'adm_parametro_categoria' tabela, 'pmc_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'prf_seq' chave, cast(t.data as datetime) dataNumero
    FROM log_adm_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, cast(t.data as datetime) dataNumero
	FROM log_adm_setor t;

CREATE VIEW VW_ADM_LOG AS 
SELECT DISTINCT row_number() OVER (ORDER BY t.data DESC) as id, t.usuario,t.data,t.operacao,t.ip,t.entidade,t.tabela,t.chave,t.datanumero 
FROM VW_ADM_LOG_DADOS t;



CREATE VIEW VW_ADM_LOG_VALOR_DADOS AS
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'car_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xcar_seq as char) valorAnterior, cast(t.car_seq as char) valor
    FROM log_adm_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo' entidade, 'adm_cargo' tabela, 'car_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xcar_descricao as char) valorAnterior, cast(t.car_descricao as char) valor
    FROM log_adm_cargo t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'car seq' coluna, cast(t.xcfn_car_seq as char) valorAnterior, cast(t.cfn_car_seq as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'fun codigo' coluna, cast(t.xcfn_fun_codigo as char) valorAnterior, cast(t.cfn_fun_codigo as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'data exercicio' coluna, cast(t.xcfn_data_exercicio as char) valorAnterior, cast(t.cfn_data_exercicio as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'data desligamento' coluna, cast(t.xcfn_data_desligamento as char) valorAnterior, cast(t.cfn_data_desligamento as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'data posse' coluna, cast(t.xcfn_data_posse as char) valorAnterior, cast(t.cfn_data_posse as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'situacao' coluna, cast(t.xcfn_situacao as char) valorAnterior, cast(t.cfn_situacao as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'presidente' coluna, cast(t.xcfn_presidente as char) valorAnterior, cast(t.cfn_presidente as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'diretor geral' coluna, cast(t.xcfn_diretor_geral as char) valorAnterior, cast(t.cfn_diretor_geral as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'responsavel orcamento' coluna, cast(t.xcfn_responsavel_orcamento as char) valorAnterior, cast(t.cfn_responsavel_orcamento as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo funcionario' entidade, 'adm_cargo_funcionario' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'chefe sepo' coluna, cast(t.xcfn_chefe_sepo as char) valorAnterior, cast(t.cfn_chefe_sepo as char) valor
    FROM log_adm_cargo_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'car seq' coluna, cast(t.xcgp_car_seq as char) valorAnterior, cast(t.cgp_car_seq as char) valor
    FROM log_adm_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Cargo perfil' entidade, 'adm_cargo_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'prf seq' coluna, cast(t.xcgp_prf_seq as char) valorAnterior, cast(t.cgp_prf_seq as char) valor
    FROM log_adm_cargo_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'fun_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xfun_seq as char) valorAnterior, cast(t.fun_seq as char) valor
    FROM log_adm_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'fun_seq' chave, cast(t.data as datetime) dataNumero,
'pag seq' coluna, cast(t.xfun_pag_seq as char) valorAnterior, cast(t.fun_pag_seq as char) valor
    FROM log_adm_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade' entidade, 'adm_funcionalidade' tabela, 'fun_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xfun_descricao as char) valorAnterior, cast(t.fun_descricao as char) valor
    FROM log_adm_funcionalidade t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'fun seq' coluna, cast(t.xfpg_fun_seq as char) valorAnterior, cast(t.fpg_fun_seq as char) valor
    FROM log_adm_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade pagina' entidade, 'adm_funcionalidade_pagina' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'pag seq' coluna, cast(t.xfpg_pag_seq as char) valorAnterior, cast(t.fpg_pag_seq as char) valor
    FROM log_adm_funcionalidade_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'fun seq' coluna, cast(t.xfpl_fun_seq as char) valorAnterior, cast(t.fpl_fun_seq as char) valor
    FROM log_adm_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionalidade perfil' entidade, 'adm_funcionalidade_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'prf seq' coluna, cast(t.xfpl_prf_seq as char) valorAnterior, cast(t.fpl_prf_seq as char) valor
    FROM log_adm_funcionalidade_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'codigo' coluna, cast(t.xfun_codigo as char) valorAnterior, cast(t.fun_codigo as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'nome' coluna, cast(t.xfun_nome as char) valorAnterior, cast(t.fun_nome as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'cpf' coluna, cast(t.xfun_cpf as char) valorAnterior, cast(t.fun_cpf as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'email' coluna, cast(t.xfun_email as char) valorAnterior, cast(t.fun_email as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'telefone' coluna, cast(t.xfun_telefone as char) valorAnterior, cast(t.fun_telefone as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'celular' coluna, cast(t.xfun_celular as char) valorAnterior, cast(t.fun_celular as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'set sigla' coluna, cast(t.xfun_set_sigla as char) valorAnterior, cast(t.fun_set_sigla as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'car seq' coluna, cast(t.xfun_car_seq as char) valorAnterior, cast(t.fun_car_seq as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'data admissao' coluna, cast(t.xfun_data_admissao as char) valorAnterior, cast(t.fun_data_admissao as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'data saida' coluna, cast(t.xfun_data_saida as char) valorAnterior, cast(t.fun_data_saida as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario' entidade, 'adm_funcionario' tabela, 'fun_codigo' chave, cast(t.data as datetime) dataNumero,
'ativo' coluna, cast(t.xfun_ativo as char) valorAnterior, cast(t.fun_ativo as char) valor
    FROM log_adm_funcionario t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'prf seq' coluna, cast(t.xusp_prf_seq as char) valorAnterior, cast(t.usp_prf_seq as char) valor
    FROM log_adm_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Funcionario perfil' entidade, 'adm_funcionario_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'fun codigo' coluna, cast(t.xusp_fun_codigo as char) valorAnterior, cast(t.usp_fun_codigo as char) valor
    FROM log_adm_funcionario_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log coluna' entidade, 'adm_log_coluna' tabela, 'col_nome' chave, cast(t.data as datetime) dataNumero,
'nome' coluna, cast(t.xcol_nome as char) valorAnterior, cast(t.col_nome as char) valor
    FROM log_adm_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log coluna' entidade, 'adm_log_coluna' tabela, 'col_nome' chave, cast(t.data as datetime) dataNumero,
'nome exibicao' coluna, cast(t.xcol_nome_exibicao as char) valorAnterior, cast(t.col_nome_exibicao as char) valor
    FROM log_adm_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Log coluna' entidade, 'adm_log_coluna' tabela, 'col_nome' chave, cast(t.data as datetime) dataNumero,
'comando' coluna, cast(t.xcol_comando as char) valorAnterior, cast(t.col_comando as char) valor
    FROM log_adm_log_coluna t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xmnu_seq as char) valorAnterior, cast(t.mnu_seq as char) valor
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xmnu_descricao as char) valorAnterior, cast(t.mnu_descricao as char) valor
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero,
'pai seq' coluna, cast(t.xmnu_pai_seq as char) valorAnterior, cast(t.mnu_pai_seq as char) valor
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero,
'fun seq' coluna, cast(t.xmnu_fun_seq as char) valorAnterior, cast(t.mnu_fun_seq as char) valor
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Menu' entidade, 'adm_menu' tabela, 'mnu_seq' chave, cast(t.data as datetime) dataNumero,
'ordem' coluna, cast(t.xmnu_ordem as char) valorAnterior, cast(t.mnu_ordem as char) valor
    FROM log_adm_menu t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'pag_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xpag_seq as char) valorAnterior, cast(t.pag_seq as char) valor
    FROM log_adm_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'pag_seq' chave, cast(t.data as datetime) dataNumero,
'url' coluna, cast(t.xpag_url as char) valorAnterior, cast(t.pag_url as char) valor
    FROM log_adm_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina' entidade, 'adm_pagina' tabela, 'pag_seq' chave, cast(t.data as datetime) dataNumero,
'mb' coluna, cast(t.xpag_mb as char) valorAnterior, cast(t.pag_mb as char) valor
    FROM log_adm_pagina t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'pag seq' coluna, cast(t.xpgl_pag_seq as char) valorAnterior, cast(t.pgl_pag_seq as char) valor
    FROM log_adm_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Pagina perfil' entidade, 'adm_pagina_perfil' tabela, NULL chave, cast(t.data as datetime) dataNumero,
'prf seq' coluna, cast(t.xpgl_prf_seq as char) valorAnterior, cast(t.pgl_prf_seq as char) valor
    FROM log_adm_pagina_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xpar_seq as char) valorAnterior, cast(t.par_seq as char) valor
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero,
'valor' coluna, cast(t.xpar_valor as char) valorAnterior, cast(t.par_valor as char) valor
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xpar_descricao as char) valorAnterior, cast(t.par_descricao as char) valor
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero,
'codigo' coluna, cast(t.xpar_codigo as char) valorAnterior, cast(t.par_codigo as char) valor
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro' entidade, 'adm_parametro' tabela, 'par_seq' chave, cast(t.data as datetime) dataNumero,
'pmc seq' coluna, cast(t.xpar_pmc_seq as char) valorAnterior, cast(t.par_pmc_seq as char) valor
    FROM log_adm_parametro t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro categoria' entidade, 'adm_parametro_categoria' tabela, 'pmc_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xpmc_seq as char) valorAnterior, cast(t.pmc_seq as char) valor
    FROM log_adm_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro categoria' entidade, 'adm_parametro_categoria' tabela, 'pmc_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xpmc_descricao as char) valorAnterior, cast(t.pmc_descricao as char) valor
    FROM log_adm_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Parametro categoria' entidade, 'adm_parametro_categoria' tabela, 'pmc_seq' chave, cast(t.data as datetime) dataNumero,
'ordem' coluna, cast(t.xpmc_ordem as char) valorAnterior, cast(t.pmc_ordem as char) valor
    FROM log_adm_parametro_categoria t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'prf_seq' chave, cast(t.data as datetime) dataNumero,
'seq' coluna, cast(t.xprf_seq as char) valorAnterior, cast(t.prf_seq as char) valor
    FROM log_adm_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'prf_seq' chave, cast(t.data as datetime) dataNumero,
'descricao' coluna, cast(t.xprf_descricao as char) valorAnterior, cast(t.prf_descricao as char) valor
    FROM log_adm_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'prf_seq' chave, cast(t.data as datetime) dataNumero,
'geral' coluna, cast(t.xprf_geral as char) valorAnterior, cast(t.prf_geral as char) valor
    FROM log_adm_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Perfil' entidade, 'adm_perfil' tabela, 'prf_seq' chave, cast(t.data as datetime) dataNumero,
'administrador' coluna, cast(t.xprf_administrador as char) valorAnterior, cast(t.prf_administrador as char) valor
    FROM log_adm_perfil t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, cast(t.data as datetime) dataNumero,
'sigla' coluna, cast(t.xset_sigla as char) valorAnterior, cast(t.set_sigla as char) valor
    FROM log_adm_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, cast(t.data as datetime) dataNumero,
'nome' coluna, cast(t.xset_nome as char) valorAnterior, cast(t.set_nome as char) valor
    FROM log_adm_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, cast(t.data as datetime) dataNumero,
'pai' coluna, cast(t.xset_pai as char) valorAnterior, cast(t.set_pai as char) valor
    FROM log_adm_setor t
UNION
SELECT t.usuario, t.data, t.operacao, t.ip, 'Setor' entidade, 'adm_setor' tabela, 'set_sigla' chave, cast(t.data as datetime) dataNumero,
'tipo' coluna, cast(t.xset_tipo as char) valorAnterior, cast(t.set_tipo as char) valor
FROM log_adm_setor t;


CREATE VIEW VW_ADM_LOG_VALOR AS
select distinct row_number() OVER (ORDER BY v.data DESC) as id,v.usuario,v.data,v.operacao,v.ip,v.entidade,v.tabela,v.chave,v.datanumero,v.coluna,v.valoranterior,v.valor
from vw_adm_log_valor_dados v;


