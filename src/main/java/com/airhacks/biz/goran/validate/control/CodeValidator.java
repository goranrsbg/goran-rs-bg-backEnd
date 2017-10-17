
package com.airhacks.biz.goran.validate.control;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author Goran
 */
@Stateful
@LocalBean
public class CodeValidator {
    
    private static final String AUTHORIZATION_CODE = "r2-d2aNdC-3pO";
    private static final String CODE_NONE = "none";
    private static final String MASK_TEMP = "temp";
    private static final int  MAX_CODE_SIZE = 12;
    private static final char SPACE = ' ';
    private static final char DEFAULT = '?';
    private static final char MASK = '_';
    
    private final StringBuilder sb;
    
    public CodeValidator() {
        sb = new StringBuilder(MAX_CODE_SIZE);
    }
    
    public String createValidKey(final String code,final String header) {
        if(header.equals(AUTHORIZATION_CODE) && code.length() < MAX_CODE_SIZE) {
            sb.append(code);
            char c;
            for(int i = 0; i < sb.length(); i++) {
                c = sb.charAt(i);
                if(c == SPACE || c == MASK)
                    sb.setCharAt(i, DEFAULT);
            }
            if(sb.indexOf(MASK_TEMP) == 0)
                sb.insert(4, MASK);
            sb.append(MASK);
        }
        else 
            sb.append(CODE_NONE);
        
        return sb.toString();
    }
    
}