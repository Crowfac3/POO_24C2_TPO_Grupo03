package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.juego.Juego;
import modelo.juego.Jugador;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;
import modelo.objectViews.CriaturaView;
import modelo.objectViews.PersonajeView;
import modelo.personaje.Personaje;
import vista.PantallaCombate;
import vista.PantallaEstadoPersonaje;
import vista.PantallaMapa;

public class ControladorJuego {
	private Juego juego;
    private Jugador jugador;
    private Mapa mapa;
	private PantallaMapa pantallaMapa;
    
    public ControladorJuego(Juego juego) {
        this.juego = juego;
    }
    
    
    public Juego getJuego() {
        return juego;
    }

    public ControladorJuego() {
        this.mapa = new Mapa();
    }

    public void crearPersonaje(String nombre, String tipoPersonaje) {
    	// Verifica si el jugador no está inicializado y lo crea
        if (juego.getJugador() == null) {
            juego.setJugador(new Jugador(nombre,tipoPersonaje));
            this.jugador = juego.getJugador();
        }
        if (this.jugador.getPersonaje() == null) {
            throw new IllegalStateException("No se pudo crear el personaje correctamente.");
        }
    }

    public void mostrarMapa() {
        mapa.mostrarMapa();
    }

    public void visitarUbicacion(int fila, int columna) {
        Ubicacion ubicacion = juego.getMapa().obtenerUbicacion(fila, columna);
        juego.visitarUbicacion(ubicacion);
    }
    
    public boolean estaEnUbicacionNeutral() {
        Ubicacion ubicacionActual = juego.getJugador().getUbicacionActual();
        return ubicacionActual.esNeutral();
    }
    
    
    public void iniciarPelea(int fila, int columna) {
        juego.visitarUbicacion(juego.getMapa().obtenerUbicacion(fila, columna));

        if (juego.getPeleaActual() != null) { // Si hay una pelea activa
            PantallaCombate pantallaCombate = new PantallaCombate(this);
            pantallaCombate.mostrar(); 
        } else {
            JOptionPane.showMessageDialog(null, "No se puede iniciar la pelea. No hay criatura en esta ubicación.");
        }
    }

    public void mostrarMisiones() {
        // Lógica para mostrar misiones del jugador
    }
    
    public void descansarPersonaje() {
        if (!juego.descansarPersonaje()) {
            System.out.println("No puedes descansar aquí.");
        }
    }

    public boolean reclamarRecompensa() {
        return juego.reclamarRecompensa();
    }
    
  
    public boolean hayCriaturaEnUbicacion(int fila, int columna) {
        Ubicacion ubicacion = juego.getMapa().obtenerUbicacion(fila, columna);
        return ubicacion != null && ubicacion.tieneCriatura();
    }

    public boolean mejorarAtaque() {
        return juego.getJugador().getPersonaje().mejorarAtaque();
    }

    public boolean mejorarDefensa() {
        return juego.getJugador().getPersonaje().mejorarDefensa();
    }

    public Personaje getPersonaje() {
        return jugador != null ? jugador.getPersonaje() : null;
    }

    public void reclamarRecompensa(String nombreMision) {
        if (jugador != null) {
            jugador.reclamarRecompensaEnUbicacionNeutral();
        } else {
            System.out.println("No se ha creado un jugador aún.");
        }
    }

    public PersonajeView obtenerEstadoPersonaje() {
        // Validación para asegurarse de que jugador y personaje no sean null
        if (jugador == null || jugador.getPersonaje() == null) {
            throw new IllegalStateException("El personaje no ha sido inicializado.");
        }

        return jugador.getPersonaje().toView();
    }
    
    public CriaturaView obtenerEstadoCriatura() {
        if (juego.getPeleaActual() == null || juego.getPeleaActual().getCriatura() == null) {
            throw new IllegalStateException("No hay criatura activa en el combate.");
        }

        return juego.getPeleaActual().getCriatura().toView();
    }
    
    public void setPantallaMapa(PantallaMapa pantallaMapa) {
        this.pantallaMapa = pantallaMapa;
    }

    public void abrirPantallaMapa() {
        if (pantallaMapa == null) {
            pantallaMapa = new PantallaMapa(this);
        }
        pantallaMapa.mostrar();
    }
    
    public String obtenerRepresentacionMapa() {
        return mapa.obtenerRepresentacionTexto();  // Utilizar el método del Mapa para obtener la representación como texto.
    }
     
    //#####################
    //#######COMBATE#######
    //#####################
    
    public int getVidaPersonaje() {
        try {
            return obtenerEstadoPersonaje().getPuntosVida();
        } catch (IllegalStateException e) {
            return 0; // Retorna 0 si no hay personaje
        }
    }

    
    public int getVidaCriatura() {
        try {
            return obtenerEstadoCriatura().getVida();
        } catch (IllegalStateException e) {
            return 0; // Retorna 0 si no hay criatura
        }
    }

    
    public void ejecutarTurnoDeCombate() {
        if (juego.getPeleaActual() != null) {
            juego.getPeleaActual().ejecutarTurno();
        }
    }

    public List<String> obtenerEventosPelea() {
        if (juego.getPeleaActual() != null) {
            return juego.getPeleaActual().getEventosPelea();
        }
        return new ArrayList<>();
    }

    public void limpiarEventosPelea() {
        if (juego.getPeleaActual() != null) {
            juego.getPeleaActual().getEventosPelea().clear();
        }
    }
    
    public boolean ganoPersonaje() {
        return juego.getPeleaActual().ganoPersonaje();
    }
    
    public boolean esVictoriaFinal() {
        return juego.esTesoroEncontrado();
    }
    
    private PantallaEstadoPersonaje pantallaEstadoPersonaje;
    
    public void setPantallaEstadoPersonaje(PantallaEstadoPersonaje pantallaEstadoPersonaje) {
        this.pantallaEstadoPersonaje = pantallaEstadoPersonaje;
    }

    public void actualizarPantallaEstadoPersonaje() {
        if (pantallaEstadoPersonaje != null) {
            pantallaEstadoPersonaje.actualizar();
        }
    }
    
    public void volverAPersonaje() {
        if (pantallaEstadoPersonaje == null) {
            pantallaEstadoPersonaje = new PantallaEstadoPersonaje(this);
        }
        pantallaEstadoPersonaje.actualizar();
        pantallaEstadoPersonaje.mostrar();
    }


    public boolean esUbicacionVisitada(int fila, int columna) {
        // Obtener la ubicación desde el mapa
        Ubicacion ubicacion = juego.getMapa().obtenerUbicacion(fila, columna);
        // Verificar si la ubicación ha sido visitada
        return ubicacion != null && ubicacion.esVisitada();
    }


    public String getDefensaPersonaje() {
        try {
            return String.valueOf(obtenerEstadoPersonaje().getNivelDefensa());
        } catch (IllegalStateException e) {
            return "0"; // Valor predeterminado si el personaje no está inicializado
        }
    }


    public String getDefensaCriatura() {
        if (juego.getPeleaActual() != null && juego.getPeleaActual().getCriatura() != null) {
            CriaturaView criaturaView = juego.getPeleaActual().getCriatura().toView();
            return String.valueOf(criaturaView.getDefensa());
        }
        return "N/A"; // Devuelve "N/A" si no hay criatura activa
    }

    
   
    
}
