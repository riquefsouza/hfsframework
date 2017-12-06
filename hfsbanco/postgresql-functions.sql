CREATE OR REPLACE FUNCTION public.pkg_adm_obter_usuario()
  RETURNS character varying AS
$BODY$declare
	v_usuario_aplicacao   VARCHAR(30);
BEGIN
	select current_setting('myvars.usuario_login')
	INTO v_usuario_aplicacao;

	IF v_usuario_aplicacao IS NULL THEN
		v_usuario_aplicacao:='sem usuario';
	END IF;

	RETURN v_usuario_aplicacao;
EXCEPTION
    WHEN OTHERS THEN
	v_usuario_aplicacao:='sem usuario';
	RETURN v_usuario_aplicacao;	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE OR REPLACE FUNCTION public.pkg_adm_obter_ip_usuario()
  RETURNS character varying AS
$BODY$declare
	v_ip_usuario_aplicacao VARCHAR(50);
BEGIN
	SELECT current_setting('myvars.usuario_ip')
	INTO v_ip_usuario_aplicacao;

	IF strpos(v_ip_usuario_aplicacao,'.') = 0 THEN
		v_ip_usuario_aplicacao:='127.0.0.1';
	END IF;
	RETURN v_ip_usuario_aplicacao;
EXCEPTION
    WHEN OTHERS THEN
	v_ip_usuario_aplicacao:='127.0.0.1';
	RETURN v_ip_usuario_aplicacao;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE OR REPLACE FUNCTION public.pkg_adm_obter_colunas_tabela(
    p_esquema character varying,
    p_tabela character varying,
    p_prefixo character varying)
  RETURNS character varying AS
$BODY$DECLARE
	v_resultado VARCHAR(32000);
	v_colunas RECORD;
BEGIN
	v_resultado := '';

	FOR v_colunas IN (
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION) 
	LOOP
		v_resultado := v_resultado || lower(p_prefixo) || lower(v_colunas.nome) || ',';
	END LOOP;

	v_resultado := substr(v_resultado, 1, length(v_resultado) - 1);

	RETURN v_resultado;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  
  
CREATE OR REPLACE FUNCTION public.pkg_adm_obter_estrutura_tabela(
    p_esquema character varying,
    p_tabela character varying,
    p_prefixo_coluna character varying)
  RETURNS character varying AS
$BODY$
DECLARE
    v_resultado VARCHAR(32000);
    v_colunas RECORD;
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
    v_resultado := NULL;

    FOR v_colunas IN (
		SELECT c.COLUMN_NAME coluna, c.data_type tipo, 
		(case c.data_type when 'bigint' then null else c.numeric_precision end) tamanho, 
		c.numeric_scale decimais, c.character_maximum_length tamanho_char
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION
    ) LOOP

      IF (v_resultado IS NULL) THEN
        v_resultado := p_prefixo_coluna || v_colunas.coluna;
      ELSE
        v_resultado := v_resultado || ',' || p_prefixo_coluna || v_colunas.coluna;
      END IF;

      IF (v_colunas.tipo = 'bigint') THEN
        v_resultado := v_resultado || ' bigint';
      ELSEIF (v_colunas.tipo = 'timestamp') THEN
        v_resultado := v_resultado || ' timestamp(0)';
      ELSIF (v_colunas.tipo IN ('char', 'varchar', 'character varying')) THEN
        v_resultado := v_resultado || ' ' || v_colunas.tipo || '(' || v_colunas.tamanho_char || ')';
      ELSIF (v_colunas.decimais IS NOT NULL) THEN
        v_resultado := v_resultado || ' ' || v_colunas.tipo || '(' || v_colunas.tamanho || ',' || v_colunas.decimais || ')';
      ELSIF (v_colunas.tamanho IS NOT NULL) THEN
        v_resultado := v_resultado || ' ' || v_colunas.tipo || '(' || v_colunas.tamanho || ')';
      ELSE
        v_resultado := v_resultado || ' ' || v_colunas.tipo;
      END IF;

    END LOOP;

    RETURN v_resultado;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

CREATE OR REPLACE FUNCTION public.pkg_adm_criar_sequence(
    p_esquema character varying,
    p_tabela character varying)
  RETURNS character varying AS
