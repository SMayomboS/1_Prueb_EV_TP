package dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


/**
 * Esta clase es responsable de leer el tablero de un
 * fichero en forma de ce ceros y unos, ir transitando de
 * estado e ir mostrando dichos estados.
 */




public class Tablero {
    private static final int DIMENSION = 30;
    private int [][] estadoActual = new int[DIMENSION][DIMENSION];
    private int [][] estadoSiguiente = new int[DIMENSION][DIMENSION];


    public void leerEstadoActual() throws IOException{
        var lineas = Files.readAllLines(Paths.get("src/main/java/dominio/matriz"));
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = lineas.get(i).charAt(j) - '0';
            }
        }
    }


    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
            }
        }
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
}


