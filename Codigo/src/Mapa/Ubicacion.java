package Mapa;

import Personaje.Personaje;
import Criatura.Criatura;
import Juego.Jugador;

import java.util.Scanner;

public class Ubicacion {
    @SuppressWarnings("unused")
	private String nombre;
    private Criatura criatura;
    private boolean contieneTesoro;
    private boolean esNeutral;
    private boolean recompensaReclamada; // Indica si ya se reclamó la recompensa

    public Ubicacion(String nombre, boolean esNeutral) {
        this.nombre = nombre;
        this.esNeutral = esNeutral;
        this.contieneTesoro = false;
        this.recompensaReclamada = false; // Inicialmente, la recompensa no ha sido reclamada
    }

    public void setCriatura(Criatura criatura) {
        this.criatura = criatura;
    }

    public boolean tieneCriatura() {
        return criatura != null;
    }

    public Criatura getCriatura() {
        return criatura;
    }

    public boolean tieneTesoro() {
        return contieneTesoro;
    }

 // Método para establecer el tesoro solo si hay una criatura
    public void setTesoro(boolean contieneTesoro) {
        if (tieneCriatura()) {
            this.contieneTesoro = contieneTesoro;
        } else {
            System.out.println("El tesoro solo puede estar en una ubicación con una criatura.");
        }
    }
    
 // Mostrar el tesoro solo si no queda criatura
    public void mostrarTesoroSiGanaste() {
        if (contieneTesoro && !tieneCriatura()) {
            System.out.println("¡Has encontrado el tesoro!");
        }
    }

    public boolean esNeutral() {
        return esNeutral;
    }

    public void descansar(Personaje personaje) {
        if (esNeutral) {
            personaje.setPuntosVida(100); // Restaurar puntos de vida al máximo
            System.out.println(personaje.getNombre() + " ha descansado y su vida ha sido restaurada al 100%.");
        } else {
            System.out.println("No puedes descansar aquí.");
        }
    }

    public void reclamarRecompensa(Personaje personaje) {
        if (esNeutral && !recompensaReclamada) {
        	
            System.out.println(personaje.getNombre() + " ha reclamado una recompensa.");
            // Aquí puedes implementar el efecto de la recompensa, por ejemplo, aumentar el ataque o la defensa
            personaje.mejorar(10, 5); // Ejemplo: aumenta 10 puntos de ataque y 5 de defensa
            recompensaReclamada = true; // Marca la recompensa como reclamada
        } else if (recompensaReclamada) {
            System.out.println("La recompensa ya ha sido reclamada en esta ubicación.");
        } else {
            System.out.println("No puedes reclamar una recompensa aquí.");
        }
    }
    
    // Nuevo metodo para canjear puntos de experiencia
    
    @SuppressWarnings("resource")
	public void canjearExperiencia(Personaje personaje) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Deseas mejorar ataque (A) o deffensa (D)? Ingresa A o D");
    	String opcion = scanner.nextLine().toUpperCase();
    	
    	switch(opcion) {
    	case "A":
    			if (!personaje.mejorarAtaque()) {
    				System.out.println("No tenes suficiente experiencia para mejorar el ataque");
    			}
    			break;
    	case "D":
    			if (!personaje.mejorarDefensa()) {
    				System.out.println("No tenes suficiente experiencia para mejorar la defensa");
    			}
    			break;
    	default:
    			System.out.println("Opcion no valida");
    			break;
    	}
    }
    
 // Nuevo método para reclamar recompensas de misiones en ubicaciones neutrales
    public void reclamarRecompensaMisiones(Jugador jugador) {
        if (esNeutral) {
            jugador.reclamarRecompensaEnUbicacionNeutral(); // El jugador reclama recompensas de misiones completadas
        } else {
            System.out.println("No puedes reclamar recompensas de misiones aquí.");
        }
    }

    @Override
    public String toString() {
        if (contieneTesoro) {
            return "T"; // T de Tesoro
        } else if (tieneCriatura()) {
            return "C"; // C de Criatura
        } else if (esNeutral) {
            return "N"; // N de Neutral
        } else {
            return "."; // Ubicación vacía
        }
    }
}
