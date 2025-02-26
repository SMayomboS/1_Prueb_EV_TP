package mates;

import java.util.Random;

public class Matematicas {
    /**
     * Genera una aproximación al número pi mediante el metodo de Montecarlo.
     *El parámetro puntosTotales muestra el  número de puntos a generar
     */
    public static double generarNumeroPi(long puntosTotales) {
        if (puntosTotales <= 0) throw new IllegalArgumentException("El número de puntos debe ser mayor que cero");

        Random random = new Random();
        int aciertos = 0;

        for (int i = 0; i < puntosTotales; i++) {
            double x = random.nextDouble() * 2 - 1, y = random.nextDouble() * 2 - 1;
            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        return 4.0 * aciertos / puntosTotales;
    }
}