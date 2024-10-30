package app;

import controlador.ControladorJuego;
import vista.PantallaInicio;

public class Main {
    public static void main(String[] args) {
        ControladorJuego controlador = new ControladorJuego();
        PantallaInicio pantallaInicio = new PantallaInicio(controlador);
        pantallaInicio.mostrar(); // Muestra la pantalla de inicio
    }
}
