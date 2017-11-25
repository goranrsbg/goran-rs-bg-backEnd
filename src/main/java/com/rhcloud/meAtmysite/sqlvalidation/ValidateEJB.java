package com.rhcloud.meAtmysite.sqlvalidation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Goran
 */
@Stateless
@LocalBean
public class ValidateEJB {

    @PersistenceContext
    private EntityManager em;
    
    private StoredProcedureQuery query;
    
    public String validateKey(final String word) {
        query = em.createStoredProcedureQuery("VALIDATE");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query = query.setParameter(1, word);
        query.execute();
        return (String) query.getOutputParameterValue(2);
    }

}
