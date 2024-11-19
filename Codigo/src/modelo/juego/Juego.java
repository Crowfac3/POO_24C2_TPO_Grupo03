package modelo.juego;

import modelo.criatura.Criatura;
import modelo.criatura.*;
import modelo.mapa.Mapa;
import modelo.mapa.Ubicacion;
import vista.PantallaMejoraPersonaje;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Juego {
    private Jugador jugador;
    private Mapa mapa;
    private boolean tesoroEncontrado;
    private Pelea peleaActual;

    public Juego() {
        this.jugador = null;
        this.tesoroEncontrado = false; // Inicialmente, el tesoro no ha sido encontrado
        this.mapa= new Mapa();
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Mapa getMapa() {
        return mapa;
    }
    
    public void verMapa() {
        mapa.mostrarMapa(); // Llama al método mostrarMapa() para imprimir el mapa
    }
    
    public void configurarJuegoInicial() {
        Random random = new Random();
        Set<String> ubicacionesOcupadas = new HashSet<>(); // Para evitar duplicados

        // Establecer ubicaciones neutrales
        for (int i = 0; i < 3; i++) { // Tres ubicaciones neutrales
            int[] coords = generarCoordenadasUnicas(random, ubicacionesOcupadas);
            mapa.establecerUbicacionNeutral(coords[0], coords[1]);
        }

        // Establecer ubicaciones con criaturas
        int[] coordsTroll = generarCoordenadasUnicas(random, ubicacionesOcupadas);
        mapa.establecerUbicacionConCriatura(coordsTroll[0], coordsTroll[1], new Troll("Troll de la Cueva", 100, 15, 10));

        int[] coordsDragon = generarCoordenadasUnicas(random, ubicacionesOcupadas);
        mapa.establecerUbicacionConCriatura(coordsDragon[0], coordsDragon[1], new Dragon("Dragón del Norte", 200, 30, 25));

        // Establecer la ubicación del tesoro (protegido por el dragón)
        mapa.establecerUbicacionConTesoro(coordsDragon[0], coordsDragon[1]); // Tesoro en la misma ubicación que el dragón
        
        
        // Establecer ubicaciones con nuevas criaturas
        int[] coordsEspectro = generarCoordenadasUnicas(random, ubicacionesOcupadas);
        mapa.establecerUbicacionConCriatura(coordsEspectro[0], coordsEspectro[1], new Espectro("Espectro de las Sombras", 120, 20, 15));
        
        int[] coordsEspectro2 = generarCoordenadasUnicas(random, ubicacionesOcupadas);
        mapa.establecerUbicacionConCriatura(coordsEspectro2[0], coordsEspectro2[1], new Espectro("Espectro de las Sombras", 120, 20, 15));

        

        System.out.println("Juego configurado con ubicaciones iniciales:");
        mapa.mostrarMapa(); // Mostrar mapa en consola
    }

    // Método auxiliar para generar coordenadas únicas
    private int[] generarCoordenadasUnicas(Random random, Set<String> ubicacionesOcupadas) {
        int fila, columna;
        do {
            fila = random.nextInt(10); // Generar fila entre 0 y 9
            columna = random.nextInt(10); // Generar columna entre 0 y 9
        } while (!ubicacionesOcupadas.add(fila + "," + columna)); // Añadir coordenadas al set
        return new int[]{fila, columna};
    }
    
    public void visitarUbicacion(Ubicacion ubicacion) {
    	jugador.setUbicacionActual(ubicacion);
    	
    	
        if (ubicacion.tieneCriatura()) {
            System.out.println("Te has encontrado con " + ubicacion.getCriatura().getNombre() + " en esta ubicación.");
            iniciarPelea(ubicacion);
            
        } else if (ubicacion.esNeutral()) {
            System.out.println("Has llegado a una ubicación neutral.");
            //ubicacion.descansar(jugador.getPersonaje()); // Permite descansar
            //ubicacion.reclamarRecompensa(jugador.getPersonaje()); // Permite reclamar recompensa si está disponible
            //ubicacion.canjearExperiencia(jugador.getPersonaje());
            
            
        } else if (ubicacion.tieneTesoro()) {
        	ubicacion.mostrarTesoroSiGanaste();
        
        } else {
            System.out.println("Aquí no hay nada especial.");
        }
    }

    // Método para inicializar una pelea
    public void iniciarPelea(Ubicacion ubicacion) {
        Criatura criatura = ubicacion.getCriatura();
        if (criatura == null) {
            System.out.println("No hay criatura para combatir en esta ubicación.");
            return;
        }
        peleaActual = new Pelea(jugador.getPersonaje(), criatura);

        if (criatura instanceof Dragon) {
            verificarVictoria(criatura, ubicacion);
        }
    }
    
 // Ejecutar un turno de combate
    public boolean ejecutarTurnoDeCombate() {
        if (peleaActual == null) {
            System.out.println("No hay pelea activa.");
            return false;
        }
        peleaActual.ejecutarTurno(); // Delegar la lógica del combate
        return true;
    }

    // Obtener la criatura actual (si existe)
    public Criatura getCriaturaActual() {
        return peleaActual != null ? peleaActual.getCriatura() : null;
    }

    // Obtener la pelea actual
    public Pelea getPeleaActual() {
        return peleaActual;
    }

    public void verificarVictoria(Criatura criatura, Ubicacion ubicacion) {
        if (criatura instanceof Dragon && !tesoroEncontrado && ubicacion.tieneTesoro()) {
            tesoroEncontrado = true;
            System.out.println("¡Felicidades! Has derrotado al dragón y encontrado el tesoro.");
        }
    }

    public void finDelJuego() {
        if (!tesoroEncontrado) {
            System.out.println("El jugador ha sido derrotado. Fin del juego.");
        } else {
            System.out.println("¡Victoria! El tesoro ha sido encontrado.");
        }
    }
    
    public Jugador getJugador() {
        return jugador;
    }

    
    public boolean esTesoroEncontrado() {
        return tesoroEncontrado;
    }
    
    public boolean descansarPersonaje() {
        if (jugador != null && jugador.getUbicacionActual() != null && jugador.getPersonaje() != null) {
            Ubicacion ubicacionActual = jugador.getUbicacionActual();
            if (ubicacionActual.esNeutral()) {
                ubicacionActual.descansar(jugador.getPersonaje());
                return true;
            }
        }
        return false;
    }

    public boolean reclamarRecompensa() {
        if (jugador != null && jugador.getUbicacionActual() != null && jugador.getPersonaje() != null) {
            Ubicacion ubicacionActual = jugador.getUbicacionActual();
            if (ubicacionActual.esNeutral()) {
                ubicacionActual.reclamarRecompensa(jugador.getPersonaje());
                return true;
            }
        }
        return false;
    }
     
    
}
