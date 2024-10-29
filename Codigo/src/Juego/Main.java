package Juego;

import Mapa.Ubicacion;
import Mapa.SistemaDeUbicaciones;
import Criatura.Dragon;

public class Main {
    public static void main(String[] args) {
        // Crear la ubicación donde está el dragón
        Ubicacion montanasHeladas = new Ubicacion("Montañas Heladas", false); // Ubicación no neutral
        Dragon dragon = new Dragon("Dragón del Norte", 150, 25, 10); // Crear al dragón
        montanasHeladas.setCriatura(dragon); // Colocar al dragón en la ubicación

        // Registrar la ubicación en el sistema de ubicaciones
        SistemaDeUbicaciones.registrarUbicacion(montanasHeladas);

        // 1. Crear el jugador (ej: Guerrero) sin pasarle detalles del dragón ni de la ubicación
        Jugador jugador = new Jugador("Arthur", "guerrero");
        jugador.mostrarInformacionPersonaje(); // Información del personaje creado

        // El jugador entra en la ubicación y verifica si hay una criatura
        if (montanasHeladas.tieneCriatura()) {
            System.out.println("Te has encontrado con una criatura en esta ubicación: " + montanasHeladas.getCriatura().getNombre());
            // Iniciar la pelea entre el jugador y la criatura de la ubicación
            Pelea pelea = new Pelea(jugador.getPersonaje(), montanasHeladas.getCriatura());
            pelea.iniciar();

            // Completar la misión de derrotar al Dragón del Norte si la criatura es derrotada
            if (pelea.ganoPersonaje()) {
                System.out.println("\n--- Completando la misión: Derrota al Dragón del Norte ---");
                jugador.completarMision("Derrota al Dragón del Norte");
            }
        } else {
            System.out.println("No hay ninguna criatura en esta ubicación.");
        }

        // El jugador viaja a una ubicación neutral (ej: Santuario) para reclamar la recompensa
        System.out.println("\n--- El jugador viaja a un Santuario para reclamar su recompensa ---");
        Ubicacion santuario = new Ubicacion("Santuario", true); // Ubicación neutral
        santuario.reclamarRecompensaMisiones(jugador);
    }
}