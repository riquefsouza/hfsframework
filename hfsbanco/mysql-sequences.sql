DROP PROCEDURE IF EXISTS CreateSequence;
 
  DELIMITER //
 
  CREATE PROCEDURE CreateSequence (name VARCHAR(64), start bigint, inc bigint)
  BEGIN
     -- Create a table to store sequences
     CREATE TABLE IF NOT EXISTS _sequences
     (
         name VARCHAR(64) NOT NULL UNIQUE,
         next bigint NOT NULL,
         inc bigint NOT NULL
     );
 
     -- Add the new sequence
     INSERT INTO _sequences VALUES (name, start, inc);  
  END
  //
  DELIMITER ;

DROP PROCEDURE IF EXISTS DropSequence;
 
  DELIMITER //
 
  CREATE PROCEDURE DropSequence (vname VARCHAR(64))
  BEGIN
     -- Drop the sequence
     DELETE FROM _sequences WHERE name = vname;  
  END
  //
  DELIMITER ;

DROP FUNCTION IF EXISTS NextVal;
 
  DELIMITER //
 
  CREATE FUNCTION NextVal (vname VARCHAR(64))
    RETURNS bigint
  BEGIN
     -- Retrieve and update in single statement
     UPDATE _sequences
       SET next = (@next := next) + 1
       WHERE name = vname;
 
     RETURN @next;
  END
  //
  DELIMITER ;
  
  
select max(car_seq) from adm_cargo;
select max(fun_seq) from adm_funcionalidade;
select max(mnu_seq) from adm_menu;
select max(pag_seq) from adm_pagina;
select max(pmc_seq) from adm_parametro_categoria;
select max(par_seq) from adm_parametro;
select max(prf_seq) from adm_perfil;

call CreateSequence('ADM_CARGO_SEQ', 999998, 1);
call CreateSequence('ADM_FUNCIONALIDADE_SEQ', 62, 1);
call CreateSequence('ADM_MENU_SEQ', 45, 1);
call CreateSequence('ADM_PAGINA_SEQ', 61, 1);
call CreateSequence('ADM_PARAMETRO_CATEGORIA_SEQ', 5, 1);
call CreateSequence('ADM_PARAMETRO_SEQ', 15, 1);
call CreateSequence('ADM_PERFIL_SEQ', 5, 1);
