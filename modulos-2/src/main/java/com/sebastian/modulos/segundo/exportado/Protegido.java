package com.sebastian.modulos.segundo.exportado;

/**
 *
 * @author Sebastian Avila A.
 */
class Protegido implements Implementable {

    private int a;
    private String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String saludar() {
        return "esto no puede ser posible!!";
    }

}
