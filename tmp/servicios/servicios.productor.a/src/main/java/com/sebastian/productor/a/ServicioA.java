package com.sebastian.productor.a;

import java.util.concurrent.atomic.AtomicInteger;
import com.sebastian.servicios.port.Servicio;

/**
 * utiliza servicio exponiendo la interface {@link Servicio}.
 *
 * @author Sebastián Ávila A.
 */
public class ServicioA implements Servicio {
  private final AtomicInteger ai = new AtomicInteger();

  @Override
  public String identificar() {
    return "Hola desde el Servicio A " + ai.incrementAndGet();
  }
}
