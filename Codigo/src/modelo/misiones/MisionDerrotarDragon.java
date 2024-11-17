package modelo.misiones;

import modelo.mapa.SistemaDeUbicaciones;
import modelo.mapa.Ubicacion;
import modelo.personaje.Personaje;

public class MisionDerrotarDragon extends Mision {
	private String nombreUbicacion;

	public MisionDerrotarDragon() {
        super("Derrota al Dragón del Norte", "Vencer al Dragón del Norte en las Montañas Heladas.", "Espada de Fuego (+20% ataque)");
        this.nombreUbicacion = "Montañas Heladas"; // La misión sabe en qué ubicación debería estar el dragón
    }

	@Override
    public void completar() {
        Ubicacion ubicacion = obtenerUbicacion(nombreUbicacion); // Método para obtener la ubicación desde un sistema central
        if (ubicacion != null && ubicacion.tieneCriatura() && !ubicacion.getCriatura().estaVivo()) {
            completada = true;
            System.out.println("¡Has completado la misión de derrotar al Dragón del Norte y has recibido la Espada de Fuego!");
        } else {
            System.out.println("El dragón sigue vivo o no has llegado a la ubicación correcta. No puedes completar la misión.");
        }
    }

	@Override
    public void aplicarRecompensa(Personaje personaje) {
        if (completada) {
            personaje.mejorar(20, 0); // Aumenta un 20% el ataque
            System.out.println("Tu ataque ha aumentado en un 20% gracias a la Espada de Fuego.");
        }
    }
	
	// Método que simula la obtención de una ubicación desde un sistema central o base de datos de ubicaciones
    private Ubicacion obtenerUbicacion(String nombreUbicacion) {
        // Aquí puedes implementar la lógica para acceder a un sistema que te proporcione la ubicación por nombre
        return SistemaDeUbicaciones.getUbicacion(nombreUbicacion); // Supone que existe un sistema global de ubicaciones
    }
}
