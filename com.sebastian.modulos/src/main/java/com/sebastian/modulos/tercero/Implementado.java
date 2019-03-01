package com.sebastian.modulos.tercero;

import com.sebastian.modulos.segundo.exportado.Implementable;
import java.lang.annotation.Annotation;
import java.lang.module.ModuleDescriptor;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Sebastian Avila A.
 */
public class Implementado implements Implementable {

    @Override
    public String saludar() {
        buscarEnElStackDeCapas();
        System.out.println("===================================================layer");
        layerInfo();
        return "hola desde " + getClass().getCanonicalName();
    }

    public void buscarEnElStackDeCapas() {
        try {
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                    .walk(s -> {
                        s.forEach(d -> {
                            System.out.println("<< " + d.getClassName());
                        });
                        return s;
                    });

            Class<?> c
                    = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                            .walk(s -> s.filter(f -> f.getDeclaringClass() != this.getClass()).findFirst()
                            .map(StackWalker.StackFrame::getDeclaringClass).orElseThrow(IllegalStateException::new));
            System.out.println("clase que es distinta a la implementando: " + c);
            Module moduloOtroLayer = c.getModule();
            if (moduloOtroLayer.getLayer() != null) {
                final ModuleLayer ml = moduloOtroLayer.getLayer();
                mostrarInfoLayer(ml, moduloOtroLayer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarInfoLayer(ModuleLayer layer, Module modulo) {
        layer.modules().stream().forEach(this::analizarModulo);
        System.out.println("es boot layer?? " + (modulo.getLayer() == ModuleLayer.boot()));
        System.out.println("esta modulo.primero presente en esta layer (y padres): " + layer.findModule("modulos.primero").isPresent());
        System.out.println("esta modulo.comun presente en esta layer (y padres): " + layer.findModule("modulos.comun").isPresent());
    }

    public void analizarModulo(Module modulo) {
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

    private void layerInfo() {
        Module modulo = getClass().getModule();
        if (modulo.getLayer() != null) { // tiene layer si no viene del unnamed o el dynamic
            ModuleLayer layer = modulo.getLayer();
            mostrarInfoLayer(layer, modulo);
        } else {
            System.out.println("<<<<<<<<<<<<<<<<<<sin informacion de layer UNNAMED MODULE");
        }
    }
}
