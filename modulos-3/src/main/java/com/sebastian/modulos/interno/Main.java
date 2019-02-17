package com.sebastian.modulos.interno;

/**
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hola tercero");
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
            var base = Object.class.getModule();
            base.addOpens("java.lang", getClass().getModule());
            System.out.println("fue abierto: " + base.isOpen("java.lang", getClass().getModule()));
        } catch (IllegalCallerException e) {
            e.printStackTrace();
        }
    }
}
