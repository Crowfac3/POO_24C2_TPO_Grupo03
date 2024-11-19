package modelo.criatura;

import modelo.objectViews.CriaturaView;
import modelo.personaje.Personaje;

public abstract class Criatura {
	
	protected String nombre;
    protected int puntosVida;
    protected int nivelAtaque;
    protected int nivelDefensa;
    protected int experienciaOtorgada;

    public Criatura(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa, int experienciaOtorgada) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experienciaOtorgada = experienciaOtorgada;
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
    
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    
    public int getExperienciaOtorgada() {
        return experienciaOtorgada; 
    }

    public void recibirGolpe(int danio) {
        // Caso especial: si el daño es igual o mayor a los puntos de vida actuales, derrotar a la criatura directamente
        if (danio >= puntosVida) {
            puntosVida = 0;
            nivelDefensa = 0;
            return;
        }

        // Daño normal: primero afecta a la defensa
        if (nivelDefensa > 0) {
            nivelDefensa -= danio;
            if (nivelDefensa < 0) {
                puntosVida += nivelDefensa; // Si la defensa baja de 0, resta el exceso a la vida
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
    
    public CriaturaView toView() {
        return new CriaturaView(this.nombre, this.puntosVida, this.nivelAtaque, this.nivelDefensa);
    }


}
