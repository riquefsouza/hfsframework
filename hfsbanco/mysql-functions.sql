DELIMITER $$
CREATE FUNCTION row_number() 
RETURNS BIGINT
    DETERMINISTIC
BEGIN
	RETURN IF(@rownum, @rownum:=@rownum+1, @rownum:=1);
END$$
DELIMITER ;


DELIMITER $$
CREATE FUNCTION pkg_adm_obter_usuario() 
RETURNS varchar(30)
BEGIN
	RETURN IFNULL(@usuario_login, USER());
END$$
DELIMITER ;


DELIMITER $$
CREATE FUNCTION pkg_adm_obter_ip_usuario() 
RETURNS varchar(50)
BEGIN
	RETURN IF(locate('.',@usuario_ip)=0, '127.0.0.1', @usuario_ip);
END$$
DELIMITER ;


DELIMITER $$
CREATE FUNCTION pkg_adm_obter_colunas_tabela(
    p_esquema varchar(64),
    p_tabela varchar(64),
    p_prefixo varchar(64)) 
RETURNS text
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;
	DECLARE v_resultado TEXT;
    DECLARE v_nome_coluna varchar(64);
	DECLARE c_tabela CURSOR FOR
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;
    
    SET v_resultado = '';    
    
    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_coluna;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
    
        SET v_resultado = CONCAT(v_resultado, lower(p_prefixo), lower(v_nome_coluna), ',');

	END LOOP loop_tabela;

	CLOSE c_tabela;

	SET v_resultado = substr(v_resultado, 1, length(v_resultado) - 1);

	RETURN v_resultado;
END$$
DELIMITER ;

select pkg_adm_obter_colunas_tabela('hfsbanco','adm_funcionario','x')



DELIMITER $$
CREATE FUNCTION pkg_adm_obter_estrutura_tabela(
    p_esquema varchar(64),
    p_tabela varchar(64),
    p_prefixo_coluna varchar(64)) 
RETURNS text
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;
	DECLARE v_resultado TEXT;
    DECLARE v_nome_coluna varchar(64);
	DECLARE v_tipo_coluna varchar(64);
	DECLARE v_tam_coluna bigint;
	DECLARE v_decimais_coluna bigint;
	DECLARE v_tamanho_char bigint;
	DECLARE c_tabela CURSOR FOR
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
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;
  
    SET v_resultado = NULL;

    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_coluna, v_tipo_coluna, 
			v_tam_coluna, v_decimais_coluna, v_tamanho_char;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
    
      IF (v_resultado IS NULL) THEN
        SET v_resultado = CONCAT(p_prefixo_coluna, v_nome_coluna);
      ELSE
        SET v_resultado = CONCAT(v_resultado, ',', p_prefixo_coluna, v_nome_coluna);
      END IF;

      IF (v_tipo_coluna = 'bigint') THEN
        SET v_resultado = CONCAT(v_resultado, ' bigint');
      ELSEIF (v_tipo_coluna = 'timestamp') THEN
        SET v_resultado = CONCAT(v_resultado, ' timestamp(0)');
      ELSEIF (v_tipo_coluna IN ('char', 'varchar', 'character varying')) THEN
        SET v_resultado = CONCAT(v_resultado, ' ', v_tipo_coluna, '(', v_tamanho_char, ')');
      ELSEIF (v_decimais_coluna IS NOT NULL) THEN
        SET v_resultado = CONCAT(v_resultado, ' ', v_tipo_coluna, '(', v_tam_coluna, ',', v_decimais_coluna, ')');
      ELSEIF (v_tam_coluna IS NOT NULL) THEN
        SET v_resultado = CONCAT(v_resultado, ' ', v_tipo_coluna, '(', v_tam_coluna, ')');
      ELSE
        SET v_resultado = CONCAT(v_resultado, ' ', v_tipo_coluna);
      END IF;

	END LOOP loop_tabela;

	CLOSE c_tabela;

    RETURN v_resultado;
END$$
DELIMITER ;

select pkg_adm_obter_estrutura_tabela('hfsbanco','adm_funcionario','x')


DELIMITER $$
CREATE FUNCTION pkg_adm_criar_sequence(
    p_esquema varchar(64),
    p_tabela varchar(64))
RETURNS text
BEGIN
	DECLARE v_resultado TEXT;
	DECLARE v_cr CHAR(2);
	
	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	
	SET v_resultado = CONCAT('call CreateSequence(''log_', lower(p_tabela), '_seq'', 1, 1);', v_cr);
	
	RETURN v_resultado;
END$$
DELIMITER ;


