CREATE OR REPLACE PACKAGE pkg_adm AS

  PROCEDURE setar_usuario_ip(p_usuario_aplicacao    VARCHAR2,
                            p_ip_usuario_aplicacao VARCHAR2);
  FUNCTION obter_usuario RETURN VARCHAR2;
  FUNCTION obter_ip_usuario RETURN VARCHAR2;
  PROCEDURE auditar_tabela(p_esquema          VARCHAR2,
                             p_tabela           VARCHAR2,
                             p_tabela_abreviada VARCHAR2);
  PROCEDURE gerar_trigger_after(p_esquema          VARCHAR2,
                                  p_tabela           VARCHAR2,
                                  p_tabela_abreviada VARCHAR2);
  PROCEDURE recompilar_objetos_invalidos(p_esquema VARCHAR2);

  PROCEDURE criar_tabela(p_esquema VARCHAR2,
                           p_tabela  VARCHAR2,
                           p_tabela_abreviada VARCHAR2);

  PROCEDURE montar_auditoria(p_esquema VARCHAR2);

  procedure montar_view_adm_log_valor(p_esquema VARCHAR2);

  PROCEDURE montar_view_adm_log(p_esquema VARCHAR2);

  PROCEDURE drop_auditoria(p_esquema VARCHAR2);

END pkg_adm;


CREATE OR REPLACE PACKAGE BODY pkg_adm AS

  v_usuario_aplicacao   VARCHAR2(30);
  v_ip_usuario_aplicacao VARCHAR2(50);

  PROCEDURE setar_usuario_ip(p_usuario_aplicacao    VARCHAR2,
                            p_ip_usuario_aplicacao VARCHAR2) AS
  BEGIN
    v_usuario_aplicacao    := p_usuario_aplicacao;
    v_ip_usuario_aplicacao := p_ip_usuario_aplicacao;
  END;

  FUNCTION obter_usuario RETURN VARCHAR2 AS
  BEGIN
    RETURN v_usuario_aplicacao;
  END;

  FUNCTION obter_ip_usuario RETURN VARCHAR2 AS
  BEGIN
    RETURN v_ip_usuario_aplicacao;
  END;

  FUNCTION obter_colunas_tabela(p_esquema VARCHAR2,
                                  p_tabela  VARCHAR2,
                                  p_prefixo VARCHAR2) RETURN VARCHAR2 AS
    v_resultado VARCHAR2(32000);
  BEGIN
    v_resultado := '';

    FOR v_colunas IN (SELECT colunas.column_name nome
                          FROM all_tables tabelas JOIN user_tab_columns colunas ON (tabelas.table_name = colunas.table_name)
                          WHERE tabelas.owner = p_esquema AND
                                tabelas.table_name = p_tabela AND
                                colunas.data_type NOT IN ('BLOB', 'CLOB', 'LONG')) LOOP

      v_resultado := v_resultado || lower(p_prefixo) || lower(v_colunas.nome) || ',';
    END LOOP;

    v_resultado := substr(v_resultado,
                          1,
                          length(v_resultado) - 1);

    RETURN v_resultado;
  END;

  PROCEDURE criar_sequence(p_esquema          VARCHAR2,
                             p_tabela_abreviada VARCHAR2) AS
    v_comando    VARCHAR2(4000);
    v_sequencia  VARCHAR2(30);
  BEGIN
     v_sequencia := p_tabela_abreviada;

    v_comando := 'CREATE SEQUENCE ' || LOWER(p_esquema) || '.log_' || v_sequencia || '_seq minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache cycle order;';

    --EXECUTE IMMEDIATE v_comando;
  DBMS_OUTPUT.put_line(v_comando);
  END;

  PROCEDURE gerar_trigger_after(p_esquema          VARCHAR2,
                                  p_tabela           VARCHAR2,
                                  p_tabela_abreviada VARCHAR2) AS
    v_comando VARCHAR2(32000);
    v_tabela  VARCHAR2(30);
  BEGIN
      v_tabela := LOWER(p_tabela_abreviada);

    v_comando := 'CREATE OR REPLACE TRIGGER ' || LOWER(p_esquema) || '.' || 'trg_' || v_tabela ||
                 '_auid AFTER INSERT OR UPDATE OR DELETE ON ' || LOWER(p_esquema) || '.' || LOWER(p_tabela) || '
    FOR EACH ROW
    BEGIN
      IF (INSERTING) THEN
        INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,
        ' || obter_colunas_tabela(p_esquema, p_tabela, NULL)  || ') VALUES
        (' || LOWER(p_esquema) || '.log_' || v_tabela || '_seq.nextval, SYSDATE, ''I'', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT(''USERENV'', ''IP_ADDRESS'', 15)),
        ' || obter_colunas_tabela(p_esquema, p_tabela, ':NEW.') || ');
      ELSIF (UPDATING) THEN
        INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,
        ' || obter_colunas_tabela(p_esquema, p_tabela, 'X') || ',
        ' || obter_colunas_tabela(p_esquema, p_tabela, NULL) || ') VALUES
        (' || LOWER(p_esquema) || '.log_' || v_tabela || '_seq.nextval, SYSDATE, ''U'', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT(''USERENV'', ''IP_ADDRESS'', 15)),
        ' || obter_colunas_tabela(p_esquema, p_tabela, ':OLD.') || ',
        ' || obter_colunas_tabela(p_esquema, p_tabela, ':NEW.') || ');
      ELSE
            INSERT INTO ' || LOWER(p_esquema) || '.log_' || v_tabela || '(id,data,operacao,usuario,ip,
            ' || obter_colunas_tabela(p_esquema, p_tabela, NULL) || ')' || ' VALUES
        (' || LOWER(p_esquema) || '.log_' || v_tabela || '_seq.nextval, SYSDATE, ''D'', NVL(pkg_adm.obter_usuario, USER),
        NVL(pkg_adm.obter_ip_usuario, SYS_CONTEXT(''USERENV'', ''IP_ADDRESS'', 15)),
        ' || obter_colunas_tabela(p_esquema, p_tabela, ':OLD.') || ');
      END IF;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
