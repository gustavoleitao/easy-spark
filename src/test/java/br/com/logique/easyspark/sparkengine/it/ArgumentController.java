package br.com.logique.easyspark.sparkengine.it;

import br.com.logique.easyspark.sparkengine.annotations.Controller;
import spark.Request;
import spark.Response;

/**
 * Controller to test if controller method arguments works fine.
 * 
 * Created by Gustavo on 26/04/2016.
 */
@Controller
public class ArgumentController {

    public Boolean noArguments() {
        return Boolean.TRUE;
    }

    public Boolean request(Request request) {
        return request != null;
    }

    public Boolean response(Response response) {
        return response != null;
    }

    public Boolean requestResponse(Request request, Response response) {
        return request != null && response != null;
    }


}
