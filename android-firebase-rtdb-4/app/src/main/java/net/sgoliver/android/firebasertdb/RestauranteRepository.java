package net.sgoliver.android.firebasertdb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestauranteRepository {

    private final DatabaseReference dbRef;

    public RestauranteRepository() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("un-pedacito-de-mi-mexico");
    }

    public void crearPlatos(List<Plato> platos, DatabaseReference.CompletionListener listener) {
        Map<String, Object> data = new HashMap<>();
        for (Plato plato : platos) {
            data.put(plato.id, plato);
        }
        dbRef.child("menu").setValue(data, listener);
    }

    public void crearPedido(Pedido pedido, DatabaseReference.CompletionListener listener) {
        dbRef.child("pedidos").child(pedido.id).setValue(pedido, listener);
    }

    public void notificarPedidoACocina(String idPedido, DatabaseReference.CompletionListener listener) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("pedidos/" + idPedido + "/notificacionCocina", "Nueva orden recibida");
        updates.put("pedidos/" + idPedido + "/estado", "En Cocina");
        dbRef.updateChildren(updates, listener);
    }

    public void simularSeguimientoPedido(String idPedido, DatabaseReference.CompletionListener listener) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("pedidos/" + idPedido + "/estado", "Entregado");
        updates.put("pedidos/" + idPedido + "/timeline/1", "Pendiente");
        updates.put("pedidos/" + idPedido + "/timeline/2", "En Cocina");
        updates.put("pedidos/" + idPedido + "/timeline/3", "En Camino");
        updates.put("pedidos/" + idPedido + "/timeline/4", "Entregado");
        dbRef.updateChildren(updates, listener);
    }

    public void registrarPagoYFactura(Factura factura, DatabaseReference.CompletionListener listener) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("pedidos/" + factura.idPedido + "/metodoPago", factura.metodoPago);
        updates.put("pedidos/" + factura.idPedido + "/estadoPago", "Pagado contra entrega");
        updates.put("facturas/" + factura.idFactura, factura);
        dbRef.updateChildren(updates, listener);
    }

    public void registrarCierreServicio(String idPedido, DatabaseReference.CompletionListener listener) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("pedidos/" + idPedido + "/servicio", "Concluido");
        updates.put("historial-ventas/" + idPedido + "/totalCordobas", 195.0);
        updates.put("historial-ventas/" + idPedido + "/totalDolares", ConversorMoneda.aDolares(195.0));
        updates.put("totales/generalCordobas", 195.0);
        updates.put("totales/generalDolares", ConversorMoneda.aDolares(195.0));
        dbRef.updateChildren(updates, listener);
    }

    public void guardarConfiguracion(ConfiguracionNegocio config, DatabaseReference.CompletionListener listener) {
        dbRef.child("configuracion").setValue(config, listener);
    }
}