$BODY$
DECLARE
	v_comando VARCHAR(4000);
BEGIN
  v_comando := 'CREATE SEQUENCE ' || LOWER(p_esquema) || '.log_' || lower(p_tabela) || '_seq minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 CYCLE;' || chr(13) || chr(10);
  RETURN v_comando;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE OR REPLACE FUNCTION public.pkg_adm_criar_tabela(
    p_esquema character varying,
    p_tabela character varying)
  RETURNS character varying AS
$BODY$
DECLARE                          
    v_comando VARCHAR(32000);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  

    v_comando := 'CREATE TABLE ' || LOWER(p_esquema) || '.log_' || LOWER(p_tabela) || ' (' || v_cr;
    v_comando := v_comando || '   id         BIGINT not null primary key,' || v_cr;
    v_comando := v_comando || '   usuario    varchar(30),' || v_cr;
    v_comando := v_comando || '   data       TIMESTAMP(0),' || v_cr;
    v_comando := v_comando || '   operacao   char(1),' || v_cr;
    v_comando := v_comando || '   ip         varchar(50),' || v_cr;
    v_comando := v_comando || '   ' || pkg_adm_obter_estrutura_tabela(p_esquema, p_tabela, 'x') || ',' || v_cr;
    v_comando := v_comando || '   ' || pkg_adm_obter_estrutura_tabela(p_esquema, p_tabela, '') ||');' || v_cr;

    --RAISE NOTICE '%', v_comando;
    RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
 
  
CREATE OR REPLACE FUNCTION public.pkg_adm_drop_auditoria(p_esquema character varying)
  RETURNS void AS
$BODY$
DECLARE                          
    tabela_rec RECORD;
    v_comando VARCHAR(32676);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
    v_comando := '';
    
     FOR tabela_rec in (
	select t.table_name
	from information_schema.tables t
	where t.table_schema=lower(p_esquema)
	and t.table_type='BASE TABLE'
      and t.table_name <> 'adm_usuario'
      and t.table_name not like 'log_%'
      and t.table_name not like 'vw_%'
      and t.table_name not like '%_aud'
      and t.table_name like 'adm_%'
      ORDER BY T.TABLE_NAME
     )
     
     LOOP
         v_comando := v_comando || 'DROP TRIGGER trg_' || lower(tabela_rec.table_name) || '_auid ON log_' || lower(tabela_rec.table_name) || ';' || v_cr;
         v_comando := v_comando || 'DROP FUNCTION trg_' || lower(tabela_rec.table_name) || '_aiud();' || v_cr;
         v_comando := v_comando || 'DROP SEQUENCE log_' || lower(tabela_rec.table_name) || '_seq;' || v_cr;
         v_comando := v_comando || '/*--------------------------------------------------------------*/' || v_cr;
     END LOOP;
     
         v_comando := v_comando || 'DROP TABLE log_ADM_PARAMETRO;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_PARAMETRO_CATEGORIA;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_PAGINA_PERFIL;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_MENU;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_LOG_COLUNA;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_FUNCIONARIO_PERFIL;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_FUNCIONARIO;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_FUNCIONALIDADE_PERFIL;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_FUNCIONALIDADE_PAGINA;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_FUNCIONALIDADE;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_PAGINA;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_CARGO_PERFIL;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_CARGO_FUNCIONARIO;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_CARGO;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_PERFIL;' || v_cr;
         v_comando := v_comando || 'DROP TABLE log_ADM_SETOR;' || v_cr;

     RAISE NOTICE '%', v_comando;
    --RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  
  
CREATE OR REPLACE FUNCTION public.pkg_adm_gerar_function_after(
    p_esquema character varying,
    p_tabela character varying)
  RETURNS character varying AS
