package net.sgoliver.android.firebasertdb;

public final class ConversorMoneda {

    private static final double TASA_CORDOBAS_POR_DOLAR = 36.5;

    private ConversorMoneda() {
    }

    public static double aDolares(double cordobas) {
        return redondear(cordobas / TASA_CORDOBAS_POR_DOLAR);
    }

    public static double aCordobas(double dolares) {
        return redondear(dolares * TASA_CORDOBAS_POR_DOLAR);
    }

    private static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
