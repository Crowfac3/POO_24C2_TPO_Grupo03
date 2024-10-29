package Criatura;

import Personaje.Personaje;
import Personaje.Arquero;

public class Espectro extends Criatura {

    public Espectro(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
    }

    @Override
    public void atacar(Personaje personaje) {
        int ataque = nivelAtaque;
        if (personaje instanceof Arquero) {
            ataque *= 1.2; // Aumenta un 20% el ataque contra arqueros
        }
        int danio = ataque - personaje.getNivelDefensa();
        personaje.recibirGolpe(danio);
    }
}
