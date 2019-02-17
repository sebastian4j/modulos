package com.sebastian.modulos.tercero;

import com.sebastian.modulos.segundo.exportado.Implementable;

/**
 *
 * @author Sebastian Avila A.
 */
public class Implementado implements Implementable {

    @Override
    public String saludar() {
        return "hola desde " + getClass().getCanonicalName();
    }

}
