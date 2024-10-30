package vista;

import controlador.ControladorJuego;
import modelo.mapa.Mapa;

public class PantallaMapa {
    private ControladorJuego controlador;

    public PantallaMapa(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {
        System.out.println("Mapa del Reino:");
        controlador.mostrarMapa();
    }

    public void viajar(int fila, int columna) {
        controlador.visitarUbicacion(fila, columna);
    }
}
