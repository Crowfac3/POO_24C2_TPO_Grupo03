package modelo.criatura;

import modelo.personaje.Mago;
import modelo.personaje.Personaje;

public class Troll extends Criatura {

	public Troll(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa, 50); 
    }

    
	@Override
	public void atacar(Personaje personaje) {
        int ataque = nivelAtaque;
        personaje.recibirGolpe(ataque);
    }
	
	public void recibirGolpe(int danio, Personaje atacante) {
	    int defensaEfectiva = nivelDefensa;

	    // Si el atacante es un Mago, aumenta la defensa del Troll en un 15%
	    if (atacante instanceof Mago) {
	        defensaEfectiva += (int) (nivelDefensa * 0.15);
	    }

	    // Aplicar el daño normal (misma lógica que antes)
	    if (defensaEfectiva > 0) {
	        defensaEfectiva -= danio;

	        if (defensaEfectiva < 0) {
	            puntosVida += defensaEfectiva; // defensaEfectiva es negativa aquí
	            nivelDefensa = 0;
	        } else {
	            nivelDefensa = defensaEfectiva;
	        }
	    } else {
	        puntosVida -= danio;
	    }

	    if (puntosVida < 0) {
	        puntosVida = 0;
	    }
	}

}
