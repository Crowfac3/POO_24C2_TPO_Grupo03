package Criatura;

import Personaje.Personaje;
import Personaje.Mago;

public class Troll extends Criatura {

    public Troll(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
    }

    @SuppressWarnings("unused")
	@Override
    public void atacar(Personaje personaje) {
        int ataqueModificado = nivelAtaque;
        int defensaModificada = nivelDefensa;

        // Si el personaje es un Mago, el Troll aumenta su defensa un 15%
        if (personaje instanceof Mago) {
            defensaModificada *= 1.15; // Aumenta un 15% la defensa contra magos
        }

        // El cálculo del daño debe basarse en el ataque modificado por las habilidades de la criatura
        int danio = ataqueModificado - personaje.getNivelDefensa();
        if (danio < 0) {
            danio = 0; // Evitamos valores negativos de daño
        }

        personaje.recibirGolpe(danio);
    }
}
