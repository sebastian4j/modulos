package com.sebastian.modulos.comun.abierto;

/**
 *
 * @author Sebastian Avila A.
 */
public class Anemona {

    private int id;
    private String nombre;
    public String anm;

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
        return "Anemona{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
