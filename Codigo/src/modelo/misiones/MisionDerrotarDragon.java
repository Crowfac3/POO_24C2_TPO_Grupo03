package modelo.misiones;

import modelo.mapa.Ubicacion;
import modelo.personaje.Personaje;
import modelo.juego.Juego;

public class MisionDerrotarDragon extends Mision {
    private String nombreUbicacion;
    private Juego juego;

    public MisionDerrotarDragon() {
        super("Derrota al Dragón del Norte", 
              "Vencer al Dragón Menor en las Montañas Heladas.", 
              "Espada de Fuego (+20% ataque)");
        this.nombreUbicacion = "Montañas Heladas"; // Nombre de la ubicación donde está el dragón menor
    }

    @Override
    public void completar() {
        Ubicacion ubicacion = juego.obtenerUbicacionPorNombre(nombreUbicacion);
        if (ubicacion != null && ubicacion.tieneCriatura() && !ubicacion.getCriatura().estaVivo()) {
            completada = true;
            System.out.println("¡Has completado la misión de derrotar al Dragón Menor y has recibido la Espada de Fuego!");
        } else {
            System.out.println("El dragón sigue vivo o no has llegado a la ubicación correcta. No puedes completar la misión.");
        }
    }

    @Override
    public void aplicarRecompensa(Personaje personaje) {
        if (completada) {
            personaje.mejorar(20, 0); // Mejora del ataque en un 20%
            System.out.println("Tu ataque ha aumentado en un 20% gracias a la Espada de Fuego.");
        }
    }

	@Override
	protected void ejecutarRecompensa(Personaje personaje) {
		// TODO Auto-generated method stub
		
	}


}
