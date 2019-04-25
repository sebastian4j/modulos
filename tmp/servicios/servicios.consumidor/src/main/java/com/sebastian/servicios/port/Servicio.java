package com.sebastian.servicios.port;

/**
 * 
 * @author Sebastián Ávila A.
 */
public interface Servicio {

  /**
   * cada implementacion entrega su identificacion.
   *
   * @return identificacion del servicio
   */
  public String identificar();
}
