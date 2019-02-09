package com.sebastian.modulos.primero;

/**
 * hola mundo con modulos =).
 * 
 * mvn clean package
 * 
 * java --module-path target/modulos-1-1.0-SNAPSHOT.jar --module modulos.primero/com.sebastian.modulos.primero.Main
 * 
 * @author Sebastian Avila A.
 */
public class Main {
  public static void main(String[] args) {
    System.out.println("hola modulos!!");
  }
}
