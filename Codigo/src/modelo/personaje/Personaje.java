package modelo.personaje;

import modelo.criatura.Criatura;
import modelo.objectViews.PersonajeView;

public abstract class Personaje {
	
	
	
	protected String nombre;
    protected int puntosVida;
    protected int nivelAtaque;
    protected int nivelDefensa;
    protected int experiencia;
    
    public Personaje(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experiencia = 0; // Inicialmente sin experiencia
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

    public int getExperiencia() {
        return experiencia;
    }
    
    public void ganarExperiencia(int puntos) {
        experiencia += puntos;
        System.out.println(nombre + " ha ganado " + puntos + " puntos de experiencia. Experiencia actual: " + experiencia);
    }
    
    public void recibirGolpe(int danio) {
        if (nivelDefensa > 0) {
            nivelDefensa -= danio;
            if (nivelDefensa < 0) {
                puntosVida += nivelDefensa; // Si la defensa queda en negativo, resta el exceso a la vida
                nivelDefensa = 0;
            }
        } else {
            puntosVida -= danio;
        }
    }

    public abstract void atacar(Criatura criatura);
    
    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public void mejorar(int puntosMejoraAtaque, int puntosMejoraDefensa) {
        nivelAtaque += puntosMejoraAtaque;
        nivelDefensa += puntosMejoraDefensa;
    }


    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    
 // Método para canjear experiencia y mejorar ataque
    public boolean mejorarAtaque() {
        int costoMejora = 50; // Costo de experiencia para mejorar ataque
        if (experiencia >= costoMejora) {
            nivelAtaque += 10; // Mejora de 10 puntos en ataque
            experiencia -= costoMejora;
            System.out.println(nombre + " ha mejorado su ataque. Nivel de ataque actual: " + nivelAtaque);
            return true;
        } else {
            System.out.println(nombre + " no tiene suficiente experiencia para mejorar el ataque.");
            return false;
        }
    }
    
    
 // Método para canjear experiencia y mejorar defensa
    public boolean mejorarDefensa() {
        int costoMejora = 30; // Costo de experiencia para mejorar defensa
        if (experiencia >= costoMejora) {
            nivelDefensa += 5; // Mejora de 5 puntos en defensa
            experiencia -= costoMejora;
            System.out.println(nombre + " ha mejorado su defensa. Nivel de defensa actual: " + nivelDefensa);
            return true;
        } else {
            System.out.println(nombre + " no tiene suficiente experiencia para mejorar la defensa.");
            return false;
        }
    }
    
    
    public PersonajeView toView() {
        return new PersonajeView(nombre, puntosVida, nivelAtaque, nivelDefensa);
    }

}
