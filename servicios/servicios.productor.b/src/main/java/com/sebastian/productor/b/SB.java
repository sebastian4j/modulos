package com.sebastian.productor.b;

import com.sebastian.servicios.port.Servicio;

public class SB implements Servicio {

  @Override
  public String identificar() {
    return "Hola desde el Servicio B";
  }

}
