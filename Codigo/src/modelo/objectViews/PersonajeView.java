package modelo.objectViews;

public class PersonajeView {
    private String nombre;
    private int puntosVida;
    private int nivelAtaque;
    private int nivelDefensa;
    private int experiencia;

    public PersonajeView(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa, int experiencia) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.experiencia = experiencia;

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
}
