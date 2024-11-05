package Juego;

import java.util.Random;
import java.util.Scanner;
import Mapa.Ubicacion;
import Mapa.SistemaDeUbicaciones;
import Criatura.Criatura;
import Criatura.Dragon;
import Criatura.Espectro;
import Criatura.Troll;
import Jugador.Jugador;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Preguntar al usuario qué tipo de personaje quiere ser
        System.out.println("Bienvenido a la aventura. Elige tu personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Arquero");
        System.out.print("Ingresa el número de tu elección: ");
        int opcionPersonaje = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        String tipoPersonaje = "";
        switch (opcionPersonaje) {
            case 1:
                tipoPersonaje = "guerrero";
                break;
            case 2:
                tipoPersonaje = "mago";
                break;
            case 3:
                tipoPersonaje = "arquero";
                break;
            default:
                System.out.println("Opción no válida. Se te asignará el personaje Guerrero por defecto.");
                tipoPersonaje = "guerrero";
                break;
        }

        // Crear el jugador con el tipo de personaje elegido
        System.out.print("Ingresa el nombre de tu personaje: ");
        String nombreJugador = scanner.nextLine();
        Jugador jugador = new Jugador(nombreJugador, tipoPersonaje);
        jugador.mostrarInformacionPersonaje(); // Mostrar la información del personaje creado

        // Crear la ubicación para la criatura
        Ubicacion montanasHeladas = new Ubicacion("Montañas Heladas", false); // Ubicación no neutral
        
        // Seleccionar aleatoriamente una criatura entre Dragon, Espectro, y Troll
        Criatura criatura;
        int opcionCriatura = random.nextInt(3);
        switch (opcionCriatura) {
            case 0:
                criatura = new Dragon("Dragón del Norte", 150, 25, 10);
                break;
            case 1:
                criatura = new Espectro("Espectro del Vacío", 120, 20, 15);
                break;
            case 2:
                criatura = new Troll("Troll de las Montañas", 200, 30, 5);
                break;
            default:
                throw new IllegalStateException("Error en la selección de criatura.");
        }
        
        // Colocar la criatura en la ubicación
        montanasHeladas.setCriatura(criatura);

        // Registrar la ubicación en el sistema de ubicaciones
        SistemaDeUbicaciones.registrarUbicacion(montanasHeladas);

        // El jugador elige si quiere explorar las Montañas Heladas
        System.out.println("\n¿Deseas explorar las Montañas Heladas? (sí/no)");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("sí") || respuesta.equals("si")) {
            // El jugador entra en la ubicación y verifica si hay una criatura
            if (montanasHeladas.tieneCriatura()) {
                System.out.println("Te has encontrado con una criatura en esta ubicación: " + montanasHeladas.getCriatura().getNombre());

                // Iniciar la pelea entre el jugador y la criatura de la ubicación
                Pelea pelea = new Pelea(jugador.getPersonaje(), montanasHeladas.getCriatura());
                pelea.iniciar();

                // Completar la misión de derrotar a la criatura si es derrotada
                if (pelea.ganoPersonaje()) {
                    System.out.println("\n--- Completando la misión: Derrota a " + montanasHeladas.getCriatura().getNombre() + " ---");
                    jugador.completarMision("Derrota a " + montanasHeladas.getCriatura().getNombre());
                } else {
                    System.out.println("Has sido derrotado. Fin de la aventura.");
                    return;
                }
            } else {
                System.out.println("No hay ninguna criatura en esta ubicación.");
            }
        } else {
            System.out.println("Decides no explorar las Montañas Heladas. Fin de la aventura.");
            return;
        }

        // El jugador viaja a una ubicación neutral (ej: Santuario) para reclamar la recompensa
        System.out.println("\n--- El jugador viaja a un Santuario para reclamar su recompensa ---");
        Ubicacion santuario = new Ubicacion("Santuario", true); // Ubicación neutral
        santuario.reclamarRecompensaMisiones(jugador);

        scanner.close();
    }
}
