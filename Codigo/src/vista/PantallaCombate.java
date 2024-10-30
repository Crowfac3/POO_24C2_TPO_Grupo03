package vista;

import controlador.ControladorJuego;

public class PantallaCombate {
    private ControladorJuego controlador;

    public PantallaCombate(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void iniciarCombate() {
        controlador.iniciarCombate();
    }
}
