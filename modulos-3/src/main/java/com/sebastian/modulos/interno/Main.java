package com.sebastian.modulos.interno;

import com.sebastian.modulos.segundo.exportado.Implementable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hola tercero");
        Main main = new Main();
        main.accederClasePackage();
    }

    private void accederClasePackage() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> cns = Class.forName("com.sebastian.modulos.tercero.Protegido").getDeclaredConstructor();
        cns.setAccessible(true);
        System.out.println(((Implementable) cns.newInstance()).saludar());
    }

}
