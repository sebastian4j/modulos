package com.sebastian.dependencia.opcional;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * no lo carga:
 *
 * java --module-path target/classes/ --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main
 *
 * java --module-path target/classes/:/home/sebastian/java/workspace/maven/modulos/com.sebastian.modulos.primero/target/com.sebastian.modulos.primero-1.2-SNAPSHOT.jar  --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main
 *
 * si lo carga:
 *
 * java --module-path target/classes/:/home/sebastian/java/workspace/maven/modulos/com.sebastian.modulos.primero/target/com.sebastian.modulos.primero-1.2-SNAPSHOT.jar --add-modules com.sebastian.modulos.primero --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main
 *
 * @author Sebastián Ávila A.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        new Main().verificar("com.sebastian.modulos.comun");
        new Main().verificar("com.sebastian.dependencia.opcional");
        new Main().verificar("com.sebastian.modulos.primero");
    }

    private void verificar(final String nombre) {
        LOGGER.log(Level.INFO, "verificando {0}", nombre);
        LOGGER.log(Level.INFO, "está presente? {0}", ModulosUtil.isModulePresent(nombre));

    }
}
