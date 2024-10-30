package vista;

import controlador.ControladorJuego;
import modelo.personaje.Personaje;


public class PantallaEstadoPersonaje {
    private ControladorJuego controlador;

    public PantallaEstadoPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {
        Personaje personaje = controlador.getPersonaje();
        System.out.println("Estado del Personaje:");
        System.out.println("Nombre: " + personaje.getNombre());
        System.out.println("Puntos de Vida: " + personaje.getPuntosVida());
        System.out.println("Ataque: " + personaje.getNivelAtaque());
        System.out.println("Defensa: " + personaje.getNivelDefensa());
        System.out.println("Experiencia: " + personaje.getExperiencia());
    }
}