END;
/';

    --EXECUTE IMMEDIATE v_comando;
  DBMS_OUTPUT.put_line(v_comando);
  END;


  PROCEDURE habilita_trigger_after(p_tabela_abreviada VARCHAR2, p_habilita boolean) AS
    v_comando VARCHAR2(32000);
    v_tabela  VARCHAR2(30);
  BEGIN
      v_tabela := LOWER(p_tabela_abreviada);

    IF p_habilita THEN
      v_comando := 'ALTER TRIGGER trg_' || v_tabela || '_auid ENABLE;';
    ELSE
      v_comando := 'ALTER TRIGGER trg_' || v_tabela || '_auid DISABLE;';
    END IF;

    --EXECUTE IMMEDIATE v_comando;
  DBMS_OUTPUT.put_line(v_comando);
  END;

  FUNCTION obter_estrutura_tabela(p_esquema VARCHAR2,
                                    p_tabela  VARCHAR2,
                                    p_prefixo_coluna VARCHAR2) RETURN VARCHAR2 AS
    v_resultado VARCHAR2(32000);
  BEGIN
    v_resultado := NULL;

    FOR v_colunas IN (SELECT colunas.column_name coluna,
                             colunas.data_type tipo,
                             decode(colunas.data_precision,
                                    NULL,
                                    colunas.char_col_decl_length,
                                    colunas.data_precision) tamanho,
                             colunas.data_scale decimais,
                             colunas.char_length tamanho_char
                        FROM all_tables tabelas JOIN user_tab_columns colunas ON (tabelas.table_name = colunas.table_name)
                       WHERE tabelas.owner = p_esquema AND
                             tabelas.table_name = p_tabela AND
                             colunas.data_type NOT IN ('BLOB', 'CLOB', 'LONG')) LOOP

      IF (v_resultado IS NULL) THEN
        v_resultado := p_prefixo_coluna || v_colunas.coluna;
      ELSE
        v_resultado := v_resultado || ',' || p_prefixo_coluna || v_colunas.coluna;
      END IF;

      IF (v_colunas.tipo = 'DATE') THEN
        v_resultado := v_resultado || ' DATE';
      ELSIF (v_colunas.tipo IN ('CHAR', 'VARCHAR', 'VARCHAR2')) THEN
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

  PROCEDURE criar_tabela(p_esquema VARCHAR2,
                           p_tabela  VARCHAR2, p_tabela_abreviada  VARCHAR2) AS
    v_comando VARCHAR2(32000);
  BEGIN
    v_comando := 'CREATE TABLE ' || LOWER(p_esquema) || '.log_' || LOWER(p_tabela_abreviada) || ' (
                     id         NUMBER(20) not null primary key,
                     usuario    varchar2(30),
                     data       DATE,
                     operacao   char(1),
                     ip         varchar2(50),' ||
                 obter_estrutura_tabela(p_esquema, p_tabela, 'X') || ',' ||
                 obter_estrutura_tabela(p_esquema, p_tabela, NULL) ||');';

    --EXECUTE IMMEDIATE v_comando;
  DBMS_OUTPUT.put_line(v_comando);
  END;

  /*--------------------------------------------------------------*/

  PROCEDURE auditar_tabela(p_esquema          VARCHAR2,
                             p_tabela           VARCHAR2,
                             p_tabela_abreviada VARCHAR2) AS
  BEGIN
    criar_tabela(p_esquema, p_tabela,p_tabela_abreviada);
    criar_sequence(p_esquema, p_tabela_abreviada);
    gerar_trigger_after(p_esquema, p_tabela, p_tabela_abreviada);
    habilita_trigger_after(p_tabela_abreviada, true);
  END;

  /*--------------------------------------------------------------*/

  PROCEDURE recompilar_objetos_invalidos(p_esquema VARCHAR2) AS
    v_count NUMBER(5);
  BEGIN
    SELECT COUNT(*)
       INTO v_count
       FROM all_objects
       WHERE owner = p_esquema AND
             status = 'INVALID' AND
             rownum = 1;

    IF (v_count > 0) THEN
      dbms_utility.compile_schema(p_esquema);
    END IF;
  END;

  /*--------------------------------------------------------------*/

  PROCEDURE montar_auditoria(p_esquema VARCHAR2) AS
    CURSOR C1 IS
      SELECT OBJECT_NAME
      FROM all_objects
      WHERE owner = p_esquema
      AND status = 'VALID'
      AND OBJECT_TYPE='TABLE'
      AND OBJECT_NAME <> 'ADM_USUARIO'
      AND (OBJECT_NAME like 'ADM_%');
  begin
     FOR tabela_rec in c1
     LOOP
         auditar_tabela(p_esquema,p_tabela => tabela_rec.OBJECT_NAME
         ,p_tabela_abreviada => TRIM(substr(tabela_rec.OBJECT_NAME,5,21)));
         DBMS_OUTPUT.put_line('/*--------------------------------------------------------------*/');
     END LOOP;
  end;

  /*--------------------------------------------------------------*/

  procedure montar_view_adm_log_valor(p_esquema VARCHAR2) AS
  V_QTD NUMBER;
  V_COLUNA VARCHAR2(100);
  CURSOR C1 IS
    SELECT OBJECT_NAME
    FROM all_objects
    WHERE owner = p_esquema
    AND status = 'VALID'
    AND OBJECT_TYPE='TABLE'
    AND OBJECT_NAME <> 'ADM_USUARIO'
    AND OBJECT_NAME not like 'LOG_%'
    AND OBJECT_NAME not like 'VW_%'
    AND OBJECT_NAME not like '%_AUD'
    AND (OBJECT_NAME like 'ADM_%');
  BEGIN
   DBMS_OUTPUT.put_line('CREATE OR REPLACE VIEW VW_ADM_LOG_VALOR_DADOS AS');
   FOR tabela_rec in c1
     LOOP
        SELECT COUNT(*)
        INTO V_QTD
        FROM all_constraints cons, all_cons_columns cols
        WHERE cols.table_name = tabela_rec.OBJECT_NAME
        AND cons.constraint_type = 'P'
        AND cons.constraint_name = cols.constraint_name
        AND cons.owner = p_esquema;

        IF V_QTD = 1 THEN
          SELECT '''' || cols.column_name || ''''
          INTO V_COLUNA
          FROM all_constraints cons, all_cons_columns cols
          WHERE cols.table_name = tabela_rec.OBJECT_NAME
          AND cons.constraint_type = 'P'
          AND cons.constraint_name = cols.constraint_name
          AND cons.owner = p_esquema;
        ELSE
          V_COLUNA := 'NULL';
        END IF;

         FOR v_colunas IN (SELECT colunas.column_name nome
                              FROM all_tables tabelas JOIN user_tab_columns colunas ON (tabelas.table_name = colunas.table_name)
                              WHERE tabelas.owner = p_esquema AND
                                    tabelas.table_name = tabela_rec.OBJECT_NAME AND
                                    colunas.data_type NOT IN ('BLOB', 'CLOB', 'LONG')) LOOP

          DBMS_OUTPUT.put_line('SELECT t.usuario, t.data, t.operacao, t.ip, ''' || initcap(replace(substr(tabela_rec.OBJECT_NAME,5,length(tabela_rec.OBJECT_NAME)),'_',' '))
          || ''' entidade, ''' || lower(tabela_rec.OBJECT_NAME) || ''' tabela, ' || V_COLUNA || ' chave, to_number(to_char(t.data,''YYYYMMDDHH24MISS'')) dataNumero, ''' ||
          replace(lower(substr(v_colunas.nome,5,length(v_colunas.nome))),'_', ' ') || ''' coluna, to_char(t.x' || lower(v_colunas.nome) ||  ') valorAnterior, to_char(t.' || lower(v_colunas.nome) || ') valor');
          DBMS_OUTPUT.put_line('    FROM log_' || lower(TRIM(substr(tabela_rec.OBJECT_NAME,5,21))) || ' t');
          DBMS_OUTPUT.put_line('UNION');

     END LOOP;
   END LOOP;

   DBMS_OUTPUT.put_line('
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
              ORDER BY T.DATA DESC) V;');

  END;



  PROCEDURE montar_view_adm_log(p_esquema VARCHAR2) AS
  V_QTD NUMBER;
  V_COLUNA VARCHAR2(100);
  CURSOR C1 IS
    SELECT OBJECT_NAME
    FROM all_objects
    WHERE owner = p_esquema
    AND status = 'VALID'
    AND OBJECT_TYPE='TABLE'
    AND OBJECT_NAME <> 'ADM_USUARIO'
    AND OBJECT_NAME not like 'LOG_%'
    AND OBJECT_NAME not like 'VW_%'
    AND OBJECT_NAME not like '%_AUD'
    AND (OBJECT_NAME like 'ADM_%');
  begin
     DBMS_OUTPUT.put_line('CREATE OR REPLACE VIEW VW_ADM_LOG_DADOS AS');
     FOR tabela_rec in c1
     LOOP
        SELECT COUNT(*)
        INTO V_QTD
        FROM all_constraints cons, all_cons_columns cols
        WHERE cols.table_name = tabela_rec.OBJECT_NAME
        AND cons.constraint_type = 'P'
        AND cons.constraint_name = cols.constraint_name
        AND cons.owner = p_esquema;

        IF V_QTD = 1 THEN
          SELECT '''' || cols.column_name || ''''
          INTO V_COLUNA
          FROM all_constraints cons, all_cons_columns cols
          WHERE cols.table_name = tabela_rec.OBJECT_NAME
          AND cons.constraint_type = 'P'
          AND cons.constraint_name = cols.constraint_name
          AND cons.owner = p_esquema;
        ELSE
          V_COLUNA := 'NULL';
        END IF;

         DBMS_OUTPUT.put_line('SELECT t.usuario, t.data, t.operacao, t.ip, ''' || initcap(replace(substr(tabela_rec.OBJECT_NAME,5,length(tabela_rec.OBJECT_NAME)),'_',' '))
         || ''' entidade, ''' || lower(tabela_rec.OBJECT_NAME) || ''' tabela, ' || V_COLUNA || ' chave, to_number(to_char(t.data,''YYYYMMDDHH24MISS'')) dataNumero');
         DBMS_OUTPUT.put_line('    FROM log_' || lower(TRIM(substr(tabela_rec.OBJECT_NAME,5,21))) || ' t');
         DBMS_OUTPUT.put_line('UNION');
     END LOOP;

     DBMS_OUTPUT.put_line('
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
                ORDER BY T.DATA DESC) V;');

  end;

  /*--------------------------------------------------------------*/

  PROCEDURE drop_auditoria(p_esquema VARCHAR2) AS
    CURSOR C1 IS
      SELECT OBJECT_NAME
      FROM all_objects
      WHERE owner = p_esquema
      AND status = 'VALID'
      AND OBJECT_TYPE='TABLE'
      AND OBJECT_NAME <> 'ADM_USUARIO'
      AND OBJECT_NAME not like 'LOG_%'
      AND OBJECT_NAME not like 'VW_%'
      AND OBJECT_NAME not like '%_AUD'
      AND (OBJECT_NAME like 'ADM_%');
  begin
     FOR tabela_rec in c1
     LOOP
         DBMS_OUTPUT.put_line('DROP TRIGGER trg_' || lower(TRIM(substr(tabela_rec.OBJECT_NAME,5,21))) || '_auid;');
         DBMS_OUTPUT.put_line('DROP SEQUENCE log_' || lower(TRIM(substr(tabela_rec.OBJECT_NAME,5,21))) || '_seq;');
         DBMS_OUTPUT.put_line('/*--------------------------------------------------------------*/');
     END LOOP;

	DBMS_OUTPUT.put_line('DROP TABLE LOG_PARAMETRO_CATEGORIA cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_PARAMETRO cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_PAGINA_PERFIL cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_MENU cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_LOG_COLUNA cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_FUNCIONARIO_PERFIL cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_FUNCIONARIO cascade constraints');	
	DBMS_OUTPUT.put_line('DROP TABLE LOG_FUNCIONALIDADE_PERFIL cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_FUNCIONALIDADE_PAGINA cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_FUNCIONALIDADE cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_PAGINA cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_CARGO_PERFIL cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_CARGO_FUNCIONARIO cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_CARGO cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_PERFIL cascade constraints');
	DBMS_OUTPUT.put_line('DROP TABLE LOG_SETOR cascade constraints');

  end;
END pkg_adm;