$BODY$
DECLARE
    v_comando VARCHAR(32000);
    v_tabela  VARCHAR(30);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
    v_tabela := LOWER(p_tabela);

    v_comando := 'CREATE OR REPLACE FUNCTION ' || v_tabela;
    v_comando := v_comando || '_aiud() RETURNS trigger AS' || v_cr;
    v_comando := v_comando || '\$BODY\$' || v_cr;
    v_comando := v_comando || 'BEGIN' || v_cr;
    v_comando := v_comando || '  IF (TG_OP = ''INSERT'') THEN' || v_cr;
    v_comando := v_comando || '    INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, '')  || ') VALUES' || v_cr;
    v_comando := v_comando || '    (nextval(''' || LOWER(p_esquema) || '.log_' || v_tabela || '_seq''), current_timestamp, ''I'', coalesce(pkg_adm_obter_usuario(), session_user),' || v_cr;
    v_comando := v_comando || '    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),''/'')-1)),' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, 'NEW.') || ');' || v_cr;
    v_comando := v_comando || '    RETURN NEW;' || v_cr;
    v_comando := v_comando || '  ELSIF (TG_OP = ''UPDATE'') THEN' || v_cr;
    v_comando := v_comando || '    INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, 'X') || ',' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, '') || ') VALUES' || v_cr;
    v_comando := v_comando || '    (nextval(''' || LOWER(p_esquema) || '.log_' || v_tabela || '_seq''), current_timestamp, ''U'', coalesce(pkg_adm_obter_usuario(), session_user),' || v_cr;
    v_comando := v_comando || '    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),''/'')-1)),' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, 'OLD.') || ',' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, 'NEW.') || ');' || v_cr;
    v_comando := v_comando || '    RETURN NEW;' || v_cr;
    v_comando := v_comando || '  ELSIF (TG_OP = ''DELETE'') THEN' || v_cr;
    v_comando := v_comando || '        INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, '') || ')' || v_cr;
    v_comando := v_comando || ' VALUES' || v_cr;
    v_comando := v_comando || '    (nextval(''' || LOWER(p_esquema) || '.log_' || LOWER(p_tabela) || '_seq''), current_timestamp, ''D'', coalesce(pkg_adm_obter_usuario(), session_user),' || v_cr;
    v_comando := v_comando || '    coalesce(pkg_adm_obter_ip_usuario(), substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),''/'')-1)),' || v_cr;
    v_comando := v_comando || '    ' || pkg_adm_obter_colunas_tabela(p_esquema, p_tabela, 'OLD.') || ');' || v_cr;
    v_comando := v_comando || '    RETURN OLD;' || v_cr;
    v_comando := v_comando || '  END IF;' || v_cr;
    v_comando := v_comando || '  RETURN NULL;' || v_cr;
    v_comando := v_comando || 'END;' || v_cr;
    v_comando := v_comando || '\$BODY\$' || v_cr;
    v_comando := v_comando || '  LANGUAGE plpgsql VOLATILE' || v_cr;
    v_comando := v_comando || '  COST 100;' || v_cr;

    --RAISE NOTICE '%', v_comando;
    RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  
  

CREATE OR REPLACE FUNCTION public.pkg_adm_gerar_trigger_after(
    p_esquema character varying,
    p_tabela character varying)
  RETURNS character varying AS
$BODY$
DECLARE
    v_comando VARCHAR(32000);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
   v_comando := 'CREATE TRIGGER ' || LOWER(p_esquema) || '.' || 'trg_' || LOWER(p_tabela) || '_auid' || v_cr;
   v_comando := v_comando || 'AFTER INSERT OR UPDATE OR DELETE' || v_cr;
   v_comando := v_comando || 'ON ' || LOWER(p_esquema) || '.' || LOWER(p_tabela) || v_cr;
   v_comando := v_comando || 'FOR EACH ROW' || v_cr;
   v_comando := v_comando || 'EXECUTE PROCEDURE ' || LOWER(p_esquema) || '.' || LOWER(p_tabela) || '_auid();' || v_cr;

    --RAISE NOTICE '%', v_comando;
    RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE OR REPLACE FUNCTION public.pkg_adm_habilita_trigger_after(
    p_tabela character varying,
    p_habilita boolean)
  RETURNS character varying AS
$BODY$
DECLARE
    v_comando VARCHAR(32000);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  

    IF p_habilita THEN
      v_comando := 'ALTER TABLE ' || LOWER(p_tabela) || ' ENABLE TRIGGER trg_' || LOWER(p_tabela) || '_auid;' || v_cr;
    ELSE
      v_comando := 'ALTER TABLE ' || LOWER(p_tabela) || ' DISABLE TRIGGER trg_' || LOWER(p_tabela) || '_auid;' || v_cr;
    END IF;    

    --RAISE NOTICE '%', v_comando;
    RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  


