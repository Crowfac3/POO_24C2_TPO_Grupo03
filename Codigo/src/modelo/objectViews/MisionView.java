package modelo.objectViews;

public class MisionView {
    private String nombre;
    private String descripcion;
    private boolean completa;

    public MisionView(String nombre, String descripcion, boolean completa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completa = completa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completa;
    }
}
