CREATE OR REPLACE VIEW public.vw_adm_log_dados AS 
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo'::text AS entidade,
    'adm_cargo'::text AS tabela,
    'car_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_cargo t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Perfil'::text AS entidade,
    'adm_cargo_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_cargo_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade'::text AS entidade,
    'adm_funcionalidade'::text AS tabela,
    'fun_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_funcionalidade t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Pagina'::text AS entidade,
    'adm_funcionalidade_pagina'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_funcionalidade_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Perfil'::text AS entidade,
    'adm_funcionalidade_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_funcionalidade_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario Perfil'::text AS entidade,
    'adm_funcionario_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_funcionario_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Log Coluna'::text AS entidade,
    'adm_log_coluna'::text AS tabela,
    'col_nome'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_log_coluna t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina'::text AS entidade,
    'adm_pagina'::text AS tabela,
    'pag_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina Perfil'::text AS entidade,
    'adm_pagina_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_pagina_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro Categoria'::text AS entidade,
    'adm_parametro_categoria'::text AS tabela,
    'pmc_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_parametro_categoria t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Perfil'::text AS entidade,
    'adm_perfil'::text AS tabela,
    'prf_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Setor'::text AS entidade,
    'adm_setor'::text AS tabela,
    'set_sigla'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero
   FROM log_adm_setor t;

   
CREATE OR REPLACE VIEW public.vw_adm_log AS 
 SELECT row_number() OVER () AS id,
    v.usuario,
    v.data,
    v.operacao,
    v.ip,
    v.entidade,
    v.tabela,
    v.chave,
    v.datanumero
   FROM ( SELECT DISTINCT t.usuario,
            t.data,
            t.operacao,
            t.ip,
            t.entidade,
            t.tabela,
            t.chave,
            t.datanumero
           FROM vw_adm_log_dados t
          ORDER BY t.data DESC) v;

		  
