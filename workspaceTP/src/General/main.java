package General;

public class main {

	public static void main(String[] args) {
		// Inicializar el mapa y ubicaciones
        Mapa mapa = new Mapa();
        Ubicacion ubicacion1 = new Ubicacion("Bosque Oscuro");
        Ubicacion ubicacion2 = new Ubicacion("Montaña del Dragón");
        Ubicacion ubicacion3 = new Ubicacion("Pantano Maldito");
        
        // Añadir ubicaciones al mapa
        mapa.agregarUbicacion(ubicacion1);
        mapa.agregarUbicacion(ubicacion2);
        mapa.agregarUbicacion(ubicacion3);
        
        // Configurar jugador
        Jugador jugador = new Jugador("Héroe", TipoDeClase.GUERRERO);
        jugador.setUbicacion(ubicacion1);  // Comienza en el Bosque Oscuro
        
        // Configurar criaturas
        Criatura dragon = new Dragon("Dragón del Norte", 100, 50);
        Criatura espectro = new Espectro("Espectro del Pantano", 80, 30);
        Criatura troll = new Troll("Troll de la Aldea", 60, 25);
        
        // Asignar criaturas a ubicaciones
        ubicacion2.setCriatura(dragon);  // Dragón en la montaña
        ubicacion3.setCriatura(espectro);  // Espectro en el pantano
        
        // Configurar tesoro
        Tesoro tesoro = new Tesoro("Amuleto Perdido", ubicacion1);
        ubicacion1.setTesoro(tesoro);  // El tesoro está en el Bosque Oscuro
        
        // Loop del juego (simulación básica)
        while (!jugador.haGanado() && !jugador.haPerdido()) {
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
