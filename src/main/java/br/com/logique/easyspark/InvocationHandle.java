package br.com.logique.easyspark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Manage the br.com.logique.controller method execution.
 * <p>
 * Created by gustavo on 26/04/2016.
 */
public class InvocationHandle {

    private static Logger logger = LoggerFactory.getLogger(InvocationHandle.class);

    private Class<?> controller;
    private Method method;

    public InvocationHandle(Class<?> controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    /**
     * Invoke br.com.logique.controller method by reflection.
     *
     * @param request  Http Spark request
     * @param response Http spark response
     * @return br.com.logique.controller method return
     */
    public Object execute(Request request, Response response) {
        try {
            return invoke(request, response);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error("Error during br.com.logique.controller method invocation.", e);
        }
        return null;
    }

    private Object invoke(Request request, Response response) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] parameters = getParamters(method, request, response);
        Object result;
        if (parameters != null) {
            result = method.invoke(getInstance(controller), parameters);
        } else {
            result = method.invoke(getInstance(controller));
        }
        return result;
    }

    private Object[] getParamters(Method method, Request request, Response response) {
        ObjectInstantiator iogiObjectInstantiator = new IogiObjectInstantiator();
        Parameter[] parameters = method.getParameters();
        Object[] params = null;
        if (parameters.length > 0) {
            params = new Object[parameters.length];
            int index = 0;
            for (Parameter parameter : parameters) {
                if (parameter.getType().isAssignableFrom(request.getClass())) {
                    params[index++] = request;
                }else if  (parameter.getType().isAssignableFrom(response.getClass())) {
                    params[index++] = response;
                }else{
                    params[index++] = iogiObjectInstantiator.resolveParameter(method, parameter, request);
                }
            }
            return params;
        }
        return params;
    }

    private Object tryToResolveParameter(Method method, Parameter parameter, Request request) {

        //parameter.getName()

        return null;
    }

    private Object getInstance(Class<?> controller) throws IllegalAccessException, InstantiationException {
        return controller.newInstance();
    }

}
