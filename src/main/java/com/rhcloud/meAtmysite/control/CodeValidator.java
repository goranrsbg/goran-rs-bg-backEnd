
package com.rhcloud.meAtmysite.control;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Goran
 */
@Stateless
@LocalBean
public class CodeValidator {
    
    private static final String AUTHORIZATION_CODE = "r2-d2aNdC-3pO";
    private static final String CODE_NONE = "invalid";
    private static final String MASK_TEMP = "temp";
    private static final int  MAX_CODE_SIZE = 12;
    private static final char SPACE = ' ';
    private static final char DEFAULT = '?';
    private static final char MASK = '_';
    private static final String KLJUC = "klju?";
    private static final String KLJUCH = "kljuch"; // openshift oc console can not accept ?
    
    private final StringBuilder sb;
    
    public CodeValidator() {
        sb = new StringBuilder(MAX_CODE_SIZE);
    }
    
    public String createValidKey(final String code,final String auth) {
        sb.setLength(0);
        if(auth.equals(AUTHORIZATION_CODE) && code.length() < MAX_CODE_SIZE) {
            if(code.equals(KLJUC)) {
                sb.append(KLJUCH);
            } else {
                sb.append(code);
            }
            char c;
            for(int i = 0; i < sb.length(); i++) {
                c = sb.charAt(i);
                if(c == SPACE || c == MASK)
                    sb.setCharAt(i, DEFAULT);
            }
            if(sb.indexOf(MASK_TEMP) == 0) {
                sb.insert(4, MASK);  
            } else {
                sb.append(MASK);
            }
        } else {
            sb.append(CODE_NONE);
        }
        
        return sb.toString();
    }
    
}
