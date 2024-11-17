package modelo.personaje;

import modelo.criatura.Criatura;
import modelo.criatura.Dragon;

public class Arquero extends Personaje {
    private int punteria; // Valor entre 0 y 100 que representa la probabilidad de acertar
    private int agilidad; // Valor entre 0 y 100 que representa la probabilidad de esquivar golpes

    public Arquero(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa, int punteria, int agilidad) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
        this.punteria = punteria;
        this.agilidad = agilidad;
    }

    @Override
    public void atacar(Criatura criatura) {
        if (criatura instanceof Dragon) {
            System.out.println("El Arquero tiene un 100% de puntería contra Dragones.");
            criatura.recibirGolpe(nivelAtaque); // El arquero tiene un ataque perfecto contra Dragones
        } else {
            int golpeExitoso = (int) (Math.random() * 100); // Probabilidad de acierto basada en la puntería
            if (golpeExitoso < punteria) {
                criatura.recibirGolpe(nivelAtaque);
                System.out.println("El Arquero ha acertado su ataque.");
            } else {
                System.out.println("El Arquero ha fallado su ataque.");
            }
        }
    }

    @Override
    public void recibirGolpe(int danio) {
        int esquivar = (int) (Math.random() * 100); // Probabilidad de esquivar basada en la agilidad
        if (esquivar < agilidad) {
            System.out.println("El Arquero ha esquivado el golpe.");
        } else {
            super.recibirGolpe(danio);
        }
    }
}