CREATE OR REPLACE VIEW public.vw_adm_log_valor_dados AS 
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo'::text AS entidade,
    'adm_cargo'::text AS tabela,
    'car_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xcar_seq::character varying AS valoranterior,
    t.car_seq::character varying AS valor
   FROM log_adm_cargo t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo'::text AS entidade,
    'adm_cargo'::text AS tabela,
    'car_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xcar_descricao AS valoranterior,
    t.car_descricao AS valor
   FROM log_adm_cargo t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'data exercicio'::text AS coluna,
    t.xcfn_data_exercicio::character varying AS valoranterior,
    t.cfn_data_exercicio::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'fun codigo'::text AS coluna,
    t.xcfn_fun_codigo::character varying AS valoranterior,
    t.cfn_fun_codigo::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'car seq'::text AS coluna,
    t.xcfn_car_seq::character varying AS valoranterior,
    t.cfn_car_seq::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'data desligamento'::text AS coluna,
    t.xcfn_data_desligamento::character varying AS valoranterior,
    t.cfn_data_desligamento::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'data posse'::text AS coluna,
    t.xcfn_data_posse::character varying AS valoranterior,
    t.cfn_data_posse::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'situacao'::text AS coluna,
    t.xcfn_situacao AS valoranterior,
    t.cfn_situacao AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'presidente'::text AS coluna,
    t.xcfn_presidente::character varying AS valoranterior,
    t.cfn_presidente::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'diretor geral'::text AS coluna,
    t.xcfn_diretor_geral AS valoranterior,
    t.cfn_diretor_geral AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'responsavel orcamento'::text AS coluna,
    t.xcfn_responsavel_orcamento::character varying AS valoranterior,
    t.cfn_responsavel_orcamento::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Funcionario'::text AS entidade,
    'adm_cargo_funcionario'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'chefe sepo'::text AS coluna,
    t.xcfn_chefe_sepo::character varying AS valoranterior,
    t.cfn_chefe_sepo::character varying AS valor
   FROM log_adm_cargo_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Perfil'::text AS entidade,
    'adm_cargo_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'car seq'::text AS coluna,
    t.xcgp_car_seq::character varying AS valoranterior,
    t.cgp_car_seq::character varying AS valor
   FROM log_adm_cargo_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Cargo Perfil'::text AS entidade,
    'adm_cargo_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'prf seq'::text AS coluna,
    t.xcgp_prf_seq::character varying AS valoranterior,
    t.cgp_prf_seq::character varying AS valor
   FROM log_adm_cargo_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade'::text AS entidade,
    'adm_funcionalidade'::text AS tabela,
    'fun_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xfun_seq::character varying AS valoranterior,
    t.fun_seq::character varying AS valor
   FROM log_adm_funcionalidade t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade'::text AS entidade,
    'adm_funcionalidade'::text AS tabela,
    'fun_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pag seq'::text AS coluna,
    t.xfun_pag_seq::character varying AS valoranterior,
    t.fun_pag_seq::character varying AS valor
   FROM log_adm_funcionalidade t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade'::text AS entidade,
    'adm_funcionalidade'::text AS tabela,
    'fun_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xfun_descricao AS valoranterior,
    t.fun_descricao AS valor
   FROM log_adm_funcionalidade t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Pagina'::text AS entidade,
    'adm_funcionalidade_pagina'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'fun seq'::text AS coluna,
    t.xfpg_fun_seq::character varying AS valoranterior,
    t.fpg_fun_seq::character varying AS valor
   FROM log_adm_funcionalidade_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Pagina'::text AS entidade,
    'adm_funcionalidade_pagina'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pag seq'::text AS coluna,
    t.xfpg_pag_seq::character varying AS valoranterior,
    t.fpg_pag_seq::character varying AS valor
   FROM log_adm_funcionalidade_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Perfil'::text AS entidade,
    'adm_funcionalidade_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'fun seq'::text AS coluna,
    t.xfpl_fun_seq::character varying AS valoranterior,
    t.fpl_fun_seq::character varying AS valor
   FROM log_adm_funcionalidade_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionalidade Perfil'::text AS entidade,
    'adm_funcionalidade_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'prf seq'::text AS coluna,
    t.xfpl_prf_seq::character varying AS valoranterior,
    t.fpl_prf_seq::character varying AS valor
   FROM log_adm_funcionalidade_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'codigo'::text AS coluna,
    t.xfun_codigo::character varying AS valoranterior,
    t.fun_codigo::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'nome'::text AS coluna,
    t.xfun_nome AS valoranterior,
    t.fun_nome AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'cpf'::text AS coluna,
    t.xfun_cpf::character varying AS valoranterior,
    t.fun_cpf::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'email'::text AS coluna,
    t.xfun_email AS valoranterior,
    t.fun_email AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'telefone'::text AS coluna,
    t.xfun_telefone AS valoranterior,
    t.fun_telefone AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'celular'::text AS coluna,
    t.xfun_celular AS valoranterior,
    t.fun_celular AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'set sigla'::text AS coluna,
    t.xfun_set_sigla AS valoranterior,
    t.fun_set_sigla AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'car seq'::text AS coluna,
    t.xfun_car_seq::character varying AS valoranterior,
    t.fun_car_seq::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'data admissao'::text AS coluna,
    t.xfun_data_admissao::character varying AS valoranterior,
    t.fun_data_admissao::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'data saida'::text AS coluna,
    t.xfun_data_saida::character varying AS valoranterior,
    t.fun_data_saida::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario'::text AS entidade,
    'adm_funcionario'::text AS tabela,
    'fun_codigo'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'ativo'::text AS coluna,
    t.xfun_ativo::character varying AS valoranterior,
    t.fun_ativo::character varying AS valor
   FROM log_adm_funcionario t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario Perfil'::text AS entidade,
    'adm_funcionario_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'prf seq'::text AS coluna,
    t.xusp_prf_seq::character varying AS valoranterior,
    t.usp_prf_seq::character varying AS valor
   FROM log_adm_funcionario_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Funcionario Perfil'::text AS entidade,
    'adm_funcionario_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'fun codigo'::text AS coluna,
    t.xusp_fun_codigo::character varying AS valoranterior,
    t.usp_fun_codigo::character varying AS valor
   FROM log_adm_funcionario_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Log Coluna'::text AS entidade,
    'adm_log_coluna'::text AS tabela,
    'col_nome'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'nome'::text AS coluna,
    t.xcol_nome AS valoranterior,
    t.col_nome AS valor
   FROM log_adm_log_coluna t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Log Coluna'::text AS entidade,
    'adm_log_coluna'::text AS tabela,
    'col_nome'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'nome exibicao'::text AS coluna,
    t.xcol_nome_exibicao AS valoranterior,
    t.col_nome_exibicao AS valor
   FROM log_adm_log_coluna t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Log Coluna'::text AS entidade,
    'adm_log_coluna'::text AS tabela,
    'col_nome'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'comando'::text AS coluna,
    t.xcol_comando AS valoranterior,
    t.col_comando AS valor
   FROM log_adm_log_coluna t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xmnu_seq::character varying AS valoranterior,
    t.mnu_seq::character varying AS valor
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xmnu_descricao AS valoranterior,
    t.mnu_descricao AS valor
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pai seq'::text AS coluna,
    t.xmnu_pai_seq::character varying AS valoranterior,
    t.mnu_pai_seq::character varying AS valor
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'fun seq'::text AS coluna,
    t.xmnu_fun_seq::character varying AS valoranterior,
    t.mnu_fun_seq::character varying AS valor
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Menu'::text AS entidade,
    'adm_menu'::text AS tabela,
    'mnu_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'ordem'::text AS coluna,
    t.xmnu_ordem::character varying AS valoranterior,
    t.mnu_ordem::character varying AS valor
   FROM log_adm_menu t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina'::text AS entidade,
    'adm_pagina'::text AS tabela,
    'pag_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xpag_seq::character varying AS valoranterior,
    t.pag_seq::character varying AS valor
   FROM log_adm_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina'::text AS entidade,
    'adm_pagina'::text AS tabela,
    'pag_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'url'::text AS coluna,
    t.xpag_url AS valoranterior,
    t.pag_url AS valor
   FROM log_adm_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina'::text AS entidade,
    'adm_pagina'::text AS tabela,
    'pag_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'mb'::text AS coluna,
    t.xpag_mb AS valoranterior,
    t.pag_mb AS valor
   FROM log_adm_pagina t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina Perfil'::text AS entidade,
    'adm_pagina_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pag seq'::text AS coluna,
    t.xpgl_pag_seq::character varying AS valoranterior,
    t.pgl_pag_seq::character varying AS valor
   FROM log_adm_pagina_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Pagina Perfil'::text AS entidade,
    'adm_pagina_perfil'::text AS tabela,
    NULL::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'prf seq'::text AS coluna,
    t.xpgl_prf_seq::character varying AS valoranterior,
    t.pgl_prf_seq::character varying AS valor
   FROM log_adm_pagina_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xpar_seq::character varying AS valoranterior,
    t.par_seq::character varying AS valor
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'valor'::text AS coluna,
    t.xpar_valor AS valoranterior,
    t.par_valor AS valor
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xpar_descricao AS valoranterior,
    t.par_descricao AS valor
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'codigo'::text AS coluna,
    t.xpar_codigo AS valoranterior,
    t.par_codigo AS valor
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro'::text AS entidade,
    'adm_parametro'::text AS tabela,
    'par_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pmc seq'::text AS coluna,
    t.xpar_pmc_seq::character varying AS valoranterior,
    t.par_pmc_seq::character varying AS valor
   FROM log_adm_parametro t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro Categoria'::text AS entidade,
    'adm_parametro_categoria'::text AS tabela,
    'pmc_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xpmc_seq::character varying AS valoranterior,
    t.pmc_seq::character varying AS valor
   FROM log_adm_parametro_categoria t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro Categoria'::text AS entidade,
    'adm_parametro_categoria'::text AS tabela,
    'pmc_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xpmc_descricao AS valoranterior,
    t.pmc_descricao AS valor
   FROM log_adm_parametro_categoria t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Parametro Categoria'::text AS entidade,
    'adm_parametro_categoria'::text AS tabela,
    'pmc_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'ordem'::text AS coluna,
    t.xpmc_ordem::character varying AS valoranterior,
    t.pmc_ordem::character varying AS valor
   FROM log_adm_parametro_categoria t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Perfil'::text AS entidade,
    'adm_perfil'::text AS tabela,
    'prf_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'seq'::text AS coluna,
    t.xprf_seq::character varying AS valoranterior,
    t.prf_seq::character varying AS valor
   FROM log_adm_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Perfil'::text AS entidade,
    'adm_perfil'::text AS tabela,
    'prf_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'descricao'::text AS coluna,
    t.xprf_descricao AS valoranterior,
    t.prf_descricao AS valor
   FROM log_adm_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Perfil'::text AS entidade,
    'adm_perfil'::text AS tabela,
    'prf_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'geral'::text AS coluna,
    t.xprf_geral::character varying AS valoranterior,
    t.prf_geral::character varying AS valor
   FROM log_adm_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Perfil'::text AS entidade,
    'adm_perfil'::text AS tabela,
    'prf_seq'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'administrador'::text AS coluna,
    t.xprf_administrador::character varying AS valoranterior,
    t.prf_administrador::character varying AS valor
   FROM log_adm_perfil t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Setor'::text AS entidade,
    'adm_setor'::text AS tabela,
    'set_sigla'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'sigla'::text AS coluna,
    t.xset_sigla AS valoranterior,
    t.set_sigla AS valor
   FROM log_adm_setor t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Setor'::text AS entidade,
    'adm_setor'::text AS tabela,
    'set_sigla'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'nome'::text AS coluna,
    t.xset_nome AS valoranterior,
    t.set_nome AS valor
   FROM log_adm_setor t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Setor'::text AS entidade,
    'adm_setor'::text AS tabela,
    'set_sigla'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'pai'::text AS coluna,
    t.xset_pai AS valoranterior,
    t.set_pai AS valor
   FROM log_adm_setor t
UNION
 SELECT t.usuario,
    t.data,
    t.operacao,
    t.ip,
    'Setor'::text AS entidade,
    'adm_setor'::text AS tabela,
    'set_sigla'::text AS chave,
    to_number(to_char(t.data, 'YYYYMMDDHH24MISS'::text), '99999999999999'::text) AS datanumero,
    'tipo'::text AS coluna,
    t.xset_tipo AS valoranterior,
    t.set_tipo AS valor
   FROM log_adm_setor t;


CREATE OR REPLACE VIEW public.vw_adm_log_valor AS 
 SELECT row_number() OVER () AS id,
    v.usuario,
    v.data,
    v.operacao,
    v.ip,
    v.entidade,
    v.tabela,
    v.chave,
    v.datanumero,
    v.coluna,
    v.valoranterior,
    v.valor
   FROM ( SELECT DISTINCT t.usuario,
            t.data,
            t.operacao,
            t.ip,
            t.entidade,
            t.tabela,
            t.chave,
            t.datanumero,
            t.coluna,
            t.valoranterior,
            t.valor
           FROM vw_adm_log_valor_dados t
          ORDER BY t.data DESC) v;