select pkg_adm_criar_sequence('hfsbanco','adm_funcionario')


DELIMITER $$
CREATE FUNCTION pkg_adm_criar_tabela(
    p_esquema varchar(64),
    p_tabela varchar(64))
RETURNS text
BEGIN
	DECLARE v_comando TEXT;
	DECLARE v_cr CHAR(2);
	
	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	
	SET v_comando = CONCAT('CREATE TABLE ', LOWER(p_esquema), '.log_', LOWER(p_tabela), ' (', v_cr);
    SET v_comando = CONCAT(v_comando, '   id         BIGINT not null primary key,', v_cr);
    SET v_comando = CONCAT(v_comando, '   usuario    varchar(30),', v_cr);
    SET v_comando = CONCAT(v_comando, '   data       TIMESTAMP(0),', v_cr);
    SET v_comando = CONCAT(v_comando, '   operacao   char(1),', v_cr);
    SET v_comando = CONCAT(v_comando, '   ip         varchar(50),', v_cr);
    SET v_comando = CONCAT(v_comando, '   ', pkg_adm_obter_estrutura_tabela(p_esquema, p_tabela, 'x'), ',', v_cr);
    SET v_comando = CONCAT(v_comando, '   ', pkg_adm_obter_estrutura_tabela(p_esquema, p_tabela, ''), ');', v_cr);

    RETURN v_comando;
END$$
DELIMITER ;


select pkg_adm_criar_tabela('hfsbanco','adm_funcionario')


DELIMITER $$
CREATE PROCEDURE pkg_adm_drop_auditoria(p_esquema varchar(64), OUT p_resultado text)
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;
	DECLARE v_comando TEXT;
	DECLARE v_nome_tabela varchar(64);
	DECLARE v_cr CHAR(2);
	DECLARE c_tabela CURSOR FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;     

	SET v_cr = CONCAT(CHAR(13), CHAR(10));
    SET v_comando = '';

    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_tabela;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
    
		SET v_comando = CONCAT(v_comando, 'DROP TRIGGER trg_', lower(v_nome_tabela), '_ai;', v_cr);
		SET v_comando = CONCAT(v_comando, 'DROP TRIGGER trg_', lower(v_nome_tabela), '_au;', v_cr);
		SET v_comando = CONCAT(v_comando, 'DROP TRIGGER trg_', lower(v_nome_tabela), '_ad;', v_cr);
		SET v_comando = CONCAT(v_comando, 'DROP SEQUENCE log_', lower(v_nome_tabela), '_seq;', v_cr);
		SET v_comando = CONCAT(v_comando, '/*--------------------------------------------------------------*/', v_cr);

	END LOOP loop_tabela;

	CLOSE c_tabela;
     
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_PARAMETRO;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_PARAMETRO_CATEGORIA;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_PAGINA_PERFIL;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_MENU;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_LOG_COLUNA;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_FUNCIONARIO_PERFIL;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_FUNCIONARIO;', v_cr);	
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_FUNCIONALIDADE_PERFIL;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_FUNCIONALIDADE_PAGINA;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_FUNCIONALIDADE;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_PAGINA;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_CARGO_PERFIL;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_CARGO_FUNCIONARIO;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_CARGO;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_PERFIL;', v_cr);
	SET v_comando = CONCAT(v_comando, 'DROP TABLE log_ADM_SETOR;', v_cr);	

	 SET p_resultado = v_comando;
END$$
DELIMITER ;


CALL pkg_adm_drop_auditoria('hfsbanco', @resultado);
SELECT @resultado;


DELIMITER $$
CREATE FUNCTION pkg_adm_gerar_trigger_after(
    p_esquema varchar(64),
    p_tabela varchar(64))
