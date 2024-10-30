package modelo.misiones;

import modelo.personaje.Personaje;

public abstract class Mision {
	protected String nombre;
    protected String descripcion;
    protected String recompensaDescripcion;
    protected boolean completada;

 // Constructor que acepta nombre, descripción y la recompensa
    public Mision(String nombre, String descripcion, String recompensaDescripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensaDescripcion = recompensaDescripcion;
        this.completada = false;
    }

 // Método abstracto para completar la misión (definido por las subclases)
    public abstract void completar();

    // Método abstracto para aplicar la recompensa al personaje
    public abstract void aplicarRecompensa(Personaje personaje);

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }
}
