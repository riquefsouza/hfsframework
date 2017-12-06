delete from ADM_PAGINA_PERFIL;
delete from ADM_MENU;
delete from ADM_FUNCIONALIDADE_PERFIL;
delete from ADM_FUNCIONALIDADE_PAGINA;
delete from adm_funcionalidade;
delete from adm_pagina;


insert into ADM_PERFIL (prf_seq, prf_descricao, prf_geral, prf_administrador)
values (1, 'Geral', 'S', 'N');
insert into ADM_PERFIL (prf_seq, prf_descricao, prf_geral, prf_administrador)
values (2, 'Administrador', 'N', 'S');

insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (1, 'admin/admParametroCategoria/listarAdmParametroCategoria.xhtml', 'br.com.hfsframework.admin.view.admParametroCategoria.AdmParametroCategoriaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (2, 'admin/admParametroCategoria/editarAdmParametroCategoria.xhtml', 'br.com.hfsframework.admin.view.admParametroCategoria.AdmParametroCategoriaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (3, 'admin/admParametro/listarAdmParametro.xhtml', 'br.com.hfsframework.admin.view.admParametro.AdmParametroMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (4, 'admin/admParametro/editarAdmParametro.xhtml', 'br.com.hfsframework.admin.view.admParametro.AdmParametroMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (5, 'admin/admPerfil/listarAdmPerfil.xhtml', 'br.com.hfsframework.admin.view.admPerfil.AdmPerfilMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (6, 'admin/admPerfil/editarAdmPerfil.xhtml', 'br.com.hfsframework.admin.view.admPerfil.AdmPerfilMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (7, 'admin/admFuncionalidade/listarAdmFuncionalidade.xhtml', 'br.com.hfsframework.admin.view.admFuncionalidade.AdmFuncionalidadeMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (8, 'admin/admFuncionalidade/editarAdmFuncionalidade.xhtml', 'br.com.hfsframework.admin.view.admFuncionalidade.AdmFuncionalidadeMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (9, 'admin/admPagina/listarAdmPagina.xhtml', 'br.com.hfsframework.admin.view.admPagina.AdmPaginaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (10, 'admin/admPagina/editarAdmPagina.xhtml', 'br.com.hfsframework.admin.view.admPagina.AdmPaginaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (11, 'admin/admMenu/listarAdmMenu.xhtml', 'br.com.hfsframework.admin.view.admMenu.AdmMenuMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (12, 'admin/admMenu/editarAdmMenu.xhtml', 'br.com.hfsframework.admin.view.admMenu.AdmMenuMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (13, 'admin/admCargo/listarAdmCargo.xhtml', 'br.com.hfsframework.admin.view.admCargo.AdmCargoMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (14, 'admin/admCargo/editarAdmCargo.xhtml', 'br.com.hfsframework.admin.view.admCargo.AdmCargoMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (15, 'admin/admFuncionario/listarAdmFuncionario.xhtml', 'br.com.hfsframework.admin.view.admFuncionario.AdmFuncionarioMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (16, 'admin/admFuncionario/editarAdmFuncionario.xhtml', 'br.com.hfsframework.admin.view.admFuncionario.AdmFuncionarioMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (17, 'admin/admSetor/listarAdmSetor.xhtml', 'br.com.hfsframework.admin.view.admSetor.AdmSetorMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (18, 'admin/admSetor/editarAdmSetor.xhtml', 'br.com.hfsframework.admin.view.admSetor.AdmSetorMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (19, 'admin/admUsuario/listarAdmUsuario.xhtml', 'br.com.hfsframework.admin.view.admUsuario.AdmUsuarioMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (20, 'admin/vwAdmLog/listarVwAdmLog.xhtml', 'br.com.hfsframework.admin.view.vwAdmLog.VwAdmLogMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (21, 'admin/admLogColuna/listarAdmLogColuna.xhtml', 'br.com.hfsframework.admin.view.admLogColuna.AdmLogColunaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (22, 'admin/admLogColuna/editarAdmLogColuna.xhtml', 'br.com.hfsframework.admin.view.admLogColuna.AdmLogColunaMB');
insert into ADM_PAGINA (pag_seq, pag_url, pag_mb)
values (23, 'admin/visualizarArqLogServidor.xhtml', 'br.com.hfsframework.admin.view.VisualizarArqLogServidorMB');

insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (1, 1, 'Categoria dos Parâmetros de Configuração (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (2, 2, 'Editar Categoria dos Parâmetros de Configuração (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (3, 3, 'Parâmetros de Configuração (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (4, 4, 'Editar Parâmetros de Configuração (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (5, 5, 'Administrar Perfil (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (6, 6, 'Editar Administrar Perfil (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (7, 7, 'Administrar Funcionalidade (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (8, 8, 'Editar Administrar Funcionalidade (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (9, 9, 'Administrar Página (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (10, 10, 'Editar Administrar Página (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (11, 11, 'Administrar Menu (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (12, 12, 'Editar Administrar Menu (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (13, 13, 'Administrar os Cargos (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (14, 14, 'Editar Administrar os Cargos (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (15, 15, 'Administrar os Funcionários (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (16, 16, 'Editar Administrar os Funcionários (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (17, 17, 'Administrar os Setores (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (18, 18, 'Editar Administrar os Setores (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (19, 19, 'Visualizar os Usuários (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (20, 20, 'Visualizar os Logs de Auditoria (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (21, 21, 'Administrar os Campos do Log de Auditoria (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (22, 22, 'Editar Administrar os Campos do Log de Auditoria (Administrativo)');
insert into ADM_FUNCIONALIDADE (fun_seq, fun_pag_seq, fun_descricao)
values (23, 23, 'Visualizar Arquivo de Log do Servidor (Administrativo)');


insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (1, 1);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (2, 2);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (3, 3);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (4, 4);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (5, 5);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (6, 6);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (7, 7);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (8, 8);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (9, 9);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (10, 10);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (11, 11);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (12, 12);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (13, 13);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (14, 14);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (15, 15);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (16, 16);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (17, 17);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (18, 18);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (19, 19);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (20, 20);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (21, 21);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (22, 22);
insert into ADM_FUNCIONALIDADE_PAGINA (fpg_fun_seq, fpg_pag_seq)
values (23, 23);



insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (1, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (2, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (3, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (4, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (5, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (6, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (7, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (8, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (9, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (10, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (11, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (12, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (13, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (14, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (15, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (16, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (17, 1);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (17, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (18, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (19, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (20, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (21, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (22, 2);
insert into ADM_FUNCIONALIDADE_PERFIL (fpl_fun_seq, fpl_prf_seq)
values (23, 2);



insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (1, 'Administrativo', null, null, 1);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (2, 'Categoria dos Parâmetros de Configuração (Administrativo)', 1, 1, 2);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (3, 'Parâmetros de Configuração (Administrativo)', 1, 3, 3);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (4, 'Administrar Perfil (Administrativo)', 1, 5, 4);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (5, 'Administrar Funcionalidade (Administrativo)', 1, 7, 5);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (6, 'Administrar Página (Administrativo)', 1, 9, 6);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (7, 'Administrar Menu (Administrativo)', 1, 11, 7);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (8, 'Administrar os Cargos (Administrativo)', 1, 13, 8);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (9, 'Administrar os Funcionários (Administrativo)', 1, 15, 9);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (10, 'Administrar os Setores (Administrativo)', 1, 17, 10);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (11, 'Visualizar os Usuários (Administrativo)', 1, 19, 11);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (12, 'Visualizar os Logs de Auditoria (Administrativo)', 1, 20, 12);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (13, 'Administrar os Campos do Log de Auditoria (Administrativo)', 1, 21, 13);
insert into ADM_MENU (mnu_seq, mnu_descricao, mnu_pai_seq, mnu_fun_seq, mnu_ordem)
values (14, 'Visualizar Arquivo de Log do Servidor (Administrativo)', 1, 22, 14);



insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (1, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (2, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (3, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (4, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (5, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (6, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (7, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (8, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (9, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (10, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (11, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (12, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (13, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (14, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (15, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (16, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (17, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (18, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (19, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (20, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (21, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (22, 2);
insert into ADM_PAGINA_PERFIL (pgl_pag_seq, pgl_prf_seq)
values (23, 2);


insert into ADM_USUARIO (usu_matricula, usu_ip, usu_login, usu_nome, usu_data, usu_cpf, usu_email, usu_ldap_dn, usu_senha)
values (88888, '127.0.0.1', 'henrique.souza', 'Henrique Figueiredo de Souza', str_to_date('17-07-2017 13:49:12', '%d-%m-%Y %H:%i:%s'), 57358497007, 'riquefsouza@gmail.com', null,'7ce0359f12857f2a90c7de465f40a95f01cb5da9');

