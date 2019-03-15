package com.sebastian.nimbus;

/**
 * @author Sebastian Avila A.
 */
public class AccesoNimbusSoloReflexion {
    public static void main(String[] args) throws Exception {
        Object ref = Class.forName("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel").getConstructor().newInstance();
        System.out.println("por reflexion: " + ref);
    }
}
