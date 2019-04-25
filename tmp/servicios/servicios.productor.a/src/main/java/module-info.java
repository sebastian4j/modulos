/**
 * @author Sebastian Avila A.
 */
module servicios.productor.a {
  requires servicios.consumidor;

  provides com.sebastian.servicios.port.Servicio with com.sebastian.productor.a.ServicioA;

}


