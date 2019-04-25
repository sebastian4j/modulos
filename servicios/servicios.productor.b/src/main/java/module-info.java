/**
 * @author Sebastian Avila A.
 */
module servicios.productor.b {
  requires servicios.consumidor;

  provides com.sebastian.servicios.port.Servicio with com.sebastian.productor.b.ServicioB;
}

