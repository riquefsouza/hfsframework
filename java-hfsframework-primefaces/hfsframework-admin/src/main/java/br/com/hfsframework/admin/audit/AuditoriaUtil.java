/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 * The Class AuditoriaUtil.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public class AuditoriaUtil<T, I extends Serializable> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

/*
CREATE TABLE ADM_AUDITORIA_REVISAO
(
AUD_ID       NUMBER NOT NULL,
AUD_DATA_HORA NUMBER,
AUD_IP        VARCHAR2(15) NOT NULL,
AUD_LOGIN     VARCHAR2(60) NOT NULL,
PRIMARY KEY (AUD_ID));

CREATE SEQUENCE ADM_AUDITORIA_REVISAO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache
cycle
order;

create table ORC_CATEGORIA_AUD
(
  cat_seq NUMBER not null,
  REV INTEGER NOT NULL,
  cat_dsc VARCHAR2(80 CHAR) null,
  REVTYPE NUMBER(3),
 primary key (CAT_SEQ, REV)
)
	
 */
	
	/** The em. */
@Inject
	private EntityManager em;
		
	/**
	 * Gets the entidade by revisao.
	 *
	 * @param classeEntidade
	 *            the classe entidade
	 * @param entidade
	 *            the entidade
	 * @param id
	 *            the id
	 * @param revisao
	 *            the revisao
	 * @return the entidade by revisao
	 */
	public T getEntidadeByRevisao(Class<T> classeEntidade, T entidade, I id, Number revisao) {
		AuditReader reader = AuditReaderFactory.get(em);
		T entidadeRevisao = reader.find(classeEntidade, id, revisao);
		//AuditQuery query = getAuditReader().createQuery().forEntitiesAtRevision(MyEntity.class, revisionNumber);
		return entidadeRevisao;
	}

	/**
	 * Find revisoes.
	 *
	 * @param classeEntidade
	 *            the classe entidade
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<AdmAuditoriaRevisao> findRevisoes(Class<T> classeEntidade, I id) {
	    List<AdmAuditoriaRevisao> lista = new ArrayList<AdmAuditoriaRevisao>();
	    AuditReader reader = AuditReaderFactory.get(em);
	    List<Number> revisoes = reader.getRevisions(classeEntidade, id);
	    for (Number n : revisoes) {
	    	AdmAuditoriaRevisao audRevisao = reader.findRevision(AdmAuditoriaRevisao.class, n);
	        lista.add(audRevisao);
	    }
	    return lista;
	}

	/*
	public Number getRevisions() {
		
List personsAtAddress = getAuditReader().createQuery()
.forEntitiesAtRevision(Person.class, 12)
.addOrder(AuditEntity.property("surname").desc())
.add(AuditEntity.relatedId("address").eq(addressId))
.setFirstResult(4)
.setMaxResults(2)
.getResultList();

Number revision = (Number) getAuditReader().createQuery()
.forRevisionsOfEntity(MyEntity.class, false, true)
// We are only interested in the first revision
.setProjection(AuditEntity.revisionNumber().min())
.add(AuditEntity.property("actualDate").minimize()
.add(AuditEntity.property("actualDate").ge(givenDate))
.add(AuditEntity.id().eq(givenEntityId)))
.getSingleResult();

Number revision = (Number) getAuditReader().createQuery()
.forRevisionsOfEntity(MyEntity.class, false, true)
.setProjection(AuditEntity.revisionNumber().min())
.add(AuditEntity.id().eq(entityId))
.add(AuditEntity.revisionNumber().gt(42))
.getSingleResult();
		
		//auditReader.getRevisionNumberForDate(date)
		//auditReader.getRevisionDate(revision)
	    AuditReader reader = AuditReaderFactory.get(em);
		
		Number revisao = (Number) reader.createQuery()
				.forRevisionsOfEntity(AdmParametroCategoria.class, false, true)
				.setProjection(AdmAuditoriaRevisao.revisionNumber().min()).add(AdmAuditoriaRevisao.id().eq(entityId))
				.add(AdmAuditoriaRevisao.revisionNumber().gt(42)).getSingleResult();
		return revisao;
	}
	
	public List<RevisionInfo> findRevisions(String id) {
	    List<RevisionInfo> list = new ArrayList<>();
	    AuditReader reader = AuditReaderFactory.get(em);
	    List<Number> revisions = reader.getRevisions(AdmParametroCategoria.class, id);
	    for (Number n : revisions) {
	    	AdmAuditoriaRevisao revEntity = reader.findRevision(AdmAuditoriaRevisao.class, n);
	        list.add(new RevisionInfo(n, revEntity));
	    }
	    return list;
	}
	
	public List<RevisionMetadata> getRevisionHistoryMetadata(Object id) {

	    AuditReader reader = AuditReaderFactory.get(em);
		  List<Number> revisionList = reader.getRevisions(AdmParametroCategoria.class, id);
		  List<RevisionMetadata> result = new ArrayList<>();
		  for (Number revision : revisionList) {
		    Long revisionLong = Long.valueOf(revision.longValue());
		    result.add(new LazyRevisionMetadata(em, revisionLong));
		  }
		  return result;
		}
		 
	
	public E findRevision(String id, Number revision) {
	    AuditReader reader = AuditReaderFactory.get(em);
	    List<Number> revisions = reader.getRevisions(AdmParametroCategoria.class, id);
	    if (!revisions.contains(revision)) {
	        return null;
	    }
	    E entity = reader.find(AdmParametroCategoria.class, id, revision);
	    return entity;
	}
	*/
}
