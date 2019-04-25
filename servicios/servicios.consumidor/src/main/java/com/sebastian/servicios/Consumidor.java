package com.sebastian.servicios;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;
import com.sebastian.servicios.port.Servicio;

/**
 * utiliza servicio exponiendo la interface {@link Servicio}.
 *
 * compilar:
 *
 * mvn clean package
 *
 * lanzar:
 *
 * java --module-path mods --module servicios.consumidor/com.sebastian.servicios.Consumidor
 *
 * lanzar y ver la resolucion de modulos:
 *
 * java --show-module-resolution --module-path mods --module
 * servicios.consumidor/com.sebastian.servicios.Consumidor
 *
 *
 * @author Sebastián Ávila A.
 */
public class Consumidor {
  public static void main(String[] args) {

    ServiceLoader.load(Servicio.class).stream().map(Provider::get).collect(Collectors.toList())
        .forEach(s -> System.out.println(s.identificar()));
    final var sl = ServiceLoader.load(Servicio.class);
    sl.stream().forEach(r -> System.out.println(r.get()));
    System.out.println(":::::::::::::::");
    // obtienen las mismas referencias
    sl.forEach(System.out::println);
    System.out.println(":::::::::::::::");
    sl.forEach(System.out::println);
    System.out.println(":::::::::::::::");
    var it = sl.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    System.out.println(":::::::::::::::");
    // renueva las referencias
    sl.reload();
    it = sl.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}

