package dominio;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Esta clase es responsable de leer el tablero de un
 * fichero en forma de ceros y unos, ir transitando de
 * estado e ir mostrando dichos estados.
 */
public class Tablero {
    private static final int DIMENSION = 30;
    private int [][] estadoActual = new int[DIMENSION][DIMENSION];
    private int [][] estadoSiguiente = new int[DIMENSION][DIMENSION];
    private boolean finDelJuego = false;

    public void matiz() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = 0;
            }
        }
    }

    /**
     * Genera un archivo 'matriz' con el estado inicial aleatorio.
     */
    public void generarArchivoMatriz() throws IOException {
        Random random = new Random();
        try (FileWriter writer = new FileWriter("matriz")) {
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
                    writer.write(estadoActual[i][j] == 1 ? '1' : '0');
                }
                writer.write("\n");
            }
        }
        System.out.println("Estado inicial generado y almacenado en el archivo 'matriz'.");
    }

    public void leerEstadoActual() throws IOException{
        var lineas = Files.readAllLines(Paths.get("matriz"));
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = lineas.get(i).charAt(j) - '0';
            }
        }
        System.out.println("Estado inicial cargado desde el archivo 'matriz'.");
    }

    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
            }
        }
        System.out.println("Estado generado aleatoriamente mediante Montecarlo.");
    }

    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j <DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                if (estadoActual[i][j] == 1) {
                    estadoSiguiente[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                } else {
                    estadoSiguiente[i][j] = (vecinosVivos == 3) ? 1 : 0;
                }
            }
        }

        // Mostrar el cambio de estado
        System.out.println("Estado actualizado:");
        int [][] temp = estadoActual;
        estadoActual = estadoSiguiente;
        estadoSiguiente = temp;
    }

    private int contarVecinosVivos(int x, int y) {
        int vivos = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                int nx = x + i, ny = y + j;
                if (nx >= 0 && nx < DIMENSION && ny >= 0 && ny < DIMENSION) {
                    vivos += estadoActual[nx][ny];
                }
            }
        }
        return vivos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j] == 1 ? 'X' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Indica cuando el juego finaliza, en este caso cuando se alcanza el
     * número máximo de ciclos.
     */
    public void finalizarJuego(int maxCiclos) {
        if (maxCiclos <= 0) {
            finDelJuego = true;
            System.out.println("El juego ha finalizado.");
        }
    }

    public boolean isFinDelJuego() {
        return finDelJuego;
    }
}
