package vista;

import controlador.ControladorJuego;

public class PantallaFinJuego {
    public void mostrarFinDelJuego(boolean victoria) {
        if (victoria) {
            System.out.println("¡Felicidades! Has encontrado el tesoro y ganado el juego.");
        } else {
            System.out.println("El jugador ha sido derrotado. Fin del juego.");
        }
    }
}
