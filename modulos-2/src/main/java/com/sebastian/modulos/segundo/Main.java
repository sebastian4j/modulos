package com.sebastian.modulos.segundo;

import com.sebastian.modulos.comun.Persona;

/**
 *
 * java --module-path target/libs:target/modulos-2.jar --module modulos.segundo/com.sebastian.modulos.segundo.Main
 *
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(final String[] args) {
        accesoPublico();
        errorReflection();
    }

    private static void accesoPublico() {
        final var p = new Persona();
        p.setId(1);
        p.setNombre("nombre");
        System.out.println(p);

    }

    private static void errorReflection() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.privado.Auto");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                ccc.setAccessible(true);
                var inst = ccc.newInstance();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