CREATE OR REPLACE FUNCTION public.pkg_adm_auditar_tabela(
    p_esquema character varying,
    p_tabela character varying)
  RETURNS character varying AS
$BODY$
DECLARE                          
    v_comando VARCHAR(32676);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  

    v_comando := pkg_adm_criar_tabela(p_esquema, p_tabela) || v_cr;
    v_comando := v_comando || pkg_adm_criar_sequence(p_esquema, p_tabela) || v_cr;
    v_comando := v_comando || pkg_adm_gerar_function_after(p_esquema, p_tabela) || v_cr;
    v_comando := v_comando || pkg_adm_gerar_trigger_after(p_esquema, p_tabela) || v_cr;
    v_comando := v_comando || pkg_adm_habilita_trigger_after(p_tabela, true) || v_cr;

    RETURN v_comando;
  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;  
  
CREATE OR REPLACE FUNCTION public.pkg_adm_montar_auditoria(p_esquema character varying)
  RETURNS void AS
$BODY$
DECLARE                          
    tabela_rec RECORD;
  BEGIN
     FOR tabela_rec in (
	select t.table_name
	from information_schema.tables t
	where t.table_schema=lower(p_esquema)
	and t.table_type='BASE TABLE'
      and t.table_name <> 'adm_usuario'
      and t.table_name not like 'log_%'
      and t.table_name not like 'vw_%'
      and t.table_name not like '%_aud'
      and t.table_name like 'adm_%'
      ORDER BY T.TABLE_NAME
     )
     LOOP
         RAISE NOTICE '%', pkg_adm_auditar_tabela(p_esquema,tabela_rec.TABLE_NAME);
         RAISE NOTICE '--------------------------------------------------------------';
     END LOOP;
  end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
CREATE OR REPLACE FUNCTION public.pkg_adm_montar_view_adm_log(p_esquema character varying)
  RETURNS void AS
