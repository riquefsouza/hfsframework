/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class BaseSequenceGenerator extends SequenceStyleGenerator {

	@Override
    public Serializable generate(SessionImplementor session, Object obj) {
		/*
        if (obj instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) obj;
            Serializable id = identifiable.getId();
            if (id != null) {
                return id;
            }
        }*/
        
		/*
		if (banco.equals("mysql")){
			return "NextVal('"+ obj.toString() +"')";
		}
		*/
		
        return super.generate(session, obj);
    }
}
