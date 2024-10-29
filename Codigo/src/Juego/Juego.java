package Juego;

import Mapa.Ubicacion;
import Criatura.Criatura;

public class Juego {
    private Jugador jugador;
    private boolean tesoroEncontrado;

    public Juego(Jugador jugador) {
        this.jugador = jugador;
        this.tesoroEncontrado = false; // Inicialmente, el tesoro no ha sido encontrado
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

    public void iniciarPelea(Ubicacion ubicacion) {
        Criatura criatura = ubicacion.getCriatura();
        Pelea pelea = new Pelea(jugador.getPersonaje(), criatura);
        pelea.iniciar();

        if (pelea.ganoPersonaje()) {
            System.out.println("Has derrotado a la criatura.");
            // Eliminar la criatura de la ubicación para poder mostrar el tesoro
            ubicacion.setCriatura(null);
            ubicacion.mostrarTesoroSiGanaste();
        } else {
            finDelJuego();
        }
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
}
