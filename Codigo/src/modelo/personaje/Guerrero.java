package modelo.personaje;

import modelo.criatura.Criatura;
import modelo.criatura.Troll;

public class Guerrero extends Personaje {
    private int contadorAtaques;

    public Guerrero(String nombre, int puntosVida, int nivelAtaque, int nivelDefensa) {
        super(nombre, puntosVida, nivelAtaque, nivelDefensa);
        this.contadorAtaques = 0;
    }

    @Override
    public void atacar(Criatura criatura) {
        contadorAtaques++;
        int ataque = nivelAtaque;
        if (contadorAtaques >= 3) {
            ataque *= 2; // Duplica el poder de ataque a partir del tercer golpe
        }
        if (criatura instanceof Troll) {
            criatura.recibirGolpe(criatura.getPuntosVida()); // Derriba al Troll de un solo golpe
        } else {
            criatura.recibirGolpe(ataque);
        }
    }

    // Nuevo método para reiniciar el contador de ataques después de la pelea
    public void reiniciarContadorAtaques() {
        this.contadorAtaques = 0;
    }
}
