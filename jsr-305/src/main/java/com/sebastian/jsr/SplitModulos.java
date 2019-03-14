package com.sebastian.jsr;

import javax.annotation.Nonnull;
import javax.annotation.Saludador;
import javax.annotation.processing.Generated;

/**
 * prueba con split modulos.
 * 
 * javac -cp libs/* src/main/java/com/sebastian/jsr/SplitModulos.java -d classes
 * jar --create --file jsr-305.jar -C classes/ .
 * java -cp jsr-305.jar com.sebastian.jsr.SplitModulos
 * 
 * -- agregado paquete javax.annotation para que no compile:
 * javac --add-modules java.xml.ws.annotation -cp libs/* src/main/java/com/sebastian/jsr/SplitModulos.java src/main/java/javax/annotation/Saludador.java -d classes
 * -- si compila:
 * javac -cp libs/jsr305-3.0.2.jar src/main/java/com/sebastian/jsr/SplitModulos.java src/main/java/javax/annotation/Saludador.java -d classes
 * -- si es necesario:
 * javac --patch-module java.xml.ws.annotation=libs/libs/jsr305-3.0.2.jar  --add-modules java.xml.ws.annotation -cp libs/jsr305-3.0.2.jar src/main/java/com/sebastian/jsr/SplitModulos.java src/main/java/javax/annotation/Saludador.java -d classes
 * 
 * jar --create --file jsr-305.jar -C classes/ .
 * 
 * genera un error por split packages:
 * java --add-modules java.xml.ws.annotation -cp jsr-305.jar com.sebastian.jsr.SplitModulos
 * 
 * se soluciona con:
 * java --patch-module java.xml.ws.annotation=jsr-305.jar  --add-modules java.xml.ws.annotation -cp jsr-305.jar com.sebastian.jsr.SplitModulos
 * 
 * 
 * tambien funciona
 * java --patch-module java.xml.ws.annotation=libs/jsr305-3.0.2.jar:libs/jsr305-3.0.3.jar:jsr-305.jar  --add-modules java.xml.ws.annotation -cp libs/jsr305-3.0.2.jar:libs/jsr305-3.0.3.jar:libs/jsr-305.jar com.sebastian.jsr.SplitModulos

 * 
 * @author Sebastian Avila A.
 */
@Generated(value = "")
public class SplitModulos {
    public static void main(String[] args) {
        new SplitModulos().saludar(null);
    }
 
    private void saludar(@Nonnull String nombre) {
        System.out.println(new Saludador().hola(nombre));
    }
}
