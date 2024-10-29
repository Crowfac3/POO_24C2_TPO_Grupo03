package Personaje;

import Criatura.Criatura;
import Criatura.Espectro;

public class Mago extends Personaje {

    public Mago(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
    }

    @Override
    public void atacar(Criatura criatura) {
        if (!(criatura instanceof Espectro)) {
            int danio = nivelAtaque - criatura.getNivelDefensa();
            criatura.recibirGolpe(danio);
        } else {
            System.out.println("El Mago es inmune a los Espectros y no recibe daño.");
        }
    }

    public void curarse() {
        this.puntosVida = 100; // Cura su vida al 100% al finalizar una pelea
        System.out.println("El Mago ha curado su vida al 100%.");
    }
}
