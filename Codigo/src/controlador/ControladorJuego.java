package controlador;

import modelo.juego.Juego;
import modelo.juego.Jugador;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;
import modelo.objectViews.PersonajeView;
import modelo.personaje.Personaje;
import vista.PantallaMapa;

public class ControladorJuego {
	private Juego juego;
    private Jugador jugador;
    private Mapa mapa;
    
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


    public void iniciarPelea(int fila, int columna) {
        // Obtener la ubicación desde el mapa
        Ubicacion ubicacion = juego.getMapa().obtenerUbicacion(fila, columna);
        if (ubicacion != null) {
            juego.iniciarPelea(ubicacion); // Llamar al método de Juego
        } else {
            System.out.println("Ubicación inválida: [" + fila + ", " + columna + "]");
        }
    }

    public void mostrarMisiones() {
        // Lógica para mostrar misiones del jugador
    }
    
    public boolean hayCriaturaEnUbicacion(int fila, int columna) {
        Ubicacion ubicacion = juego.getMapa().obtenerUbicacion(fila, columna);
        return ubicacion != null && ubicacion.tieneCriatura();
    }

    public void mejorarAtaque() {
        if (jugador != null && jugador.getPersonaje() != null) {
            jugador.getPersonaje().mejorarAtaque();
        } else {
            System.out.println("No se ha creado un personaje aún.");
        }
    }

    public void mejorarDefensa() {
        if (jugador != null && jugador.getPersonaje() != null) {
            jugador.getPersonaje().mejorarDefensa();
        } else {
            System.out.println("No se ha creado un personaje aún.");
        }
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

    public void abrirPantallaMapa() {
        PantallaMapa pantallaMapa = new PantallaMapa(this);
        pantallaMapa.setVisible(true);  // Esto ahora funcionará correctamente ya que PantallaMapa extiende JFrame
    }
    
    public String obtenerRepresentacionMapa() {
        return mapa.obtenerRepresentacionTexto();  // Utilizar el método del Mapa para obtener la representación como texto.
    }
     
    //#####################
    //#######COMBATE#######
    //#####################
    
    public int getVidaPersonaje() {
        return juego.getJugador().getPersonaje().getPuntosVida();
    }
    
    public int getVidaCriatura() {
        return juego.getCriaturaActual().getPuntosVida();
    }
    
    public boolean ejecutarTurnoDeCombate() {
        return juego.ejecutarTurnoDeCombate();
    }
    
    public boolean ganoPersonaje() {
        return juego.getPeleaActual().ganoPersonaje();
    }


    
}
