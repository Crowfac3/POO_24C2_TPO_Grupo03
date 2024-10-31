package modelo.juego;

import java.util.ArrayList;
import java.util.List;

import modelo.mapa.Ubicacion;
import modelo.misiones.Mision;
import modelo.misiones.MisionDerrotarDragon;
import modelo.misiones.MisionEliminarEspectros;
import modelo.misiones.MisionLimpiaAldeaTrolls;
import modelo.misiones.MisionRecuperarAmuleto;



public class Jugador {
	@SuppressWarnings("unused")
	private String nombre;
    private modelo.personaje.Personaje personaje;
    private List<Mision> misiones;

    public Jugador(String nombre, String tipoPersonaje) {
        switch (tipoPersonaje.toLowerCase()) {
            case "guerrero":
                this.personaje = new modelo.personaje.Guerrero(nombre, 100, 20, 15);
                break;
            case "mago":
                this.personaje = new modelo.personaje.Mago(nombre, 80, 25, 10);
                break;
            case "arquero":
                this.personaje = new modelo.personaje.Arquero(nombre, 90, 18, 12, 85, 20);
                break;
            default:
                throw new IllegalArgumentException("Tipo de personaje no válido.");
        }
        this.misiones = new ArrayList<>();
        inicializarMisiones();
    }

    public modelo.personaje.Personaje getPersonaje() {
        return personaje;
    }

    public void mostrarInformacionPersonaje() {
        System.out.println("Jugador ha creado un " + personaje.getClass().getSimpleName() + " llamado " + personaje.getNombre());
    }
    
    public void visitarUbicacion(Ubicacion ubicacion) {
        System.out.println("Visitando la ubicación: " + ubicacion.getNombre());
        // Aquí puedes añadir la lógica que corresponde a la visita de una ubicación
    }
    
    private void inicializarMisiones() {
        misiones.add(new MisionDerrotarDragon());
        misiones.add(new MisionRecuperarAmuleto());
        misiones.add(new MisionEliminarEspectros());
        misiones.add(new MisionLimpiaAldeaTrolls());
    }
    
    public void completarMision(String nombreMision) {
        for (Mision mision : misiones) {
            if (mision.getNombre().equals(nombreMision) && !mision.estaCompletada()) {
                mision.completar();
                return;
            }
        }
        System.out.println("No tenes esta misión o ya ha sido completada.");
    }
    
    public void reclamarRecompensaEnUbicacionNeutral() {
        for (Mision mision : misiones) {
            if (mision.estaCompletada()) {
                System.out.println("Reclamando recompensa de la misión: " + mision.getNombre());
                mision.aplicarRecompensa(personaje); // Aplica la recompensa solo si la misión está completada
            } else {
                System.out.println("No has completado la misión: " + mision.getNombre());
            }
        }
    }
    
}