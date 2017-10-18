
package com.airhacks.biz.goran.validate.sqlvalidation;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Goran
 */
@Stateful
@LocalBean
public class ValidateEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public String validateKey(final String word) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("VALIDATE");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query = query.setParameter(1, word);
        query.execute();
        return (String)query.getOutputParameterValue(2);
    }    
}