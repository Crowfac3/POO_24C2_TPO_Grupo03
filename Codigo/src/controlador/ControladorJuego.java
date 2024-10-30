package controlador;

import modelo.juego.Jugador;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;
import modelo.personaje.Personaje;

public class ControladorJuego {
    private Jugador jugador;
    private Mapa mapa;

    public ControladorJuego() {
        this.mapa = new Mapa();
    }

    public void crearPersonaje(String tipoPersonaje) {
        jugador = new Jugador("Jugador", tipoPersonaje);
    }

    public void mostrarMapa() {
        mapa.mostrarMapa();
    }

    public void visitarUbicacion(int fila, int columna) {
        Ubicacion ubicacion = mapa.obtenerUbicacion(fila, columna);
        jugador.visitarUbicacion(ubicacion);
    }

    public void iniciarCombate() {
        // Lógica para iniciar el combate
    }

    public void mostrarMisiones() {
        // Lógica para mostrar misiones del jugador
    }

    public void mejorarAtaque() {
        jugador.getPersonaje().mejorarAtaque();
    }

    public void mejorarDefensa() {
        jugador.getPersonaje().mejorarDefensa();
    }

    public Personaje getPersonaje() {
        return jugador.getPersonaje();
    }
    
    public void reclamarRecompensa(String nombreMision) {
    	jugador.reclamarRecompensaEnUbicacionNeutral();
    }
}
