package Misiones;

import Criatura.Dragon;

public class MisionDerrotarDragon extends Mision {
    private Dragon dragon;

    public MisionDerrotarDragon(Dragon dragon) {
        super("Derrota al Dragón del Norte", "Vencer al Dragón del Norte en las Montañas Heladas.", "Espada de Fuego (+20% ataque)");
        this.dragon = dragon;
    }

    @Override
    public void completar() {
        if (dragon.estaVivo()) {
            System.out.println("El dragón sigue vivo. No puedes completar la misión.");
        } else {
            completada = true;
            System.out.println("¡Has completado la misión de derrotar al Dragón del Norte y has recibido la Espada de Fuego!");
        }
    }

    @Override
    public void aplicarRecompensa(Personaje.Personaje personaje) {
        if (completada) {
            personaje.mejorar(20, 0); // Aumenta un 20% el ataque
            System.out.println("Tu ataque ha aumentado en un 20% gracias a la Espada de Fuego.");
        }
    }
}
