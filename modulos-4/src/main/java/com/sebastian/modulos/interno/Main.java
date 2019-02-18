package com.sebastian.modulos.interno;

import java.lang.reflect.Constructor;

/**
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hola no modulos");
        Main main = new Main();
        main.instanciarProtegido();
    }

    private void instanciarProtegido() {
        try {
            Constructor<?> cns = Class.forName("com.sebastian.modulos.comun.Protegido").getDeclaredConstructor();
            cns.setAccessible(true);
            System.out.println(cns.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
