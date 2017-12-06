SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

SELECT  hostname,
        net_library,
        net_address,
        client_net_address
FROM    sys.sysprocesses AS S
INNER JOIN    sys.dm_exec_connections AS decc ON S.spid = decc.session_id
WHERE   spid = @@SPID;

SELECT CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address')) AS client_net_address;
SELECT SERVERPROPERTY(N'MachineName');



CREATE PROCEDURE pkg_adm_set_usuario(@p_login varchar(30), @p_ip varchar(20))
AS
BEGIN
	DECLARE @contexto VARBINARY(128);

	SELECT @contexto = 
		CAST(@p_login AS BINARY(30)) + 
		CAST(@p_ip AS BINARY(20));

	SET CONTEXT_INFO @contexto;
END
GO

CREATE FUNCTION pkg_adm_obter_usuario() 
RETURNS varchar(30)
AS
BEGIN
	DECLARE @usuario_aplicacao VARCHAR(30);
	
	SELECT @usuario_aplicacao = CAST(SUBSTRING(ci, 1, 30) AS VARCHAR)
	FROM (SELECT ci = CONTEXT_INFO()) t;

	IF @usuario_aplicacao IS NULL
	BEGIN
		SET @usuario_aplicacao = SESSION_USER;
	END;

	RETURN @usuario_aplicacao;
END
GO

CREATE FUNCTION pkg_adm_obter_ip_usuario() 
RETURNS varchar(20)
AS
BEGIN
	DECLARE @usuario_ip VARCHAR(20);
	
	SELECT @usuario_ip = CAST(SUBSTRING(ci, 31, 20) AS VARCHAR)
	FROM (SELECT ci = CONTEXT_INFO()) t;

	IF CHARINDEX('.',@usuario_ip) = 0
	BEGIN
		SET @usuario_ip = CONVERT(varchar(20), CONNECTIONPROPERTY('client_net_address'));
	END;
	
	RETURN @usuario_ip;
END
GO


CREATE FUNCTION pkg_adm_obter_colunas_tabela(
    @p_esquema varchar(64),
    @p_tabela varchar(64),
    @p_prefixo varchar(64),
    @p_sufixo varchar(64)) 
