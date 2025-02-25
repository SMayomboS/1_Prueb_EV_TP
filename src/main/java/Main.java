import aplicacion.Principal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Introduce el número de puntos a generar: ");
        Scanner scanner = new Scanner(System.in);
        String[] principalArgs = new String[]{scanner.nextLine()};
        Principal.main(principalArgs);
    }
}