package Juego;

import java.util.ArrayList;
import java.util.List;

import Misiones.Mision;
import Misiones.MisionDerrotarDragon;
import Misiones.MisionRecuperarAmuleto;
import Misiones.MisionEliminarEspectros;
import Misiones.MisionLimpiaAldeaTrolls;
import Criatura.Dragon;



public class Jugador {
	private String nombre;
    private Personaje.Personaje personaje;
    private List<Mision> misiones;

    public Jugador(String nombre, String tipoPersonaje) {
        switch (tipoPersonaje.toLowerCase()) {
            case "guerrero":
                this.personaje = new Personaje.Guerrero(nombre, 100, 20, 15);
                break;
            case "mago":
                this.personaje = new Personaje.Mago(nombre, 80, 25, 10);
                break;
            case "arquero":
                this.personaje = new Personaje.Arquero(nombre, 90, 18, 12, 85, 20);
                break;
            default:
                throw new IllegalArgumentException("Tipo de personaje no válido.");
        }
        this.misiones = new ArrayList<>();
        inicializarMisiones();
    }

    public Personaje.Personaje getPersonaje() {
        return personaje;
    }

    public void mostrarInformacionPersonaje() {
        System.out.println("Jugador ha creado un " + personaje.getClass().getSimpleName() + " llamado " + personaje.getNombre());
    }
    
    private void inicializarMisiones() {
        misiones.add(new MisionDerrotarDragon(new Dragon("Dragón del Norte", 150, 25, 10)));
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
        System.out.println("No tienes esta misión o ya ha sido completada.");
    }
    
    public void reclamarRecompensaEnUbicacionNeutral() {
        for (Mision mision : misiones) {
            if (mision.estaCompletada()) {
                System.out.println("Reclamando recompensa de la misión: " + mision.getNombre());
                // Aplica la recompensa real, por ejemplo, mejorar ataque o defensa
            }
        }
    }
    
}
