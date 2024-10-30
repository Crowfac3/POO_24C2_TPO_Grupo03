package modelo.criatura;

import modelo.personaje.Guerrero;
import modelo.personaje.Personaje;

public class Dragon extends Criatura {

    public Dragon(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
    }

    @Override
    public void atacar(Personaje personaje) {
        int ataque = nivelAtaque;
        if (personaje instanceof Guerrero) {
            ataque *= 1.3; // Aumenta un 30% el ataque contra guerreros
        }
        int danio = ataque - personaje.getNivelDefensa();
        personaje.recibirGolpe(danio);
    }
}
