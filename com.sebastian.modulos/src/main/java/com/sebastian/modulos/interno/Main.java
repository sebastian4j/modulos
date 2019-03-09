package com.sebastian.modulos.interno;

import com.sebastian.modulos.segundo.exportado.Implementable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Sebastian Avila A.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hola tercero");
        Main main = new Main();
        main.accederClasePackage();
        System.out.println(Main.class.getClassLoader().getClass().getName());
        System.out.println(
                ClassLoader.getSystemClassLoader() == Main.class.getClassLoader()
        );
        try {
            URLClassLoader loader
                    = (URLClassLoader) Main.class.getClassLoader();
            Arrays.stream(loader.getURLs())
                    .map(URL::toString)
                    .collect(Collectors.joining(", "));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        
    }

    private void accederClasePackage() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> cns = Class.forName("com.sebastian.modulos.tercero.Protegido").getDeclaredConstructor();
        cns.setAccessible(true);
        System.out.println(((Implementable) cns.newInstance()).saludar());
    }

}
