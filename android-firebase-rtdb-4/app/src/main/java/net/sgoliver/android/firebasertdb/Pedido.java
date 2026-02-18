package net.sgoliver.android.firebasertdb;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    public static final String ID_PEDIDO_DEMO = "PED-2026-0001";

    public String id;
    public String clienteNombre;
    public String direccionEntrega;
    public String estado;
    public String monedaPago;
    public String metodoPago;
    public double totalCordobas;
    public double totalDolares;
    public List<Plato> platos;

    public Pedido() {
    }

    public Pedido(String id, String clienteNombre, String direccionEntrega, String estado,
                  String monedaPago, String metodoPago, double totalCordobas, double totalDolares,
                  List<Plato> platos) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado;
        this.monedaPago = monedaPago;
        this.metodoPago = metodoPago;
        this.totalCordobas = totalCordobas;
        this.totalDolares = totalDolares;
        this.platos = platos;
    }

    public static Pedido pedidoDemoCliente() {
        List<Plato> detalle = new ArrayList<>();
        detalle.add(new Plato("TAC001", "Tacos al pastor", "Tacos", 150.0, "Piña y cilantro"));
        detalle.add(new Plato("BEB001", "Horchata artesanal", "Bebidas", 45.0, "Canela"));

        double totalCordobas = 195.0;
        double totalDolares = ConversorMoneda.aDolares(totalCordobas);

        return new Pedido(
                ID_PEDIDO_DEMO,
                "María López",
                "Km 7 Carretera Masaya, Managua",
                "Pendiente",
                "C$",
                "Efectivo",
                totalCordobas,
                totalDolares,
                detalle
        );
    }
}
