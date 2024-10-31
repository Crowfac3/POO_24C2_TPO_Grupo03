package vista;

import controlador.ControladorJuego;
import vista.ObjectView;

public class PantallaEstadoPersonaje {
    private ControladorJuego controlador;

    public PantallaEstadoPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {
        ObjectView estadoPersonaje = controlador.obtenerEstadoPersonaje();  // La vista pide los datos al controlador
        
        // Mostrar los datos recibidos en el ObjectView
        System.out.println("Estado del Personaje:");
        System.out.println("Nombre: " + estadoPersonaje.get("Nombre"));
        System.out.println("Puntos de Vida: " + estadoPersonaje.get("Puntos de Vida"));
        System.out.println("Ataque: " + estadoPersonaje.get("Ataque"));
        System.out.println("Defensa: " + estadoPersonaje.get("Defensa"));
    }
}