RETURNS text
BEGIN
	DECLARE v_comando TEXT;
	DECLARE v_cr CHAR(2);
	DECLARE v_tab CHAR(2);
	
	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	SET v_tab = CHAR(9);
	
	SET v_comando = CONCAT('DELIMITER $$', v_cr);
	
	SET v_comando = CONCAT(v_comando, 'CREATE TRIGGER ', LOWER(p_esquema), '.trg_', LOWER(p_tabela), '_ai', v_cr);
    SET v_comando = CONCAT(v_comando, 'AFTER INSERT ON ', LOWER(p_tabela), v_cr);
	SET v_comando = CONCAT(v_comando, 'FOR EACH ROW', v_cr);
	SET v_comando = CONCAT(v_comando, 'BEGIN', v_cr);	
	SET v_comando = CONCAT(v_comando, v_tab, 'INSERT INTO ', LOWER(p_esquema), '.log_', LOWER(p_tabela), '(id,data,operacao,usuario,ip,', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), ''), ') VALUES', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, '(NextVal(''log_', LOWER(p_tabela), '_seq''), CURRENT_TIMESTAMP(), ''I'', ifnull(pkg_adm_obter_usuario(), USER()),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, 'ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), 'new.'), ');', v_cr);
	SET v_comando = CONCAT(v_comando, 'END$$', v_cr, v_cr);

	SET v_comando = CONCAT(v_comando, 'CREATE TRIGGER ', LOWER(p_esquema), '.trg_', LOWER(p_tabela), '_au', v_cr);
    SET v_comando = CONCAT(v_comando, 'AFTER UPDATE ON ', LOWER(p_tabela), v_cr);
	SET v_comando = CONCAT(v_comando, 'FOR EACH ROW', v_cr);
	SET v_comando = CONCAT(v_comando, 'BEGIN', v_cr);	
	SET v_comando = CONCAT(v_comando, v_tab, 'INSERT INTO ', LOWER(p_esquema), '.log_', LOWER(p_tabela), '(id,data,operacao,usuario,ip,', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), 'x'), ',', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), ''), ') VALUES', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, '(NextVal(''log_', LOWER(p_tabela), '_seq''), CURRENT_TIMESTAMP(), ''U'', ifnull(pkg_adm_obter_usuario(), USER()),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, 'ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), 'old.'), ',', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), 'new.'), ');', v_cr);
	SET v_comando = CONCAT(v_comando, 'END$$', v_cr, v_cr);

	SET v_comando = CONCAT(v_comando, 'CREATE TRIGGER ', LOWER(p_esquema), '.trg_', LOWER(p_tabela), '_ad', v_cr);
    SET v_comando = CONCAT(v_comando, 'AFTER DELETE ON ', LOWER(p_tabela), v_cr);
	SET v_comando = CONCAT(v_comando, 'FOR EACH ROW', v_cr);
	SET v_comando = CONCAT(v_comando, 'BEGIN', v_cr);	
	SET v_comando = CONCAT(v_comando, v_tab, 'INSERT INTO ', LOWER(p_esquema), '.log_', LOWER(p_tabela), '(id,data,operacao,usuario,ip,', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), ''), ') VALUES', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, '(NextVal(''log_', LOWER(p_tabela), '_seq''), CURRENT_TIMESTAMP(), ''D'', ifnull(pkg_adm_obter_usuario(), USER()),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, 'ifnull(pkg_adm_obter_ip_usuario(), (select host from information_schema.processlist WHERE ID=connection_id())),', v_cr);
	SET v_comando = CONCAT(v_comando, v_tab, pkg_adm_obter_colunas_tabela(LOWER(p_esquema), LOWER(p_tabela), 'old.'), ');', v_cr);
	SET v_comando = CONCAT(v_comando, 'END$$', v_cr, 'DELIMITER ;', v_cr, v_cr);
		
    RETURN v_comando;
END$$
DELIMITER ;


select pkg_adm_gerar_trigger_after('hfsbanco','adm_funcionario')


DELIMITER $$
CREATE FUNCTION pkg_adm_auditar_tabela(
    p_esquema varchar(64),
    p_tabela varchar(64))
RETURNS text
BEGIN
	DECLARE v_comando TEXT;
	DECLARE v_cr CHAR(2);
	
	SET v_cr = CONCAT(CHAR(13), CHAR(10));

    SET v_comando = CONCAT(pkg_adm_criar_tabela(p_esquema, p_tabela), v_cr);
    SET v_comando = CONCAT(v_comando, pkg_adm_criar_sequence(p_esquema, p_tabela), v_cr);
    SET v_comando = CONCAT(v_comando, pkg_adm_gerar_trigger_after(p_esquema, p_tabela), v_cr);

    RETURN v_comando;
END$$
DELIMITER ;


select pkg_adm_auditar_tabela('hfsbanco','adm_funcionario')


DELIMITER $$
CREATE PROCEDURE pkg_adm_montar_auditoria(p_esquema varchar(64), OUT p_resultado text)
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;
	DECLARE v_comando TEXT;
	DECLARE v_nome_tabela varchar(64);
	DECLARE v_cr CHAR(2);
	DECLARE c_tabela CURSOR FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;     

	SET v_cr = CONCAT(CHAR(13), CHAR(10));
    SET v_comando = '';

    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_tabela;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
    
		SET v_comando = CONCAT(v_comando, pkg_adm_auditar_tabela(LOWER(p_esquema),LOWER(v_nome_tabela)));
		SET v_comando = CONCAT(v_comando, '/*--------------------------------------------------------------*/', v_cr, v_cr);

	END LOOP loop_tabela;

	CLOSE c_tabela;
     
	SET p_resultado = v_comando;

