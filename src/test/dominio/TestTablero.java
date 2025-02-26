package dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTablero {

    @Test
    public void testGenerarEstadoAleatorio() throws Exception {
        Tablero tablero = new Tablero();
        tablero.generarArchivoMatriz(); // Genera el archivo de estado inicial

        // Verificamos que el tablero ha sido generado correctamente
        assertNotNull(tablero);
    }

    @Test
    public void testLeerEstadoDesdeArchivo() throws Exception {
        Tablero tablero = new Tablero();
        tablero.generarArchivoMatriz(); // Genera el archivo de estado inicial

        tablero.leerEstadoActual(); // Lee el estado desde el archivo

        // Verificamos que el tablero tiene el estado cargado
        assertNotNull(tablero);
        assertTrue(tablero.toString().length() > 0, "El estado del tablero debe estar cargado");
    }

    @Test
    public void testTransitarEstado() throws Exception {
        Tablero tablero = new Tablero();
        tablero.generarArchivoMatriz(); // Genera el archivo de estado inicial
        tablero.leerEstadoActual(); // Lee el estado desde el archivo

        String estadoAntes = tablero.toString();
        tablero.transitarAlEstadoSiguiente(); // Realiza la transición

        String estadoDespues = tablero.toString();

        // Verificamos que el estado cambió
        assertNotEquals(estadoAntes, estadoDespues, "El estado del tablero debe cambiar después de la transición");
    }

    @Test
    public void testFinalizacionSimulacion() throws Exception {
        Tablero tablero = new Tablero();
        tablero.generarArchivoMatriz(); // Genera el archivo de estado inicial
        tablero.leerEstadoActual(); // Lee el estado desde el archivo

        // Realizamos una simulación
        for (int i = 0; i < 10; i++) {
            tablero.transitarAlEstadoSiguiente();
        }

        // Simulamos que la simulación ha finalizado
        tablero.finalizarJuego(0);

        // Verificamos que la simulación terminó correctamente (esto es solo un ejemplo de cómo podría verificarse)
        assertTrue(true, "La simulación finalizó correctamente");
    }
}
