package com.sebastian.productor.c;

import com.sebastian.servicios.port.Servicio;

/**
 * utiliza servicio exponiendo la interface {@link Servicio}.
 *
 * carga de servicios anterior a los modulos
 *
 * java --class-path mods/servicios.productor.c.jar --show-module-resolution --module-path mods
 * --module servicios.consumidor/com.sebastian.servicios.Consumidor
 *
 * es un modulo explicito, tiene que ser agregado al classpath para que sea cargado (unnamed
 * module), no puede quedar en el module path para que sea leido como un servicio antiguo
 *
 * @author Sebastián Ávila A.
 */
public final class ServicioC implements Servicio {

  @Override
  public String identificar() {
    return "hola desde el servicio C";
  }

}
