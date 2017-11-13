package br.com.logiquesistemas.easyspark.core;

import java.lang.reflect.Method;

/**
 * Created by Gustavo on 28/04/2016.
 */
public interface PathResolver {

    /**
     * Generate URL path.
     *
     * @param controller Controller class
     * @param method Controller method
     * @return Http path
     */
    String resolvePath(Class<?> controller, Method method);


}
