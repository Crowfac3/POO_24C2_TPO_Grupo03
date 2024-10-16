package General;
import Class.Mapa;
import Class.TipoDeClase;
import Class.Troll;
import Class.Jugador;
import Class.Criatura;
import Class.Dragon;
import Class.Espectro;
import Class.Guerrero;
import Class.Ubicacion;
import Class.Juego;

public class main {

	public static void main(String[] args) {
		
        Juego juego1 = new Juego(String "Lucca", TipoDeClase Guerrero);
        
        juego1.realizarAccion(String accion);
        
        while (!juego1.verificarVictoria() && !juego1.verificarDerrota()) {
            jugador.moverse(mapa);
            
            // Verificar si hay una criatura en la nueva ubicación
            if (mapa.hayCriatura(jugador.getUbicacion())) {
                Criatura criaturaEnemiga = mapa.getCriatura(jugador.getUbicacion());
                Pelea pelea = new Pelea(jugador, criaturaEnemiga);
                pelea.iniciar();
            }
            
            // Verificar si el jugador ha encontrado el tesoro
            if (jugador.getUbicacion().tieneTesoro()) {
                jugador.recogerTesoro(jugador.getUbicacion().getTesoro());
                System.out.println("¡Has encontrado el " + jugador.getTesoro().getNombre() + " y ganado el juego!");
                break;
            }
        }
        
        // Fin del juego
        if (jugador.haPerdido()) {
            System.out.println("Has sido derrotado. ¡Intenta de nuevo!");
        }

	}

}
