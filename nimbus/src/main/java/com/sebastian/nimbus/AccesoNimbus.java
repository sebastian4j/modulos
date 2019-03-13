package com.sebastian.nimbus;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;



/**
 * usando java9:
 * 
 * intentar compilar (el paquete es interno):
 * javac -d target/classes src/main/java/com/sebastian/nimbus/AccesoNimbus.java 
 * 
 * compilar con warnings:
 * javac --add-exports=java.desktop/com.sun.java.swing.plaf.nimbus=ALL-UNNAMED -d target/classes src/main/java/com/sebastian/nimbus/AccesoNimbus.java
 * 
 * ejecutar con warnings:
 * java -cp target/classes/ com.sebastian.nimbus.AccesoNimbus
 * 
 * no permite ejecutar:
 * java --illegal-access=deny -cp target/classes com.sebastian.nimbus.AccesoNimbus
 * 
 * ejecutar:
 * java  --illegal-access=deny --add-exports=java.desktop/com.sun.java.swing.plaf.nimbus=ALL-UNNAMED -cp target/classes com.sebastian.nimbus.AccesoNimbus
 * 
 * 
 * @author Sebastian Avila A.
 */
public class AccesoNimbus {
    public static void main(String[] args) throws Exception {
        NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
        System.out.println(nimbus);
        Object ref = Class.forName("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel").getConstructor().newInstance();
        System.out.println(ref);
    }
}
