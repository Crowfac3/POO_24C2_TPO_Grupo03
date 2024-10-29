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
            personaje.atacar(criatura);
            if (criatura.estaVivo()) {
                // La criatura contraataca si sigue viva
                criatura.atacar(personaje);
            }
        }

        if (personaje.estaVivo()) {
            System.out.println(personaje.getNombre() + " ha vencido a " + criatura.getNombre() + "!");
            if (personaje instanceof Guerrero) {
                ((Guerrero) personaje).reiniciarContadorAtaques(); // Reiniciar el contador de ataques del guerrero
            }
        } else {
            System.out.println(criatura.getNombre() + " ha derrotado a " + personaje.getNombre() + ".");
        }
    }

    public boolean ganoPersonaje() {
        return personaje.estaVivo();
    }
}
