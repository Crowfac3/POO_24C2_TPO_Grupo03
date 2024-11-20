package modelo.misiones;

import modelo.personaje.Personaje;

public abstract class Mision {
	protected String nombre;
    protected String descripcion;
    protected String recompensaDescripcion;
    protected boolean completada;
    protected boolean recompensaReclamada;

 // Constructor que acepta nombre, descripción y la recompensa
    public Mision(String nombre, String descripcion, String recompensaDescripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensaDescripcion = recompensaDescripcion;
        this.completada = false;
        this.recompensaReclamada = false;
    }

    // Método abstracto para completar la misión (definido por las subclases)
    public abstract void completar();
    
    // Método para aplicar la recompensa al personaje
    public void aplicarRecompensa(Personaje personaje) {
        if (completada && !recompensaReclamada) {
            ejecutarRecompensa(personaje);
            recompensaReclamada = true; // Marca la recompensa como reclamada
        } else if (recompensaReclamada) {
            System.out.println("La recompensa ya ha sido reclamada para esta misión.");
        } else {
            System.out.println("La misión no está completada aún.");
        }
    }

    // Método abstracto para definir la lógica específica de la recompensa
    protected abstract void ejecutarRecompensa(Personaje personaje);

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
