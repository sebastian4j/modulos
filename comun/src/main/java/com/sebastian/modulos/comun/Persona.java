package com.sebastian.modulos.comun;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

/**
 *
 * @author Sebastian Avila A.
 */
public class Persona {

    private int id;
    private String nombre;
    public String noEncapsulado = "valor-no-encapsulado";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    public Lookup lookupPrivado() throws IllegalAccessException {
        return MethodHandles.privateLookupIn(this.getClass(), MethodHandles.lookup());
    }
}
