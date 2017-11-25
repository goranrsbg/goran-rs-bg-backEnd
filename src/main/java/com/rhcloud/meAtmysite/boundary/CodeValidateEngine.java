
package com.rhcloud.meAtmysite.boundary;

import com.rhcloud.meAtmysite.sqlvalidation.ValidateEJB;
import com.rhcloud.meAtmysite.control.CodeValidator;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Goran
 */
@Path("/code")
public class CodeValidateEngine {

    @EJB
    private CodeValidator cv;
    
    @EJB
    private ValidateEJB validate;

    /**
     *
     * @param key
     * @param headers
     * @return
     */
    @POST
    @Produces("aplication/json")
    public JsonObject data(String key, @Context HttpHeaders headers) {
        System.out.println(headers);
        System.out.println(key);
        key = cv.createValidKey(key, headers.getHeaderString("authorization"));
        System.out.println("----- " + key + " -----");
        return createResponse("Skynet", validate.validateKey(key));      
    }
    
    private JsonObject createResponse(String name, String value) {
        return Json.createObjectBuilder().
                add("name", name).
                add("askAndLearn", value).
                build();
    }
}