package aplicacion;
import mates.Matematicas;
import dominio.Tablero;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;


/*public class Principal{
   public static void main(String[] args){
       System.out.println("El número PI es " + Matematicas.
               generarNumeroPi(Integer.parseInt(args[0])));
   }
}
*/


public class Principal{
    public static void main(String[] args){
        try
        {
            Tablero tablero = new Tablero();
            System.out.println("SIMULACIOÓN CON TABLERO LEÍDO");
            tablero.leerEstadoActual();
            System.out.println("------------------------------");
            System.out.println(tablero);
            for (int i = 0; i <= 5; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("////////////////////////");
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }
            System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANE MONTECARLO");
            tablero.generarEstadoActualPorMontecarlo();
            System.out.println("------------------------------");
            System.out.println(tablero);
            for (int i = 0; i <= 15; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("//////////////////////////////");
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }
        } catch (InterruptedException e){
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