END$$
DELIMITER ;


CALL pkg_adm_montar_auditoria('hfsbanco', @resultado);
SELECT @resultado;


DELIMITER $$
CREATE FUNCTION pkg_adm_initcap(p_nome varchar(255))
RETURNS varchar(255)
BEGIN
    RETURN CONCAT(UCASE(MID(p_nome,1,1)),LCASE(MID(p_nome,2)));
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE pkg_adm_montar_view_adm_log(p_esquema varchar(64), OUT p_resultado text)
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;
	DECLARE v_comando TEXT;
	DECLARE v_nome_tabela varchar(64);
	DECLARE v_qtd bigint;
  	DECLARE v_coluna varchar(100);	
	DECLARE v_cr CHAR(2);
	DECLARE c_tabela CURSOR FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;     

	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	SET v_comando = CONCAT('CREATE OR REPLACE VIEW VW_ADM_LOG_DADOS AS', v_cr);
	
    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_tabela;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
		
		SELECT COUNT(*) INTO v_qtd
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=v_nome_tabela
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

		IF v_qtd = 1 THEN
			SELECT CONCAT('''', kcu.column_name, '''')
			INTO v_coluna
			FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
			INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
			WHERE kcu.table_schema=p_esquema
			and kcu.table_schema=tc.table_schema
			and kcu.table_name=v_nome_tabela
			and kcu.table_name=tc.table_name
			and kcu.constraint_name = tc.CONSTRAINT_name
			and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
		ELSE
			SET v_coluna = 'NULL';
		END IF;
		
		SET v_comando = CONCAT(v_comando, 'SELECT t.usuario, t.data, t.operacao, t.ip, ''', pkg_adm_initcap(replace(substr(v_nome_tabela,5,length(v_nome_tabela)),'_',' ')));
		SET v_comando = CONCAT(v_comando, ''' entidade, ''', lower(v_nome_tabela), ''' tabela, ', V_COLUNA, ' chave, cast(date_format(t.data,''%Y%m%d%H%i%s'') as signed) dataNumero', v_cr);
		SET v_comando = CONCAT(v_comando, '    FROM log_', lower(v_nome_tabela), ' t', v_cr);
		SET v_comando = CONCAT(v_comando, 'UNION', v_cr);
		
	END LOOP loop_tabela;

	CLOSE c_tabela;

	SET v_comando = CONCAT(v_comando, v_cr, v_cr, 'CREATE OR REPLACE VIEW VW_ADM_LOG AS ', v_cr);
	SET v_comando = CONCAT(v_comando, 'SELECT DISTINCT row_number() as id, t.usuario,t.data,t.operacao,t.ip,t.entidade,t.tabela,t.chave,t.datanumero ', v_cr);
	SET v_comando = CONCAT(v_comando, 'FROM VW_ADM_LOG_DADOS t ', v_cr);
	SET v_comando = CONCAT(v_comando, 'ORDER BY t.data DESC;', v_cr);
	 
	SET p_resultado = v_comando;

END$$
DELIMITER ;


CALL pkg_adm_montar_view_adm_log('hfsbanco', @resultado);
SELECT @resultado;


DELIMITER $$
CREATE FUNCTION pkg_adm_montar_coluna_adm_log_valor(
    p_esquema varchar(64),
    p_tabela varchar(64),
	v_coluna varchar(100))
RETURNS text
BEGIN
	DECLARE h_coluna BOOLEAN DEFAULT FALSE;
	DECLARE v_comando TEXT;
	DECLARE v_cr CHAR(2);
	DECLARE v_nome_coluna varchar(64);
	DECLARE c_coluna CURSOR FOR
		SELECT c.COLUMN_NAME nome
		FROM INFORMATION_SCHEMA.COLUMNS c, information_schema.tables t
		WHERE C.TABLE_SCHEMA=lower(p_esquema)
		AND C.TABLE_SCHEMA=T.TABLE_SCHEMA
		AND c.TABLE_NAME=t.TABLE_NAME 
		AND t.TABLE_NAME =lower(p_tabela)
		AND t.table_type='BASE TABLE'
		AND c.data_type NOT IN ('bytea','text')
		ORDER BY c.TABLE_NAME, c.ORDINAL_POSITION;		
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_coluna = TRUE;

	SET v_comando = '';
	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	
	OPEN c_coluna;

	loop_coluna: LOOP
		FETCH c_coluna INTO v_nome_coluna;
		IF h_coluna THEN
			LEAVE loop_coluna;
		END IF;
	
		SET v_comando = CONCAT(v_comando, 'SELECT t.usuario, t.data, t.operacao, t.ip, ''', pkg_adm_initcap(replace(substr(p_tabela,5,length(p_tabela)),'_',' ')));
		SET v_comando = CONCAT(v_comando, ''' entidade, ''', lower(p_tabela), ''' tabela, ', v_coluna, ' chave, cast(date_format(t.data,''%Y%m%d%H%i%s'') as signed) dataNumero,', v_cr);
		SET v_comando = CONCAT(v_comando, '''', replace(lower(substr(v_nome_coluna,5,length(v_nome_coluna))),'_', ' '), ''' coluna, cast(t.x', lower(v_nome_coluna), ' as char) valorAnterior, cast(t.', lower(v_nome_coluna), ' as char) valor', v_cr);
		SET v_comando = CONCAT(v_comando, '    FROM log_', lower(p_tabela), ' t', v_cr);
		SET v_comando = CONCAT(v_comando, 'UNION', v_cr);
		
	END LOOP loop_coluna;
	
	CLOSE c_coluna;

    RETURN v_comando;
END$$
DELIMITER ;


select pkg_adm_montar_coluna_adm_log_valor('hfsbanco', 'adm_funcionario','''fun_codigo''');


DELIMITER $$
CREATE PROCEDURE pkg_adm_montar_view_adm_log_valor(p_esquema varchar(64), OUT p_resultado text)
BEGIN
	DECLARE h_tabela BOOLEAN DEFAULT FALSE;	
	DECLARE v_comando TEXT;
	DECLARE v_nome_tabela varchar(64);	
	DECLARE v_qtd bigint;
  	DECLARE v_coluna varchar(100);
	DECLARE v_cr CHAR(2);
	DECLARE c_tabela CURSOR FOR
		select t.table_name
		from information_schema.tables t
		where t.table_schema=lower(p_esquema)
		and t.table_type='BASE TABLE'
		  and t.table_name <> 'adm_usuario'
		  and t.table_name not like 'log_%'
		  and t.table_name not like 'vw_%'
		  and t.table_name not like '%_aud'
		  and t.table_name like 'adm_%'
		  ORDER BY T.TABLE_NAME;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET h_tabela = TRUE;		  

	SET v_cr = CONCAT(CHAR(13), CHAR(10));
	SET v_comando = CONCAT('CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR_DADOS AS', v_cr);
	
    OPEN c_tabela;

	loop_tabela: LOOP
		FETCH c_tabela INTO v_nome_tabela;
		IF h_tabela THEN
			LEAVE loop_tabela;
		END IF;
		
		SELECT COUNT(*) INTO v_qtd
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
		INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
		WHERE kcu.table_schema=p_esquema
		and kcu.table_schema=tc.table_schema
		and kcu.table_name=v_nome_tabela
		and kcu.table_name=tc.table_name
		and kcu.constraint_name = tc.CONSTRAINT_name
		and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');

		IF v_qtd = 1 THEN
			SELECT CONCAT('''', kcu.column_name, '''')
			INTO v_coluna
			FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc,
			INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
			WHERE kcu.table_schema=p_esquema
			and kcu.table_schema=tc.table_schema
			and kcu.table_name=v_nome_tabela
			and kcu.table_name=tc.table_name
			and kcu.constraint_name = tc.CONSTRAINT_name
			and tc.CONSTRAINT_TYPE IN ('PRIMARY KEY');
		ELSE
			SET v_coluna = 'NULL';
		END IF;

		SET v_comando = CONCAT(v_comando, pkg_adm_montar_coluna_adm_log_valor(p_esquema, v_nome_tabela, v_coluna));
		
	END LOOP loop_tabela;

	CLOSE c_tabela;

	SET v_comando = CONCAT(v_comando, v_cr, v_cr, 'CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR AS', v_cr);
	SET v_comando = CONCAT(v_comando, 'select distinct row_number() as id,v.usuario,v.data,v.operacao,v.ip,v.entidade,v.tabela,v.chave,v.datanumero,v.coluna,v.valoranterior,v.valor', v_cr);
	SET v_comando = CONCAT(v_comando, 'from vw_adm_log_valor_dados v', v_cr);
	SET v_comando = CONCAT(v_comando, 'order by v.data desc;', v_cr);
	 
	SET p_resultado = v_comando;

END$$
DELIMITER ;


CALL pkg_adm_montar_view_adm_log_valor('hfsbanco', @resultado);
SELECT @resultado;
