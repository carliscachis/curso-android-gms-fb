package net.sgoliver.android.firebasertdb;

public class Factura {

    public String idFactura;
    public String idPedido;
    public String fecha;
    public String formato;
    public String metodoPago;
    public String moneda;
    public double total;

    public Factura() {
    }

    public Factura(String idFactura, String idPedido, String fecha, String formato,
                   String metodoPago, String moneda, double total) {
        this.idFactura = idFactura;
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.formato = formato;
        this.metodoPago = metodoPago;
        this.moneda = moneda;
        this.total = total;
    }

    public static Factura facturaDemoVertical() {
        return new Factura("FAC-2026-0001", Pedido.ID_PEDIDO_DEMO, "2026-02-18",
                "Vertical", "Efectivo", "C$", 195.0);
    }
}
