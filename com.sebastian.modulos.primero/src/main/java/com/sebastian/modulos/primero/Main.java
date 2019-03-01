package com.sebastian.modulos.primero;

/**
 * hola mundo con modulos =).
 *
 * mvn clean package
 *
 * java --module-path target/modulos-1-1.0-SNAPSHOT.jar --module modulos.primero/com.sebastian.modulos.primero.Main
 *
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hola modulos!!");
        if (args.length > 0) {
            new Main().abrirJavaLang();
        }
    }

    /**
     * para poder ejecutarlo:
     *
     * java --patch-module java.base=target/modulos-1-1.0-SNAPSHOT.jar --module
     * java.base/com.sebastian.modulos.primero.Main
     *
     *
     */
    private void abrirJavaLang() {
        try {
            Module base = Object.class.getModule();
            base.addOpens("java.lang", getClass().getModule());
            System.out.println("fue abierto: " + base.isOpen("java.lang", getClass().getModule()));
        } catch (IllegalCallerException e) {
            e.printStackTrace();
        }
    }
}
