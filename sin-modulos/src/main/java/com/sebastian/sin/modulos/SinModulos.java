package com.sebastian.sin.modulos;

import java.util.Arreglo;
/**
 * compilar con java8:
 * 
 * javac src/main/java/com/sebastian/sin/modulos/SinModulos.java 
 * src/main/java/java/util/Arreglo.java -d target/classes
 * 
 * ejecutar con java > 8:
 * java -cp target/classes/  com.sebastian.sin.modulos.SinModulos
 * 
Exception in thread "main" java.lang.NoClassDefFoundError: java/util/Arreglo
	at com.sebastian.sin.modulos.SinModulos.main(SinModulos.java:14)
Caused by: java.lang.ClassNotFoundException: java.util.Arreglo
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:583)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:521)

 * 
 * @author Sebastian Avila A.
 */
public class SinModulos {
    public static void main(String[] args) {
        System.out.println(new Arreglo());
    }
}
