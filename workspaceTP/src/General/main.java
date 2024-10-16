package General;
import Class.Mapa;
import Class.TipoDeClase;
import Class.Jugador;
import Class.Guerrero;
import Class.Ubicacion;
import Class.Juego;
import Class.Pelea;

public class main {

	public static void main(String[] args) {
		
        Juego juego1 = new Juego();
        
        juego1.iniciarJuego("Lucca", "Guerrero");
        
        while (!juego1.verificarVictoria() && !juego1.verificarDerrota()) {
            jugador.viajar(Ubicacion ubicacion);
            
            // Verificamos  si hay una criatura en la nueva ubicación
            if (Ubicacion.hayCriatura(jugador.getUbicacion())) {
                Criatura criaturaEnemiga = mapa.getCriatura(jugador.getUbicacion());
                Pelea pelea = new Pelea(jugador, criaturaEnemiga);
                pelea.iniciar();
            }
            
            // Verificamos si el jugador encontro el tesoro
            if (jugador.getUbicacion().tieneTesoro()) {
                jugador.recogerTesoro(jugador.getUbicacion().getTesoro());
                System.out.println("Encontrasate el tesoro!");
                break;
            }
        }
        
        // Fin del juego
        if (juego.verificarDerrota()) {
            System.out.println("Fuiste derrotado, Intenta de nuevo");
        }

	}

}
