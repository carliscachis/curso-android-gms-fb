package net.sgoliver.android.firebasertdb;

public class Plato {

    public String id;
    public String nombre;
    public String categoria;
    public double precioCordobas;
    public String descripcion;

    public Plato() {
    }

    public Plato(String id, String nombre, String categoria, double precioCordobas, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioCordobas = precioCordobas;
        this.descripcion = descripcion;
    }
}
