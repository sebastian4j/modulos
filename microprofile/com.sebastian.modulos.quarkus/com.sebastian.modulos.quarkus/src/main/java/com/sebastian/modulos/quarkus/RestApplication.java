package com.sebastian.modulos.quarkus;

import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.sebastian.modulos.thorn.recursos.Hola;

@ApplicationPath("/")
public class RestApplication extends Application {
  @PostConstruct
  public void init() {
    System.out.println("iniciado");
  }

  @Override
  public Set<Class<?>> getClasses() {
    return Set.of(Hola.class);
  }
}
