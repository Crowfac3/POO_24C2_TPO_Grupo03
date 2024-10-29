package Juego;

import Mapa.Mapa;
import Mapa.Ubicacion;
import Criatura.Dragon;
import Criatura.Troll;

public class Main {
    public static void main(String[] args) {
        // Crear el mapa del juego
        Mapa mapa = new Mapa();

        // Crear un jugador con su personaje
        Jugador jugador = new Jugador("Arthur", "guerrero");

        // Crear el juego con el jugador
        Juego juego = new Juego(jugador);

        // Establecer algunas ubicaciones con criaturas y neutrales
        Dragon dragon = new Dragon("Dragón del Norte", 150, 25, 10);
        Troll troll = new Troll("Troll de las Montañas", 120, 15, 20);
        mapa.establecerUbicacionConCriatura(2, 3, dragon);
        mapa.establecerUbicacionConCriatura(4, 7, troll);
        mapa.establecerUbicacionNeutral(1, 1); // Ubicación neutral
        mapa.establecerUbicacionNeutral(5, 5); // Ubicación neutral

        // Mostrar el mapa
        mapa.mostrarMapa();

        // El jugador visita la ubicación neutral (1, 1)
        System.out.println("El jugador visita la ubicación (1, 1)");
        Ubicacion ubicacion = mapa.obtenerUbicacion(1, 1);
        juego.visitarUbicacion(ubicacion);

        // El jugador visita la ubicación neutral (5, 5)
        System.out.println("El jugador visita la ubicación (5, 5)");
        ubicacion = mapa.obtenerUbicacion(5, 5);
        juego.visitarUbicacion(ubicacion);

        // Intentar reclamar la recompensa nuevamente en (1, 1)
        System.out.println("El jugador vuelve a la ubicación (1, 1)");
        ubicacion = mapa.obtenerUbicacion(1, 1);
        juego.visitarUbicacion(ubicacion);
    }
}
