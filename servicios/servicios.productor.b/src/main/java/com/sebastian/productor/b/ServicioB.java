package com.sebastian.productor.b;

import com.sebastian.servicios.port.Servicio;

/**
 * utiliza servicio exponiendo la interface {@link Servicio}.
 *
 * @author Sebastián Ávila A.
 */
public final class ServicioB {
  private ServicioB() {}

  public static Servicio provider() {
    return new SB();
  }
}
