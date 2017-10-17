
package com.airhacks.biz.goran.validate.boundary;

import com.airhacks.biz.goran.validate.beans.ValidateEJB;
import com.airhacks.biz.goran.validate.control.CodeValidator;
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
@Path("code")
public class CodeValidateEngine {

    @EJB
    private CodeValidator cv;
    
    @EJB
    private ValidateEJB validate;

    public CodeValidateEngine() {}
    
    @POST
    @Produces("aplication/json")
    public JsonObject data(String key, @Context HttpHeaders headers) {
        key = cv.createValidKey(key, headers.getHeaderString("authorization"));
        System.out.println(key);
        return createResponse("Skynet", this.validate.validateKey(key));      
    }
    
    private JsonObject createResponse(String name, String value) {
        return Json.createObjectBuilder().
                add("name", name).
                add("askAndLearn", value).
                build();
    }
}