package Juego;

import Personaje.Guerrero;
import Personaje.Personaje;
import Criatura.Criatura;

public class Pelea {
    private Personaje personaje;
    private Criatura criatura;

    public Pelea(Personaje personaje, Criatura criatura) {
        this.personaje = personaje;
        this.criatura = criatura;
    }

    public void iniciar() {
        System.out.println("----- Iniciando pelea entre " + personaje.getNombre() + " y " + criatura.getNombre() + " -----");

        while (personaje.estaVivo() && criatura.estaVivo()) {
            // El personaje ataca primero
            System.out.println(personaje.getNombre() + " ataca a " + criatura.getNombre() + " con " + personaje.getNivelAtaque() + " de ataque.");
            personaje.atacar(criatura); // Aplica el ataque del personaje
            System.out.println(criatura.getNombre() + " tiene ahora " + criatura.getPuntosVida() + " puntos de vida.");

            // Si la criatura sigue viva, contraataca
            if (criatura.estaVivo()) {
                System.out.println(criatura.getNombre() + " contraataca a " + personaje.getNombre() + " con " + criatura.getNivelAtaque() + " de ataque.");
                criatura.atacar(personaje); // Aplica el ataque de la criatura
                System.out.println(personaje.getNombre() + " tiene ahora " + personaje.getPuntosVida() + " puntos de vida.");
            }
        }

     // Verificar el resultado de la pelea
        if (personaje.estaVivo()) {
            System.out.println(personaje.getNombre() + " ha vencido a " + criatura.getNombre() + "!");
            // Reiniciar contador de ataques del guerrero, si corresponde
            if (personaje instanceof Guerrero) {
                ((Guerrero) personaje).reiniciarContadorAtaques();
                System.out.println("El guerrero ha reiniciado su contador de ataques.");
            }
        } else {
            System.out.println(criatura.getNombre() + " ha derrotado a " + personaje.getNombre() + ".");
        }
    }

 // Método para verificar si el personaje ganó la pelea
    public boolean ganoPersonaje() {
        return personaje.estaVivo();
    }
}
