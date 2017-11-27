package com.rhcloud.meAtmysite.sqlvalidation;

import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Goran
 */
@Stateless
@LocalBean
public class ValidateEJB {

    private String[] valid_keys;

    private static final int MAX_SIZE = 19;
    private static final String VALID = "keyIsValid";
    private static final String TEMP = "temp";
    private int length;

    private final StringBuilder sb;
    private final Random random;
    private final int MAX = 12;
    private final int RANGE = 75;
    private final int START = 48;

    public ValidateEJB() {
        this.sb = new StringBuilder(MAX);
        this.sb.append("............");
        this.random = new Random();
    }

    @PostConstruct
    void init() {
        valid_keys = new String[MAX_SIZE];
        clear();
        insertInto_validKeys_values(
                "ključ_",
                "xep624_",
                "temp_1234",
                "temp_1243",
                "temp_1324",
                "temp_1342",
                "temp_1423",
                "temp_1432",
                "temp_2134",
                "temp_2143",
                "temp_2314",
                "temp_2314",
                "temp_2413",
                "temp_2431");
    }

    public String validateKey(final String word) {
        for (int i = 0; i < length; i++) {
            if (valid_keys[i].equals(word)) {
                if(word.startsWith(TEMP)) {
                    delete_from_validKeys(i);
                }
                return VALID;
            }
        }
        return createRandomResponse();
    }

    public String createRandomResponse() {
        for (int i = 0; i < MAX; i++) {
            sb.setCharAt(i, (char) (random.nextInt(RANGE) + START));
        }
        return sb.toString();
    }
    
    private void delete_from_validKeys(int pos) {
        for (int i = pos; i < length;) {
            valid_keys[i] = valid_keys[++i];
        }
        length--;
    }

    public boolean insertInto_validKeys_values(String... values) {
        for (String v : values) {
            if (!isFull()) {
                valid_keys[length++] = v;
            } else {
                return false;
            }
        }
        return !isFull();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == MAX_SIZE;
    }

    private void clear() {
        length = 0;
    }

    public void select_star_from_validKeys() {
        for (int i = 0; i < length; i++) {
            System.out.println((i + 1) + " : " + valid_keys[i]);
        }
    }

}
