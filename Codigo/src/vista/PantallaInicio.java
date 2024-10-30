package vista;

import java.util.Scanner;
import controlador.ControladorJuego;

public class PantallaInicio {
    private ControladorJuego controlador;
    
    public PantallaInicio(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al Reino Encantado. Selecciona tu personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Arquero");
        int eleccion = scanner.nextInt();
        
        switch (eleccion) {
            case 1:
                controlador.crearPersonaje("guerrero");
                break;
            case 2:
                controlador.crearPersonaje("mago");
                break;
            case 3:
                controlador.crearPersonaje("arquero");
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                mostrar();
        }
    }
}
