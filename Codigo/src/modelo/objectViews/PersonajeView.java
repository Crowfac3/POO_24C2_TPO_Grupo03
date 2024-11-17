package modelo.objectViews;

public class PersonajeView {
    private String nombre;
    private int puntosVida;
    private int nivelAtaque;
    private int nivelDefensa;

    public PersonajeView(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
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
}
