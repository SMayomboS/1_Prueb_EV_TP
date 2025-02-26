import dominio.Tablero;
import pr2.Graph;
import mates.Matematicas;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenú:");
            System.out.println("1. Iniciar Aproximación de Pi");
            System.out.println("2. Jugar: Juego de la Vida");
            System.out.println("3. Test Graph");
            System.out.println("4. Exit");
            System.out.print("Elige un programa: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Introduce el número de puntos a generar: ");
                    scanner.nextLine(); // Consume newline
                    long puntos = scanner.nextLong();
                    System.out.println("Aproximación de Pi: " + Matematicas.generarNumeroPi(puntos));
                    break;
                case 2:
                    System.out.println("Juego de la Vida");
                    juegoVida();
                    break;
                case 3:
                    testGraph();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void juegoVida() {
        try {
            Tablero tablero = new Tablero();

            // Crear el archivo matriz con el estado inicial aleatorio
            tablero.generarArchivoMatriz();

            System.out.println("SIMULACIÓN CON TABLERO LEÍDO");
            tablero.leerEstadoActual();
            System.out.println("------------------------------");
            System.out.println(tablero);

            // Definir el número máximo de ciclos
            int maxCiclos = 10;
            for (int i = 0; i <= maxCiclos; i++){
                if (tablero.isFinDelJuego()) {
                    break; // Termina si el juego ha finalizado
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println("////////////////////////");
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);

                // Opcional: puedes añadir una condición para terminar el juego si no hay cambios
                tablero.finalizarJuego(maxCiclos - i); // Establece los ciclos restantes
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private static void testGraph() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        System.out.println("Graph created and edges added.");
        System.out.println("Graph: " + g);
        System.out.println("Path from 1 to 4: " + g.onePath(1, 4));
    }


}