$BODY$
DECLARE
    tabela_rec RECORD;
    v_colunas RECORD;
  V_QTD bigint;
  V_COLUNA VARCHAR(100);
    v_comando VARCHAR(32676);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
   RAISE NOTICE '%', 'CREATE OR REPLACE VIEW VW_ADM_LOG_DADOS AS' || v_cr;
   
   FOR tabela_rec in (
	select t.table_name
	from information_schema.tables t
	where t.table_schema=lower(p_esquema)
	and t.table_type='BASE TABLE'
      and t.table_name <> 'adm_usuario'
      and t.table_name not like 'log_%'
      and t.table_name not like 'vw_%'
      and t.table_name not like '%_aud'
      and t.table_name like 'adm_%'
      ORDER BY T.TABLE_NAME
   )
     LOOP
	SELECT COUNT(*)
	INTO V_QTD
	FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
	INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
	WHERE kcu.table_schema=p_esquema
	and kcu.table_schema=tc.table_schema
	and kcu.table_name=tabela_rec.Table_NAME
	and kcu.table_name=tc.table_name
	and kcu.constraint_name = tc.CONSTRAINT_name
	and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

        IF V_QTD = 1 THEN
		SELECT '''' || kcu.column_name || ''''
		INTO V_COLUNA
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=tabela_rec.Table_NAME
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
        ELSE
          V_COLUNA := 'NULL';
        END IF;

          v_comando := 'SELECT t.usuario, t.data, t.operacao, t.ip, ''' || initcap(replace(substr(tabela_rec.table_name,5,length(tabela_rec.table_name)),'_',' '));
          v_comando := v_comando || ''' entidade, ''' || lower(tabela_rec.table_name) || ''' tabela, ' || V_COLUNA || ' chave, to_number(to_char(t.data,''YYYYMMDDHH24MISS''),''99999999999999'') dataNumero' || v_cr;
          v_comando := v_comando || '    FROM log_' || lower(tabela_rec.table_name) || ' t' || v_cr;		
          v_comando := v_comando || 'UNION';

	  RAISE NOTICE '%', v_comando;
   END LOOP;

   v_comando := 'CREATE OR REPLACE VIEW public.vw_adm_log AS ';
   v_comando := v_comando || 'SELECT row_number() OVER () AS id,v.usuario,v.data,v.operacao,v.ip, v.entidade,v.tabela,v.chave,v.datanumero ';
   v_comando := v_comando || 'FROM ( SELECT DISTINCT t.usuario,t.data,t.operacao,t.ip,t.entidade,t.tabela,t.chave,t.datanumero ';
   v_comando := v_comando || 'FROM vw_adm_log_dados t ';
   v_comando := v_comando || 'ORDER BY t.data DESC) v;';

   RAISE NOTICE '%', v_comando;

  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  

CREATE OR REPLACE FUNCTION public.pkg_adm_montar_view_adm_log_valor(p_esquema character varying)
  RETURNS void AS
$BODY$
DECLARE                          
    tabela_rec RECORD;
    v_colunas RECORD;
  V_QTD bigint;
  V_COLUNA VARCHAR(100);
    v_comando VARCHAR(32676);
    v_cr CHAR(2);
  BEGIN
    v_cr := chr(13) || chr(10);  
   RAISE NOTICE '%', 'CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR_DADOS AS' || v_cr;
   
   FOR tabela_rec in (
	select t.table_name
	from information_schema.tables t
	where t.table_schema=lower(p_esquema)
	and t.table_type='BASE TABLE'
      and t.table_name <> 'adm_usuario'
      and t.table_name not like 'log_%'
      and t.table_name not like 'vw_%'
      and t.table_name not like '%_aud'
      and t.table_name like 'adm_%'
      ORDER BY T.TABLE_NAME
   )
     LOOP
	SELECT COUNT(*)
	INTO V_QTD
	FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
	INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
	WHERE kcu.table_schema=p_esquema
	and kcu.table_schema=tc.table_schema
	and kcu.table_name=tabela_rec.Table_NAME
	and kcu.table_name=tc.table_name
	and kcu.constraint_name = tc.CONSTRAINT_name
	and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

        IF V_QTD = 1 THEN
		SELECT '''' || kcu.column_name || ''''
		INTO V_COLUNA
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=tabela_rec.Table_NAME
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
        ELSE
          V_COLUNA := 'NULL';
        END IF;

         FOR v_colunas IN (
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(tabela_rec.table_name)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION) 
	LOOP

          v_comando := 'SELECT t.usuario, t.data, t.operacao, t.ip, ''' || initcap(replace(substr(tabela_rec.table_NAME,5,length(tabela_rec.table_NAME)),'_',' '));
          v_comando := v_comando || ''' entidade, ''' || lower(tabela_rec.table_NAME) || ''' tabela, ' || V_COLUNA || ' chave, to_number(to_char(t.data,''YYYYMMDDHH24MISS''),''99999999999999'') dataNumero, ''';
          v_comando := v_comando || replace(lower(substr(v_colunas.nome,5,length(v_colunas.nome))),'_', ' ') || ''' coluna, cast(t.x' || lower(v_colunas.nome) ||  ' as varchar) valorAnterior, cast(t.' || lower(v_colunas.nome) || ' as varchar) valor' || v_cr;
          v_comando := v_comando || '    FROM log_' || lower(tabela_rec.table_NAME) || ' t' || v_cr;
          v_comando := v_comando || 'UNION';

	  RAISE NOTICE '%', v_comando;
     END LOOP;
   END LOOP;

   v_comando := 'CREATE OR REPLACE VIEW public.vw_adm_log_valor AS ';
   v_comando := v_comando || 'SELECT row_number() OVER () AS id,v.usuario,v.data,v.operacao,v.ip,v.entidade,v.tabela,v.chave,v.datanumero,v.coluna,v.valoranterior,v.valor ';
   v_comando := v_comando || 'FROM ( SELECT DISTINCT t.usuario,t.data,t.operacao,t.ip,t.entidade,t.tabela,t.chave,t.datanumero,t.coluna,t.valoranterior,t.valor ';
   v_comando := v_comando || 'FROM vw_adm_log_valor_dados t ';
   v_comando := v_comando || 'ORDER BY t.data DESC) v;';
   
   RAISE NOTICE '%', v_comando;

  END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
