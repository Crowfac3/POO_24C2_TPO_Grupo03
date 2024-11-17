package controlador;

import modelo.juego.Jugador;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;
import modelo.personaje.Personaje;
import vista.ObjectView;
import vista.PantallaMapa;

public class ControladorJuego {
    private Jugador jugador;
    private Mapa mapa;

    public ControladorJuego() {
        this.mapa = new Mapa();
    }

    public void crearPersonaje(String tipoPersonaje) {
        // Inicializa el jugador con el tipo de personaje seleccionado
        this.jugador = new Jugador("Jugador", tipoPersonaje);
        if (this.jugador.getPersonaje() == null) {
            throw new IllegalStateException("No se pudo crear el personaje correctamente.");
        }
    }

    public void mostrarMapa() {
        mapa.mostrarMapa();
    }

    public void visitarUbicacion(int fila, int columna) {
        if (mapa != null && fila >= 0 && columna >= 0) {
            Ubicacion ubicacion = mapa.obtenerUbicacion(fila, columna);
            jugador.visitarUbicacion(ubicacion);
            System.out.println("Viajaste a la ubicación [" + fila + ", " + columna + "].");
        } else {
            System.out.println("Coordenadas inválidas.");
        }
    }


    public void iniciarCombate() {
        // Lógica para iniciar el combate
    }

    public void mostrarMisiones() {
        // Lógica para mostrar misiones del jugador
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

    public ObjectView obtenerEstadoPersonaje() {
        // Validación para asegurarse de que jugador y personaje no sean null
        if (jugador == null || jugador.getPersonaje() == null) {
            throw new IllegalStateException("El personaje no ha sido inicializado.");
        }

        // Obtener el estado del personaje del modelo
        Personaje personaje = jugador.getPersonaje();

        // Crear un ObjectView para enviar a la vista
        ObjectView vistaPersonaje = new ObjectView();
        vistaPersonaje.add("Nombre", personaje.getNombre());
        vistaPersonaje.add("Puntos de Vida", personaje.getPuntosVida());
        vistaPersonaje.add("Ataque", personaje.getNivelAtaque());
        vistaPersonaje.add("Defensa", personaje.getNivelDefensa());

        return vistaPersonaje;
    }

    public void abrirPantallaMapa() {
        PantallaMapa pantallaMapa = new PantallaMapa(this);
        pantallaMapa.setVisible(true);  // Esto ahora funcionará correctamente ya que PantallaMapa extiende JFrame
    }
    
    public String obtenerRepresentacionMapa() {
        return mapa.obtenerRepresentacionTexto();  // Utilizar el método del Mapa para obtener la representación como texto.
    }


    
}
