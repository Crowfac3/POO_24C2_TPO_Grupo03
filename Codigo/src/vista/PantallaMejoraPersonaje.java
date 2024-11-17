package vista;

import controlador.ControladorJuego;

import java.util.Scanner;

public class PantallaMejoraPersonaje {
    private ControladorJuego controlador;

    public PantallaMejoraPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrarOpcionesMejora() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué deseas mejorar?");
        System.out.println("1. Mejorar Ataque");
        System.out.println("2. Mejorar Defensa");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            controlador.mejorarAtaque();
        } else if (opcion == 2) {
            controlador.mejorarDefensa();
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
