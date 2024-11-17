package modelo.objectViews;

public class CriaturaView {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;

    public CriaturaView(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    // Método para actualizar la vida de la criatura
    public void setVida(int nuevaVida) {
        this.vida = nuevaVida;
    }

    @Override
    public String toString() {
        return "CriaturaView{" +
                "nombre='" + nombre + '\'' +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                '}';
    }
}
