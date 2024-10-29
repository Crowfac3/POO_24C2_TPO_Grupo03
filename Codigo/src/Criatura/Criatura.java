package Criatura;

import Personaje.Personaje;

public abstract class Criatura {
	
	protected String nombre;
    protected int puntosVida;
    protected int nivelAtaque;
    protected int nivelDefensa;

    public Criatura(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public int getNivelDefensa() {
        return nivelDefensa;
    }

    public void recibirGolpe(int danio) {
        if (nivelDefensa > 0) {
            nivelDefensa -= danio;
            if (nivelDefensa < 0) {
                puntosVida += nivelDefensa; // Al igual que en Personaje, si la defensa baja de 0, resta a la vida
                nivelDefensa = 0;
            }
        } else {
            puntosVida -= danio;
        }
    }

    public abstract void atacar(Personaje personaje);
    
    public boolean estaVivo() {
        return puntosVida > 0;
    }

}
