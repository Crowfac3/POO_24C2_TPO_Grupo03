package modelo.juego;

import modelo.criatura.Criatura;
import modelo.personaje.Guerrero;
import modelo.personaje.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Pelea {
	private int contadorTurnos = 0;
    private Personaje personaje;
    private Criatura criatura;
    private List<String> eventosPelea; // Lista para registrar eventos
    private int experienciaPorVictoria;

    public Pelea(Personaje personaje, Criatura criatura) {
        this.personaje = personaje;
        this.criatura = criatura;
        this.eventosPelea = new ArrayList<>(); // Inicializamos la lista
        this.experienciaPorVictoria = criatura.getExperienciaOtorgada();
    }

        
    public void ejecutarTurno() {
        // Alternar turnos basado en el contador
        if (contadorTurnos % 2 == 0) {
            // Turno del personaje
            if (personaje.estaVivo() && criatura.estaVivo()) {
                registrarEvento(personaje.getNombre() + " ataca a " + criatura.getNombre() + " con " + personaje.getNivelAtaque() + " de ataque.");
                personaje.atacar(criatura);
                registrarEvento(criatura.getNombre() + " tiene ahora " + criatura.getPuntosVida() + " puntos de vida.");
                
            }
        } else {
            // Turno de la criatura
            if (criatura.estaVivo() && personaje.estaVivo()) {
                registrarEvento(criatura.getNombre() + " contraataca a " + personaje.getNombre() + " con " + criatura.getNivelAtaque() + " de ataque.");
                criatura.atacar(personaje);
                registrarEvento(personaje.getNombre() + " tiene ahora " + personaje.getPuntosVida() + " puntos de vida.");
                
            }
        }

        // Incrementar el contador de turnos
        contadorTurnos++;

        // Verificar si hay un ganador
        if (!criatura.estaVivo()) {
            registrarEvento(personaje.getNombre() + " ha vencido a " + criatura.getNombre() + "!");
            this.contadorTurnos = 0;
            otorgarExperiencia();
            if (personaje instanceof Guerrero) {
                ((Guerrero) personaje).reiniciarContadorAtaques();
                registrarEvento("El guerrero ha reiniciado su contador de ataques.");
            }
        } else if (!personaje.estaVivo()) {
            registrarEvento(criatura.getNombre() + " ha derrotado a " + personaje.getNombre() + ".");
        }
    }




    // Método para registrar eventos en la lista
    private void registrarEvento(String evento) {
        eventosPelea.add(evento);
    }

    // Obtener la lista de eventos de la pelea
    public List<String> getEventosPelea() {
        return eventosPelea;
    }

    public boolean ganoPersonaje() {
        return personaje.estaVivo();
    }

    public Criatura getCriatura() {
        return criatura;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
    
    private void otorgarExperiencia() {
        personaje.ganarExperiencia(experienciaPorVictoria);
        registrarEvento(personaje.getNombre() + " ha ganado " + experienciaPorVictoria + " puntos de experiencia.");
    }
}