RETURNS VARCHAR(8000)
AS
BEGIN
	DECLARE @v_resultado VARCHAR(8000);
    DECLARE @v_nome_coluna varchar(64);
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(@p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(@p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;
    
    SET @v_resultado = '';    
    
    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_coluna;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
        SET @v_resultado = CONCAT(@v_resultado, lower(@p_prefixo), lower(@v_nome_coluna), lower(@p_sufixo), ',');
		
		FETCH NEXT FROM c_tabela INTO @v_nome_coluna;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;

	SET @v_resultado = substring(@v_resultado, 1, len(@v_resultado) - 1);

	RETURN @v_resultado;
END
GO

select dbo.pkg_adm_obter_colunas_tabela('dbo','adm_funcionario','x','');



CREATE FUNCTION pkg_adm_obter_estrutura_tabela(
    @p_esquema varchar(64),
    @p_tabela varchar(64),
    @p_prefixo_coluna varchar(64)) 
RETURNS VARCHAR(8000)
AS
BEGIN
	DECLARE @v_resultado VARCHAR(8000);
    DECLARE @v_nome_coluna varchar(64);
	DECLARE @v_tipo_coluna varchar(64);
	DECLARE @v_tam_coluna bigint;
	DECLARE @v_decimais_coluna bigint;
	DECLARE @v_tamanho_char bigint;
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		SELECT c.COLUMN_NAME coluna, c.data_type tipo, 
		(case c.data_type when 'bigint' then null else c.numeric_precision end) tamanho, 
		c.numeric_scale decimais, c.character_maximum_length tamanho_char
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(@p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(@p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;
  
    SET @v_resultado = NULL;

    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_coluna, @v_tipo_coluna, 
			@v_tam_coluna, @v_decimais_coluna, @v_tamanho_char;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
    
		IF @v_resultado IS NULL BEGIN
			SET @v_resultado = CONCAT(@p_prefixo_coluna, @v_nome_coluna);
		END	ELSE BEGIN
			SET @v_resultado = CONCAT(@v_resultado, ',', @p_prefixo_coluna, @v_nome_coluna);
		END;

		IF (@v_tipo_coluna = 'bigint') 
		BEGIN
			SET @v_resultado = CONCAT(@v_resultado, ' bigint');
		END
		ELSE IF (@v_tipo_coluna IN ('char', 'varchar', 'nvarchar')) BEGIN
			SET @v_resultado = CONCAT(@v_resultado, ' ', @v_tipo_coluna, '(', @v_tamanho_char, ')');
		END
		ELSE IF (@v_decimais_coluna IS NOT NULL) BEGIN
				SET @v_resultado = CONCAT(@v_resultado, ' ', @v_tipo_coluna, '(', @v_tam_coluna, ',', @v_decimais_coluna, ')');
		END
		ELSE IF (@v_tam_coluna IS NOT NULL) BEGIN
				SET @v_resultado = CONCAT(@v_resultado, ' ', @v_tipo_coluna, '(', @v_tam_coluna, ')');
		END 
		ELSE BEGIN
			SET @v_resultado = CONCAT(@v_resultado, ' ', @v_tipo_coluna);
		END;

		FETCH NEXT FROM c_tabela INTO @v_nome_coluna, @v_tipo_coluna, 
				@v_tam_coluna, @v_decimais_coluna, @v_tamanho_char;
	  
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;

    RETURN @v_resultado;
END
GO

select dbo.pkg_adm_obter_estrutura_tabela('dbo','adm_funcionario','x')


CREATE PROCEDURE pkg_adm_criar_sequence(
    @p_esquema varchar(64),
    @p_tabela varchar(64))
AS
BEGIN
	DECLARE @v_cr CHAR(2);
	
	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	
	PRINT 'CREATE SEQUENCE ' + LOWER(@p_esquema) + '.log_' + lower(@p_tabela) +
		'_seq as bigint minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1 no cache cycle;' + @v_cr;
END
GO

exec dbo.pkg_adm_criar_sequence 'dbo','adm_funcionario'


CREATE PROCEDURE pkg_adm_criar_tabela(
    @p_esquema varchar(64),
    @p_tabela varchar(64))
AS
BEGIN
	DECLARE @v_comando VARCHAR(8000);
	DECLARE @v_cr CHAR(2);
	
	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	
	SET @v_comando = CONCAT('CREATE TABLE ', LOWER(@p_esquema), '.log_', LOWER(@p_tabela), ' (', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   id         BIGINT not null primary key,', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   usuario    varchar(30),', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   data       datetime,', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   operacao   char(1),', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   ip         nvarchar(50),', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   ', dbo.pkg_adm_obter_estrutura_tabela(@p_esquema, @p_tabela, 'x'), ',', @v_cr);
    SET @v_comando = CONCAT(@v_comando, '   ', dbo.pkg_adm_obter_estrutura_tabela(@p_esquema, @p_tabela, ''), ');', @v_cr);

    PRINT @v_comando;
END
GO

exec dbo.pkg_adm_criar_tabela 'dbo','adm_funcionario'


CREATE PROCEDURE pkg_adm_drop_auditoria(@p_esquema varchar(64))
AS
BEGIN
	DECLARE @v_nome_tabela varchar(64);
	DECLARE @v_cr CHAR(2);
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(@p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;

	SET @v_cr = CONCAT(CHAR(13), CHAR(10));

    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_tabela;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
		PRINT 'DROP TRIGGER trg_' + lower(@v_nome_tabela) + '_ai;' + @v_cr;
		PRINT 'DROP TRIGGER trg_' + lower(@v_nome_tabela) + '_au;' + @v_cr;
		PRINT 'DROP TRIGGER trg_' + lower(@v_nome_tabela) + '_ad;' + @v_cr;
		PRINT 'DROP SEQUENCE log_' + lower(@v_nome_tabela) + '_seq;' + @v_cr;
		PRINT '/*--------------------------------------------------------------*/' + @v_cr;
		
		FETCH NEXT FROM c_tabela INTO @v_nome_tabela;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;
     
	PRINT 'DROP TABLE log_ADM_PARAMETRO;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_PARAMETRO_CATEGORIA;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_PAGINA_PERFIL;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_MENU;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_LOG_COLUNA;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_FUNCIONARIO_PERFIL;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_FUNCIONARIO;' + @v_cr;	
	PRINT 'DROP TABLE log_ADM_FUNCIONALIDADE_PERFIL;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_FUNCIONALIDADE_PAGINA;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_FUNCIONALIDADE;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_PAGINA;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_CARGO_PERFIL;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_CARGO_FUNCIONARIO;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_CARGO;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_PERFIL;' + @v_cr;
	PRINT 'DROP TABLE log_ADM_SETOR;' + @v_cr;	
END
GO

exec dbo.pkg_adm_drop_auditoria 'dbo'


CREATE FUNCTION pkg_adm_obter_pkcolunas_tabela(
    @p_esquema varchar(64),
    @p_tabela varchar(64),
    @p_prefixo varchar(64),
    @p_sufixo varchar(64)) 
RETURNS VARCHAR(8000)
AS
BEGIN
    DECLARE @v_resultado VARCHAR(8000);
    DECLARE @v_nome_coluna varchar(64);
    DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
	SELECT kcu.column_name
	FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
	INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
	WHERE kcu.table_schema=lower(@p_esquema)
	and kcu.table_schema=tc.table_schema
	and kcu.table_name=lower(@p_tabela)
	and kcu.table_name=tc.table_name
	and kcu.constraint_name = tc.CONSTRAINT_name
	and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
    
    SET @v_resultado = '';    
    
    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_coluna;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
	SET @v_resultado = CONCAT(@v_resultado, @p_prefixo, lower(@v_nome_coluna), @p_sufixo, lower(@v_nome_coluna), ' AND ');
		
		FETCH NEXT FROM c_tabela INTO @v_nome_coluna;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;

	SET @v_resultado = substring(@v_resultado, 1, len(@v_resultado) - 4);

	RETURN @v_resultado;
END
GO

select dbo.pkg_adm_obter_pkcolunas_tabela('dbo','adm_cargo_funcionario','INSERTED.',' = DELETED.');


CREATE PROCEDURE pkg_adm_gerar_trigger_after(
    @p_esquema varchar(64),
    @p_tabela varchar(64))
AS
BEGIN
	DECLARE @v_cr CHAR(2);
	DECLARE @v_tab CHAR(2);
	
	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	SET @v_tab = CHAR(9);
	
	PRINT 'CREATE TRIGGER ' + LOWER(@p_esquema) + '.trg_' + LOWER(@p_tabela) + '_auid' + @v_cr;
	PRINT 'ON ' + LOWER(@p_esquema) + '.' + LOWER(@p_tabela) + @v_cr;
    PRINT 'AFTER INSERT, UPDATE, DELETE' + @v_cr;
	PRINT 'AS' + @v_cr;
	PRINT 'BEGIN' + @v_cr;
	
	PRINT @v_tab + '-- AFTER INSERT' + @v_cr;
	PRINT @v_tab + 'INSERT INTO ' + LOWER(@p_esquema) + '.log_' + LOWER(@p_tabela) + '(id,data,operacao,usuario,ip,' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ')' + @v_cr;
	PRINT @v_tab + 'SELECT NEXT VALUE FOR log_' + LOWER(@p_tabela) + '_seq, CURRENT_TIMESTAMP, ''I'', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),' + @v_cr;
	PRINT @v_tab + 'isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY(''client_net_address''))),' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ' FROM INSERTED' + @v_cr;
	PRINT @v_tab + 'WHERE NOT EXISTS (SELECT * FROM DELETED WHERE ' + dbo.pkg_adm_obter_pkcolunas_tabela(@p_esquema, @p_tabela,'INSERTED.',' = DELETED.') + ');' + @v_cr;
	
	PRINT @v_tab + '-- AFTER UPDATE' + @v_cr;
	PRINT @v_tab + 'INSERT INTO ' + LOWER(@p_esquema) + '.log_' + LOWER(@p_tabela) + '(id,data,operacao,usuario,ip,' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), 'x', '') + ',' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ')' + @v_cr;
	PRINT @v_tab + 'SELECT NEXT VALUE FOR log_' + LOWER(@p_tabela) + '_seq, CURRENT_TIMESTAMP, ''U'', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),' + @v_cr;
	PRINT @v_tab + 'isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY(''client_net_address''))),' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '(SELECT ', ' FROM DELETED)') + ',' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ' FROM INSERTED' + @v_cr;
	PRINT @v_tab + 'WHERE EXISTS (SELECT * FROM DELETED WHERE ' + dbo.pkg_adm_obter_pkcolunas_tabela(@p_esquema, @p_tabela,'INSERTED.',' = DELETED.') + ');' + @v_cr;

	PRINT @v_tab + '-- AFTER DELETE' + @v_cr;
	PRINT @v_tab + 'INSERT INTO ' + LOWER(@p_esquema) + '.log_' + LOWER(@p_tabela) + '(id,data,operacao,usuario,ip,' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ')' + @v_cr;
	PRINT @v_tab + 'SELECT NEXT VALUE FOR log_' + LOWER(@p_tabela) + '_seq, CURRENT_TIMESTAMP, ''D'', isnull(dbo.pkg_adm_obter_usuario(), SESSION_USER),' + @v_cr;
	PRINT @v_tab + 'isnull(dbo.pkg_adm_obter_ip_usuario(), CONVERT(varchar(20), CONNECTIONPROPERTY(''client_net_address''))),' + @v_cr;
	PRINT @v_tab + dbo.pkg_adm_obter_colunas_tabela(LOWER(@p_esquema), LOWER(@p_tabela), '', '') + ' FROM DELETED' + @v_cr;
	PRINT @v_tab + 'WHERE NOT EXISTS (SELECT * FROM INSERTED WHERE ' + dbo.pkg_adm_obter_pkcolunas_tabela(@p_esquema), @p_tabela,'DELETED.',' = INSERTED.') + ');' + @v_cr;

	PRINT 'END' + @v_cr;
	PRINT 'GO' + @v_cr + @v_cr;
END
GO

exec dbo.pkg_adm_gerar_trigger_after 'dbo','adm_funcionario'


CREATE PROCEDURE pkg_adm_auditar_tabela(
    @p_esquema varchar(64),
    @p_tabela varchar(64))
AS	
BEGIN
	DECLARE @v_cr CHAR(2);
	
	SET @v_cr = CONCAT(CHAR(13), CHAR(10));

	exec dbo.pkg_adm_criar_tabela @p_esquema, @p_tabela;
	PRINT @v_cr;
    exec dbo.pkg_adm_criar_sequence @p_esquema, @p_tabela;
	PRINT @v_cr;
    exec dbo.pkg_adm_gerar_trigger_after @p_esquema, @p_tabela;
	PRINT @v_cr;
END
GO

exec dbo.pkg_adm_auditar_tabela 'dbo','adm_funcionario'


CREATE PROCEDURE pkg_adm_montar_auditoria(@p_esquema varchar(64))
AS
BEGIN
	DECLARE @v_nome_tabela varchar(64);
	DECLARE @v_cr CHAR(2);
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(@p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;

	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
     
    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_tabela;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
		exec dbo.pkg_adm_auditar_tabela @p_esquema,@v_nome_tabela;
		PRINT '/*--------------------------------------------------------------*/' + @v_cr + @v_cr;
		
		FETCH NEXT FROM c_tabela INTO @v_nome_tabela;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;
END
GO

exec dbo.pkg_adm_montar_auditoria 'dbo';


CREATE FUNCTION pkg_adm_initcap(@p_nome varchar(255))
RETURNS varchar(255)
AS
BEGIN
	RETURN CONCAT(UPPER(SUBSTRING(@p_nome,1,1)),LOWER(SUBSTRING(@p_nome,2,LEN(@p_nome))));
END
GO

CREATE PROCEDURE pkg_adm_montar_view_adm_log(@p_esquema varchar(64))
AS
BEGIN
	DECLARE @v_nome_tabela varchar(64);
	DECLARE @v_qtd bigint;
  	DECLARE @v_coluna varchar(100);	
	DECLARE @v_cr CHAR(2);
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(@p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;

	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	PRINT 'CREATE VIEW VW_ADM_LOG_DADOS AS' + @v_cr;
	
    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_tabela;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 		
		SELECT @v_qtd = COUNT(*)
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=@p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=@v_nome_tabela
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

		IF @v_qtd = 1 BEGIN
			SELECT @v_coluna = CONCAT('''', kcu.column_name, '''')
			FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
			INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
			WHERE kcu.table_schema=@p_esquema
			and kcu.table_schema=tc.table_schema
			and kcu.table_name=@v_nome_tabela
			and kcu.table_name=tc.table_name
			and kcu.constraint_name = tc.CONSTRAINT_name
			and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
		END ELSE BEGIN
			SET @v_coluna = 'NULL';
		END;
		
		PRINT 'SELECT t.usuario, t.data, t.operacao, t.ip, ''' + dbo.pkg_adm_initcap(replace(substring(@v_nome_tabela,5,len(@v_nome_tabela)),'_',' ')) + 
		''' entidade, ''' + lower(@v_nome_tabela) + ''' tabela, ' + @v_coluna + ' chave, cast(t.data as datetime) dataNumero' + @v_cr;
		PRINT '    FROM log_' + lower(@v_nome_tabela) + ' t' + @v_cr;
		PRINT 'UNION' + @v_cr;
		
		FETCH NEXT FROM c_tabela INTO @v_nome_tabela;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;

	PRINT @v_cr + @v_cr + 'CREATE VIEW VW_ADM_LOG AS ' + @v_cr;
	PRINT 'SELECT DISTINCT row_number() OVER (ORDER BY t.data DESC) as id, t.usuario,t.data,t.operacao,t.ip,t.entidade,t.tabela,t.chave,t.datanumero ' + @v_cr;
	PRINT 'FROM VW_ADM_LOG_DADOS t;' + @v_cr;
	 
END
GO

exec dbo.pkg_adm_montar_view_adm_log 'dbo';


CREATE PROCEDURE pkg_adm_montar_coluna_adm_log_valor(
    @p_esquema varchar(64),
    @p_tabela varchar(64),
	@v_coluna varchar(100))
AS
BEGIN
	DECLARE @v_cr CHAR(2);
	DECLARE @v_nome_coluna varchar(64);
	DECLARE c_coluna CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(@p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(@p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;		

	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	
	OPEN c_coluna;
	FETCH NEXT FROM c_coluna INTO @v_nome_coluna;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 	
		PRINT 'SELECT t.usuario, t.data, t.operacao, t.ip, ''' + dbo.pkg_adm_initcap(replace(substring(@p_tabela,5,len(@p_tabela)),'_',' ')) +
		''' entidade, ''' + lower(@p_tabela) + ''' tabela, ' + @v_coluna + ' chave, cast(t.data as datetime) dataNumero,' + @v_cr;
		PRINT '''' + replace(lower(substring(@v_nome_coluna,5,len(@v_nome_coluna))),'_', ' ') + ''' coluna, cast(t.x' + lower(@v_nome_coluna) + ' as char) valorAnterior, cast(t.' + lower(@v_nome_coluna) + ' as char) valor' + @v_cr;
		PRINT '    FROM log_' + lower(@p_tabela) + ' t' + @v_cr;
		PRINT 'UNION' + @v_cr;
		
		FETCH NEXT FROM c_coluna INTO @v_nome_coluna;
	END;

	CLOSE c_coluna;
	DEALLOCATE c_coluna;
END
GO

exec dbo.pkg_adm_montar_coluna_adm_log_valor 'dbo', 'adm_funcionario','''fun_codigo''';


CREATE PROCEDURE pkg_adm_montar_view_adm_log_valor(@p_esquema varchar(64))
AS
BEGIN
	DECLARE @v_nome_tabela varchar(64);	
	DECLARE @v_qtd bigint;
  	DECLARE @v_coluna varchar(100);
	DECLARE @v_cr CHAR(2);
	DECLARE c_tabela CURSOR LOCAL FAST_FORWARD READ_ONLY FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(@p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;

	SET @v_cr = CONCAT(CHAR(13), CHAR(10));
	PRINT 'CREATE VIEW VW_ADM_LOG_VALOR_DADOS AS' + @v_cr;
	
    OPEN c_tabela;
	FETCH NEXT FROM c_tabela INTO @v_nome_tabela;

	WHILE (@@FETCH_STATUS = 0)
	BEGIN 
		
		SELECT @v_qtd = COUNT(*)
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=@p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=@v_nome_tabela
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

		IF @v_qtd = 1 BEGIN
			SELECT @v_coluna = CONCAT('''', kcu.column_name, '''')
			FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
			INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
			WHERE kcu.table_schema=@p_esquema
			and kcu.table_schema=tc.table_schema
			and kcu.table_name=@v_nome_tabela
			and kcu.table_name=tc.table_name
			and kcu.constraint_name = tc.CONSTRAINT_name
			and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
		END ELSE BEGIN
			SET @v_coluna = 'NULL';
		END;

		exec dbo.pkg_adm_montar_coluna_adm_log_valor @p_esquema, @v_nome_tabela, @v_coluna;
		
		FETCH NEXT FROM c_tabela INTO @v_nome_tabela;
	END;

	CLOSE c_tabela;
	DEALLOCATE c_tabela;

	PRINT @v_cr + @v_cr + 'CREATE VIEW VW_ADM_LOG_VALOR AS' + @v_cr;
	PRINT 'select distinct row_number() OVER (ORDER BY v.data DESC) as id,v.usuario,v.data,v.operacao,v.ip,v.entidade,v.tabela,v.chave,v.datanumero,v.coluna,v.valoranterior,v.valor' + @v_cr;
	PRINT 'from vw_adm_log_valor_dados v;' + @v_cr;

END
GO


exec dbo.pkg_adm_montar_view_adm_log_valor 'dbo';

