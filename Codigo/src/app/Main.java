package app;

import controlador.ControladorJuego;
import vista.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControladorJuego controlador = new ControladorJuego();

        // Inicializar la pantalla de inicio
        PantallaInicio pantallaInicio = new PantallaInicio(controlador);
        pantallaInicio.mostrar(); // Mostrar la pantalla de selección del personaje

        PantallaMapa pantallaMapa = new PantallaMapa(controlador);
        PantallaCombate pantallaCombate = new PantallaCombate(controlador);
        PantallaMisiones pantallaMisiones = new PantallaMisiones(controlador);
        PantallaMejoraPersonaje pantallaMejoraPersonaje = new PantallaMejoraPersonaje(controlador);
        PantallaFinJuego pantallaFinJuego = new PantallaFinJuego();

        Scanner scanner = new Scanner(System.in);

        boolean juegoActivo = true;

        // Ciclo principal del juego
        while (juegoActivo) {
            System.out.println("\nElige una opción:");
            System.out.println("1. Ver estado del personaje");
            System.out.println("2. Ver mapa");
            System.out.println("3. Combate");
            System.out.println("4. Misiones");
            System.out.println("5. Mejorar personaje");
            System.out.println("6. Fin del juego");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (controlador.getPersonaje() != null) {
                        // Crear la pantalla solo cuando el personaje ya esté inicializado
                        PantallaEstadoPersonaje pantallaEstadoPersonaje = new PantallaEstadoPersonaje(controlador);
                        pantallaEstadoPersonaje.setVisible(true); // Mostrar la ventana
                    } else {
                        System.out.println("Primero debes crear un personaje.");
                    }
                    break;
                case 2:
                    pantallaMapa.setVisible(true);
                    System.out.println("Elige coordenadas (fila y columna) para viajar:");
                    int fila = scanner.nextInt();
                    int columna = scanner.nextInt();
                    controlador.visitarUbicacion(fila, columna);  // Mover la lógica al controlador
                    break;
                case 3:
                    pantallaCombate.iniciarCombate();
                    break;
                case 4:
                    pantallaMisiones.mostrarMisiones();
                    System.out.println("¿Quieres reclamar una recompensa? (escribe el nombre de la misión o 'no')");
                    String nombreMision = scanner.next();
                    if (!nombreMision.equals("no")) {
                        pantallaMisiones.reclamarRecompensa(nombreMision);
                    }
                    break;
                case 5:
                    pantallaMejoraPersonaje.mostrarOpcionesMejora();
                    break;
                case 6:
                    System.out.println("¿Has encontrado el tesoro o fuiste derrotado? (escribe 'victoria' o 'derrota')");
                    String resultado = scanner.next();
                    boolean victoria = resultado.equals("victoria");
                    pantallaFinJuego.mostrarFinDelJuego(victoria);
                    juegoActivo = false;  // Fin del ciclo de juego
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }
}

