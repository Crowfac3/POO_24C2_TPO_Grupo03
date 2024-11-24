package app;

import controlador.ControladorJuego;
import modelo.juego.Juego;
import vista.PantallaInicio;

public class Main {
    public static void main(String[] args) {
        // Inicializar el Juego con un jugador genérico temporal
        Juego juego = new Juego();
        
        //aca el main tiene que llamar a la fachada para setupear esto
        //Establecer ubicaciones neutrales (ciudades) y un par de ubicaciones con criaturas
        //Establecer el tesoro (en una ubicacion con criatura)
        
        juego.configurarJuegoInicial();

        // Inicializar el ControladorJuego con el Juego
        ControladorJuego controlador = new ControladorJuego(juego);

        // Crear y mostrar la pantalla de inicio
        PantallaInicio pantallaInicio = new PantallaInicio(controlador);
        pantallaInicio.mostrar();
    }
}
