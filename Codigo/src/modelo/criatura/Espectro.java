package modelo.criatura;

import modelo.personaje.Arquero;
import modelo.personaje.Personaje;

public class Espectro extends Criatura {

	public Espectro(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa, 80); 
    }

    @Override
    public void atacar(Personaje personaje) {
        int ataque = nivelAtaque;
        if (personaje instanceof Arquero) {
            ataque *= 1.2; // Aumenta un 20% el ataque contra arqueros
        }
        personaje.recibirGolpe(ataque);
    }
}
