package net.sgoliver.android.firebasertdb;

public class ConfiguracionNegocio {

    public String nombreRestaurante;
    public String direccion;
    public String propietario;
    public String ruc;
    public String telefono;

    public ConfiguracionNegocio() {
    }

    public ConfiguracionNegocio(String nombreRestaurante, String direccion, String propietario,
                                String ruc, String telefono) {
        this.nombreRestaurante = nombreRestaurante;
        this.direccion = direccion;
        this.propietario = propietario;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    public static ConfiguracionNegocio demo() {
        return new ConfiguracionNegocio(
                "Un Pedacito de mi México",
                "Reparto San Juan, Managua, Nicaragua",
                "José Ramírez",
                "J0310001234567",
                "+505 8888-9999"
        );
    }
}
