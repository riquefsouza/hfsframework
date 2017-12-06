SELECT 'select '''+TABLE_NAME+''',count(*) from '+TABLE_NAME+' UNION'
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE' 
ORDER BY TABLE_TYPE;

create sequence ADM_PERFIL_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 3
increment by 1
no cache
cycle;

create sequence ADM_PAGINA_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 24
increment by 1
no cache
cycle;

create sequence ADM_FUNCIONALIDADE_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 24
increment by 1
no cache
cycle;

create sequence ADM_MENU_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 15
increment by 1
no cache
cycle;

create sequence ADM_PARAMETRO_CATEGORIA_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 5
increment by 1
no cache
cycle;

create sequence ADM_PARAMETRO_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 15
increment by 1
no cache
cycle;

create sequence ADM_CARGO_SEQ
as bigint
minvalue 1
maxvalue 999999999999999999
start with 999998
increment by 1
no cache
cycle;
