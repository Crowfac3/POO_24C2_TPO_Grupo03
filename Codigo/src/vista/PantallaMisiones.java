package vista;

import controlador.ControladorJuego;

public class PantallaMisiones {
    private ControladorJuego controlador;

    public PantallaMisiones(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrarMisiones() {
        controlador.mostrarMisiones();
    }

    public void reclamarRecompensa(String nombreMision) {
        controlador.reclamarRecompensa(nombreMision);
    }
}
