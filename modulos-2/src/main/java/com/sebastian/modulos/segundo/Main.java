package com.sebastian.modulos.segundo;

import com.sebastian.modulos.comun.Persona;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * java --module-path target/libs:target/modulos-2.jar --module modulos.segundo/com.sebastian.modulos.segundo.Main
 *
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(final String[] args) {
        separacion("acceso público:");
        accesoPublico();
        separacion("error reflexion:");
        errorReflection();
        separacion("sin error reflexion:");
        sinErrorReflexion();
        separacion("leer modulo abierto");
        leerDesdeModuloAbierto();
    }

    private static void accesoPublico() {
        final var p = new Persona();
        p.setId(1);
        p.setNombre("primero");
        System.out.println(p);

    }

    private static void errorReflection() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.privado.Auto");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                ccc.setAccessible(true);
                var inst = ccc.newInstance();
                System.out.println(inst);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void separacion(String txt) {
        System.out.println("_________________________________________________________ " + txt);
    }

    private static void sinErrorReflexion() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.Persona");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                ccc.setAccessible(true);
                var inst = ccc.newInstance();
                mostrarDatos(c);
                c.getMethod("setId", int.class).invoke(inst, 2);
                c.getMethod("setNombre", String.class).invoke(inst, "segundo");
                System.out.println(inst);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void mostrarDatos(Class<?> c) {
        System.out.println("===========todos los metodos");
        mostrarMetodos(c.getDeclaredMethods());
        System.out.println("===========todos metodos publicos");
        mostrarMetodos(c.getMethods());
        System.out.println("===========todos los fields");
        mostrarCampos(c.getDeclaredFields());
        System.out.println("===========fields publicos");
        mostrarCampos(c.getFields());
    }

    private static void leerDesdeModuloAbierto() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.abierto.Anemona");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                ccc.setAccessible(true);
                var inst = ccc.newInstance();
                mostrarDatos(c);
                c.getMethod("setId", int.class).invoke(inst, 2);
                c.getMethod("setNombre", String.class).invoke(inst, "segundo");
                System.out.println(inst);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void mostrarMetodos(Method[] metodos) {
        try {
            Arrays.asList(metodos).stream().forEach(f -> {
                f.setAccessible(true);
                System.out.println(f.getName());
            });
        } catch (Exception e) {
            System.out.println("!!!ERROR!!! " + e.getMessage());
        }
    }

    private static void mostrarCampos(Field[] campos) {
        try {
            Arrays.asList(campos).stream().forEach(f -> {
                f.setAccessible(true);
                System.out.println(f.getName());
            });
        } catch (Exception e) {
            System.out.println("!!!ERROR!!! " + e.getMessage());
        }
    }
}
