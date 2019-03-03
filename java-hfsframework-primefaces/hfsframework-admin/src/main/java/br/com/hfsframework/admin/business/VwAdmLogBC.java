/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import br.com.hfsframework.admin.data.VwAdmLogRepository;
import br.com.hfsframework.admin.model.AdmLogColuna;
import br.com.hfsframework.admin.model.VwAdmLog;
import br.com.hfsframework.base.BaseBusinessController;
import br.com.hfsframework.util.DataUtil;

/**
 * The Class VwAdmLogBC.
 */
public class VwAdmLogBC extends BaseBusinessController<VwAdmLog, Long, VwAdmLogRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm log coluna BC. */
	@Inject
	private AdmLogColunaBC admLogColunaBC;

	/** The em. */
	@Inject
	private EntityManager em;

	/**
	 * Checks if is string empty.
	 *
	 * @param s
	 *            the s
	 * @return true, if is string empty
	 */
	private boolean isStringEmpty(String s) {
		return (s == null) || (s.trim().isEmpty());
	}

	/**
	 * Checks if is numero.
	 *
	 * @param valor
	 *            the valor
	 * @return true, if is numero
	 */
	private boolean isNumero(String valor) {
		return valor.matches("^-?([1-9]\\d*(\\.\\d+)?)|([0](\\.\\d+)?)$");
	}

	/**
	 * Find by filtros.
	 *
	 * @param filtros
	 *            the filtros
	 * @return the list
	 */
	public List<VwAdmLog> findByFiltros(VwAdmLog filtros) {
		Long nDataInicio = DataUtil.toLong(filtros.getDataInicio(), DataUtil.DATA_NUMERO_HORA_PADRAO);
		Long nDataFim = DataUtil.toLong(filtros.getDataFim(), DataUtil.DATA_NUMERO_HORA_PADRAO);
		
		if (isStringEmpty(filtros.getEntidade())){
			filtros.setEntidade(null);
		}
		if (isStringEmpty(filtros.getUsuario())){
			filtros.setUsuario(null);
		}
		if (isStringEmpty(filtros.getIp())){
			filtros.setIp(null);
		}
		if (isStringEmpty(filtros.getChave())){
			filtros.setChave(null);
		}
		if (isStringEmpty(filtros.getOperacao())){
			filtros.setOperacao(null);
		}
			
		
		List<VwAdmLog> resultado = repositorio.findByFiltros(filtros.getEntidade(), filtros.getUsuario(),
				filtros.getIp(), filtros.getOperacao(), filtros.getChave(), nDataInicio,
				nDataFim);
		
		/*
		List<VwAdmLog> retorno = new ArrayList<VwAdmLog>();
		if ((filtros.getEntidade() != null) && (!isStringEmpty(filtros.getEntidade()))
				&& (!isStringEmpty(filtros.getChaveValor()))) {
			for (VwAdmLog log : resultado) {
				if ((!isStringEmpty(log.getChave())) && (!isStringEmpty(filtros.getChaveValor()))
						&& (isContemChave(log.getTabela(), ((Long) log.getId()).longValue(), log.getChave(),
								filtros.getChaveValor()))) {
					retorno.add(log);
				}
			}
		} else {
			retorno.addAll(resultado);
		}
		*/
		
		return resultado;

	}

	/**
	 * Checks if is contem chave.
	 *
	 * @param tabela
	 *            the tabela
	 * @param id
	 *            the id
	 * @param chave
	 *            the chave
	 * @param valor
	 *            the valor
	 * @return true, if is contem chave
	 */
	public boolean isContemChave(String tabela, long id, String chave, String valor) {
		Object obj = repositorio.isContemChave(tabela, id, chave, valor);
		Integer retorno = (Integer) obj;
		return (retorno.intValue() > 0);
	}

	/**
	 * Detalhar.
	 *
	 * @param tabela
	 *            the tabela
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<Map.Entry<String, String>> detalhar(String tabela, String id) {
		final SQLException[] erro = new SQLException[1];
		Session session = em.unwrap(Session.class);

		List<Map.Entry<String, String>> resultado = new ArrayList<Map.Entry<String, String>>();
		Map<String, AdmLogColuna> colunas = admLogColunaBC.obterColunas();
		// List<Object> listaObj = repositorio.detalhar(tabela, id);
		String sql = "select * from " + tabela + " where id = ?";

		Boolean retorno = session.doReturningWork(new ReturningWork<Boolean>() {
			@Override
			public Boolean execute(Connection connection) {
				try {
					ResultSet rs = null;
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, id);

					/*
					 * PreparedStatement ps =
					 * connection.prepareStatement(this.val$query);
					 * 
					 * ps.setString(1, this.val$id);
					 * 
					 * rs = ps.executeQuery(); if (rs != null) {
					 * this.val$resultado.add(new
					 * AbstractMap.SimpleEntry("Tabela", this.val$tabela));
					 */

					rs = ps.executeQuery();
					if (rs != null) {
						detalharTabela(ps, rs, resultado, tabela, colunas);
					}

					return true;

				} catch (SQLException e) {
					erro[0] = e;
					return false;
				}
			}
		});

		if (!retorno) {
			// throw new TransacaoException(log, erro[0].getMessage());
		}

		return resultado;
	}

	/**
	 * Detalhar tabela.
	 *
	 * @param ps
	 *            the ps
	 * @param rs
	 *            the rs
	 * @param resultado
	 *            the resultado
	 * @param tabela
	 *            the tabela
	 * @param colunas
	 *            the colunas
	 * @throws SQLException
	 *             the SQL exception
	 */
	private void detalharTabela(PreparedStatement ps, ResultSet rs, List<Map.Entry<String, String>> resultado,
			String tabela, Map<String, AdmLogColuna> colunas) throws SQLException {

		resultado.add(new AbstractMap.SimpleEntry<String, String>("Tabela", tabela));

		ResultSetMetaData columns = rs.getMetaData();
		int i = 0;

		rs.next();

		int qtdColunas = columns.getColumnCount();
		while (i < qtdColunas) {
			i++;

			String coluna = columns.getColumnName(i);
			String valor = rs.getString(coluna);
			String complemento = "";

			AdmLogColuna logColuna = (AdmLogColuna) colunas.get(coluna);
			if (logColuna != null) {
				if (columns.getColumnTypeName(i).equals("DATE")) {
					valor = DataUtil.Formatar(rs.getTimestamp(coluna), DataUtil.DATA_HORA_PADRAO);
				} else if ((columns.getColumnTypeName(i).equals("NUMBER")) && (columns.getScale(i) > 0)) {
					DecimalFormat f = new DecimalFormat("###,###,###,##0.00");
					if (!isStringEmpty(valor)) {
						valor = f.format(rs.getBigDecimal(coluna));
					} else {
						valor = "";
					}
				} else if ((!coluna.equals("DATA")) && (!coluna.equals("ID")) && (!coluna.equals("IP"))
						&& ((!coluna.equals("USUARIO")) || ((coluna.equals("USUARIO")) && (isNumero(valor))))
						&& (!isStringEmpty(logColuna.getComando())) && (!isStringEmpty(valor))) {
					if (coluna.equals("USUARIO")) {
						complemento = executarComando(ps.getConnection(), logColuna.getComando(), valor, "NUMBER");
					} else {
						complemento = executarComando(ps.getConnection(), logColuna.getComando(), valor,
								columns.getColumnTypeName(i));
					}
					if (!isStringEmpty(complemento)) {
						valor = valor + " - " + complemento;
					}
				}
			}
			if (coluna.equals("OPERACAO")) {
				if (valor.equals("I")) {
					valor = "Inclusão";
				} else if (valor.equals("U")) {
					valor = "Alteração";
				} else {
					valor = "Exclusão";
				}
			}
			resultado.add(new AbstractMap.SimpleEntry<String, String>(logColuna != null ? logColuna.getNomeExibicao() : coluna, valor));
		}
	}

	/**
	 * Executar comando.
	 *
	 * @param connection
	 *            the connection
	 * @param comando
	 *            the comando
	 * @param valor
	 *            the valor
	 * @param tipoParametro
	 *            the tipo parametro
	 * @return the string
	 */
	private String executarComando(Connection connection, String comando, String valor, String tipoParametro) {
		String resultado = "";
		try {
			ResultSet rs = null;

			PreparedStatement ps = connection.prepareStatement(comando);
			if (tipoParametro.equals("NUMBER")) {
				ps.setLong(1, Long.parseLong(valor));
			} else if (tipoParametro.equals("CHAR")) {
				ps.setString(1, valor.trim());
			} else {
				ps.setString(1, valor);
			}
			rs = ps.executeQuery();
			rs.next();
			resultado = rs.getString(1);

			rs.close();
			ps.close();
		} catch (Exception localException) {
		}
		return resultado;
	}

	/**
	 * Primary keys.
	 *
	 * @param tabela
	 *            the tabela
	 * @return the list
	 */
	private List<String> primaryKeys(String tabela) {
		List<String> lista = new ArrayList<String>();
		List<Object> listaObj = repositorio.primaryKeys(tabela.toLowerCase());
		for (Object item : listaObj) {
			lista.add(item.toString());
		}
		return lista;
	}

	/**
	 * Id logs.
	 *
	 * @param tabela
	 *            the tabela
	 * @param idLog
	 *            the id log
	 * @return the string[]
	 */
	public String[] idLogs(String tabela, String idLog) {
		Object[] resultado;
		String sqlIdAnt = "SELECT to_char(max(id))  from " + tabela + " where id < to_number(:idLog) ";

		String sqlIdPos = "SELECT to_char(min(id))  from " + tabela + " where id > to_number(:idLog) ";
		for (String id : primaryKeys(tabela)) {
			sqlIdAnt = sqlIdAnt + " and " + id + " = (select " + id + " from " + tabela
					+ " where id = to_number(:idLog))";
			sqlIdPos = sqlIdPos + " and " + id + " = (select " + id + " from " + tabela
					+ " where id = to_number(:idLog))";
		}
		String sql = "SELECT ( " + sqlIdAnt + " ) as idAnt, ( " + sqlIdPos + " ) as idPos  from dual ";

		Query query = em.createNativeQuery(sql);
		// query.setParameter("sm_itenssai_ano", sm_itenssai_ano);

		try {
			resultado = (Object[]) query.getSingleResult();
			/*
			 * Query query = getNewEntityManager().createNativeQuery(sql);
			 * query.setParameter("idLog", idLog);
			 * 
			 * Object[] resultado = (Object[]) query.getSingleResult();
			 * 
			 */
			return new String[] { (String) resultado[0], (String) resultado[1] };
		} catch (PersistenceException e) {
			return new String[] {};
		}
	}

/*	
	public List<LogVO> detalhesLog(String tabela, String idLog) {
		List<Map.Entry<String, String>> logOriginal = detalhar(tabela, idLog);
		List<Map.Entry<String, String>> logAnterior = null;

		String[] idsLogReferentes = idLogs(tabela, idLog);
		if ((idsLogReferentes != null) && (idsLogReferentes[0] != null)) {
			logAnterior = detalhar(tabela, idsLogReferentes[0]);
		}
		List<LogVO> logsVO = new ArrayList<LogVO>();
		for (Map.Entry<String, String> infoLog : logOriginal) {
			LogVO logVO = new LogVO((String) infoLog.getKey(), (String) infoLog.getValue(), null);
			if ((logAnterior != null) && (!logAnterior.isEmpty())) {
				for (Map.Entry<String, String> infoLogAnterior : logAnterior) {
					if (((String) infoLog.getKey()).equals(infoLogAnterior.getKey())) {
						logVO.setValorAnterior((String) infoLogAnterior.getValue());

						break;
					}
				}
			}
			logsVO.add(logVO);
		}
		return logsVO;
	}
*/
}
