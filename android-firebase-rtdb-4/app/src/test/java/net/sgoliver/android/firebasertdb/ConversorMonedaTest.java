package net.sgoliver.android.firebasertdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConversorMonedaTest {

    @Test
    public void convierteCordobasADolares() {
        assertEquals(2.74, ConversorMoneda.aDolares(100.0), 0.0);
    }

    @Test
    public void convierteDolaresACordobas() {
        assertEquals(365.0, ConversorMoneda.aCordobas(10.0), 0.0);
    }
}
