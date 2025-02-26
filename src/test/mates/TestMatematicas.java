package mates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatematicas {

    @Test
    public void testGenerarNumeroPi() {
        long puntosTotales = 1000000;
        double piAproximado = Matematicas.generarNumeroPi(puntosTotales);

        assertTrue(piAproximado > 3.0 && piAproximado < 3.5, "La aproximación de Pi debe estar cerca de 3.14");
    }
}
