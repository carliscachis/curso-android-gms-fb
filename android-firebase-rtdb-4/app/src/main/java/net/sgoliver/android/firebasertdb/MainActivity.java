package net.sgoliver.android.firebasertdb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAGLOG = "restaurante-app";

    private RestauranteRepository repository;

    private TextView txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new RestauranteRepository();
        txtEstado = (TextView) findViewById(R.id.txtEstado);

        configurarAccionesCliente();
        configurarAccionesCocina();
        configurarAccionesAdministracion();
    }

    private void configurarAccionesCliente() {
        Button btnSembrarMenu = (Button) findViewById(R.id.btnSembrarMenu);
        FloatingActionButton fabPedido = (FloatingActionButton) findViewById(R.id.fabCrearPedido);
        Button btnSeguimiento = (Button) findViewById(R.id.btnSeguimientoPedido);

        btnSembrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Plato> platos = Arrays.asList(
                        new Plato("TAC001", "Tacos al pastor", "Tacos", 150.0, "Piña, cebolla y cilantro"),
                        new Plato("ANT001", "Quesillo nica", "Antojitos", 75.0, "Tortilla, cebolla curtida y crema"),
                        new Plato("PRI001", "Enchiladas mexicanas", "Principales", 180.0, "Pollo, frijoles y ensalada"),
                        new Plato("BEB001", "Horchata artesanal", "Bebidas", 45.0, "Con canela"),
                        new Plato("POS001", "Flan napolitano", "Postres", 65.0, "Receta casera")
                );

                repository.crearPlatos(platos, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Menú digital cargado con categorías mexicanas.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });

        fabPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pedido pedido = Pedido.pedidoDemoCliente();
                repository.crearPedido(pedido, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Pedido enviado a cocina. Estado: Pendiente.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });

        btnSeguimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.simularSeguimientoPedido(Pedido.ID_PEDIDO_DEMO, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Seguimiento actualizado: Pendiente → En Cocina → En Camino → Entregado.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });
    }

    private void configurarAccionesCocina() {
        Button btnNotificarCocina = (Button) findViewById(R.id.btnNotificarCocina);
        Button btnPagoFactura = (Button) findViewById(R.id.btnPagoFactura);

        btnNotificarCocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.notificarPedidoACocina(Pedido.ID_PEDIDO_DEMO, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Notificación enviada a cocina para preparar pedido.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });

        btnPagoFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Factura factura = Factura.facturaDemoVertical();
                repository.registrarPagoYFactura(factura, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Pago contra entrega registrado y factura vertical generada.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });
    }

    private void configurarAccionesAdministracion() {
        Button btnConfigurarNegocio = (Button) findViewById(R.id.btnConfigurarNegocio);
        Button btnHistorialVentas = (Button) findViewById(R.id.btnHistorialVentas);

        btnConfigurarNegocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfiguracionNegocio cfg = ConfiguracionNegocio.demo();
                repository.guardarConfiguracion(cfg, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Datos fiscales y datos del negocio actualizados.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });

        btnHistorialVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.registrarCierreServicio(Pedido.ID_PEDIDO_DEMO, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            mostrarEstado("Servicio concluido. Venta guardada en historial y totales.");
                        } else {
                            mostrarError(error);
                        }
                    }
                });
            }
        });
    }

    private void mostrarEstado(String mensaje) {
        txtEstado.setText(mensaje);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        Log.i(TAGLOG, mensaje);
    }

    private void mostrarError(DatabaseError error) {
        String mensaje = "Error Firebase: " + error.getMessage();
        txtEstado.setText(mensaje);
        Log.e(TAGLOG, mensaje);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
