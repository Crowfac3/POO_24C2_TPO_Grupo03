package modelo.juego;

import modelo.criatura.Criatura;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;

public class Juego {
    private Jugador jugador;
    private Mapa mapa;
    private boolean tesoroEncontrado;
    private Pelea peleaActual;

    public Juego() {
        this.jugador = null;
        this.tesoroEncontrado = false; // Inicialmente, el tesoro no ha sido encontrado
        this.mapa= new Mapa();
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Mapa getMapa() {
        return mapa;
    }
    
    public void verMapa() {
        mapa.mostrarMapa(); // Llama al método mostrarMapa() para imprimir el mapa
    }
    
    public void visitarUbicacion(Ubicacion ubicacion) {
        if (ubicacion.tieneCriatura()) {
            System.out.println("Te has encontrado con " + ubicacion.getCriatura().getNombre() + " en esta ubicación.");
            iniciarPelea(ubicacion);
            
        } else if (ubicacion.esNeutral()) {
            System.out.println("Has llegado a una ubicación neutral.");
            ubicacion.descansar(jugador.getPersonaje()); // Permite descansar
            ubicacion.reclamarRecompensa(jugador.getPersonaje()); // Permite reclamar recompensa si está disponible
            ubicacion.canjearExperiencia(jugador.getPersonaje());
            
        } else if (ubicacion.tieneTesoro()) {
        	ubicacion.mostrarTesoroSiGanaste();
        
        } else {
            System.out.println("Aquí no hay nada especial.");
        }
    }

 // Método para inicializar una pelea
    public void iniciarPelea(Ubicacion ubicacion) {
        Criatura criatura = ubicacion.getCriatura();
        if (criatura == null) {
            System.out.println("No hay criatura para combatir en esta ubicación.");
            return;
        }
        peleaActual = new Pelea(jugador.getPersonaje(), criatura);
    }
    
 // Ejecutar un turno de combate
    public boolean ejecutarTurnoDeCombate() {
        if (peleaActual == null) {
            System.out.println("No hay pelea activa.");
            return false;
        }
        peleaActual.iniciar(); // Delegar la lógica del combate
        return true;
    }

    // Obtener la criatura actual (si existe)
    public Criatura getCriaturaActual() {
        return peleaActual != null ? peleaActual.getCriatura() : null;
    }

    // Obtener la pelea actual
    public Pelea getPeleaActual() {
        return peleaActual;
    }

    public void verificarVictoria(Criatura criatura) {
        // Aquí puedes verificar si la criatura vencida es la que protege el tesoro
        if (criatura.getNombre().equals("Dragón del Norte")) {
            this.tesoroEncontrado = true;
            System.out.println("¡Felicidades! Has encontrado el tesoro y ganado el juego.");
        }
    }

    public void finDelJuego() {
        if (!tesoroEncontrado) {
            System.out.println("El jugador ha sido derrotado. Fin del juego.");
        } else {
            System.out.println("¡Victoria! El tesoro ha sido encontrado.");
        }
    }
    
    public Jugador getJugador() {
        return jugador;
    }

    
    
    
    
    
    
    
    
    
}
