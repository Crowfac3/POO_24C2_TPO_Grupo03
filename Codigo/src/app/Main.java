package app;

import controlador.ControladorJuego;
import modelo.juego.Juego;
import vista.PantallaInicio;

public class Main {
    public static void main(String[] args) {
        // Inicializar el Juego con un jugador genérico temporal
        Juego juego = new Juego();

        // Inicializar el ControladorJuego con el Juego
        ControladorJuego controlador = new ControladorJuego(juego);

        // Crear y mostrar la pantalla de inicio
        PantallaInicio pantallaInicio = new PantallaInicio(controlador);
        pantallaInicio.mostrar();
    }
}
