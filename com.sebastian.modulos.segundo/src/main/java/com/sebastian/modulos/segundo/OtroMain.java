package com.sebastian.modulos.segundo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author Sebastian Avila A.
 */
public class OtroMain {

    public static void main(String[] args) throws IOException {
        if (args != null) {
            Arrays.asList(args).forEach(System.out::println);
        }
        System.out.println("otro main utilizado");
        System.out.println("recurso en la raiz desde la clase: "
                + mostrarContenido(OtroMain.class.getResourceAsStream("/segundo.properties")));
        System.out.println("recurso relativo desde la clase: "
                + mostrarContenido(OtroMain.class.getResourceAsStream("segundo.properties")));
        System.out.println("recurso en la raiz desde otro modulo: "
                + mostrarContenido(OtroMain.class.getClassLoader().getResourceAsStream("comun.properties")));
        System.out.println("recurso en otro modulo encapsulado: "
                + mostrarContenido(OtroMain.class.getClassLoader()
                        .getResourceAsStream("/com/sebastian/modulos/comun/abierto/comun.properties")));
        System.out.println("recurso en otro modulo encapsulado: "
                + mostrarContenido(OtroMain.class
                        .getResourceAsStream("/com/sebastian/modulos/comun/abierto/comun.properties")));
    }

    private static String mostrarContenido(final InputStream is) throws IOException {
        return is == null ? "~nulo~" : new String(is.readAllBytes());
    }
}
