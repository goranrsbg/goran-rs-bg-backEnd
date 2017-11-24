
package com.rhcloud.meAtmysite.sqlvalidation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 *
 * @author Goran
 */
@Stateless
@LocalBean
public class ValidateEJB {
    
    //@Inject
    //private DBmanager db;
    
    public String validateKey(final String word) {
        return "keyIsValid";
    }    
}