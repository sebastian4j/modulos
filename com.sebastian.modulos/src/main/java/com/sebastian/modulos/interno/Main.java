package com.sebastian.modulos.interno;

import com.sebastian.modulos.segundo.exportado.Implementable;
import java.io.IOException;
import java.lang.Runtime.Version;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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

        FileSystem fs = FileSystems.getFileSystem(URI.create("jrt:/"));
        String str = "java/lang/String.class";
        Path p = fs.getPath("java.base", str);   
        System.out.println("path: " + p);

        mostrarUrl(ClassLoader.getSystemResource(str));
        mostrarUrl(ClassLoader.getSystemResource("jdk/internal/jrtfs/ExplodedImage.class"));
        Version version = Runtime.version();
        System.out.println("feature: " + version.feature());        
        System.out.println("interim: " + version.interim());
        System.out.println("update: " + version.update());
        System.out.println("patch: " + version.patch());
    }
    
    private static void mostrarUrl(final URL url) throws IOException {
        System.out.println(null == url ? "~ref url nula~": (url + ": " + url.getContent()));
    }

    private void accederClasePackage() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> cns = Class.forName("com.sebastian.modulos.tercero.Protegido").getDeclaredConstructor();
        cns.setAccessible(true);
        System.out.println(((Implementable) cns.newInstance()).saludar());
    }

}
