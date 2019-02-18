package com.sebastian.modulos.comun;

import java.lang.annotation.Annotation;
import java.lang.module.ModuleDescriptor;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Sebastian Avila A.
 */
public class MuestraInfo {

    public void analizarModulo() {
        Module modulo = getClass().getModule();
        String anotaciones = Arrays.stream(modulo.getDeclaredAnnotations()).map(Annotation::annotationType)
                .map(Object::toString).collect(Collectors.joining(", "));
        System.out.println("anotaciones del modulo: " + anotaciones);
        ModuleDescriptor md = modulo.getDescriptor();
        if (md == null) {
            System.out.println("UNNAMED MODULE");
        } else {
            System.out.println("modificadores de modulos: " + md.modifiers());
            System.out.println("nombre del modulo: " + md.name());
            System.out.println("raw version: " + md.rawVersion());
            System.out.println("requires: " + md.requires());
            System.out.println("exports: " + md.exports());
            System.out.println("opens: " + md.opens());
            System.out.println("is open: " + md.isOpen());
            System.out.println("paquetes contenidos: " + md.packages());
            System.out.println("main: " + md.mainClass());
        }
    }
}
