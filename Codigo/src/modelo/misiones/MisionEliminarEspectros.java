package modelo.misiones;

import modelo.personaje.Personaje;

public class MisionEliminarEspectros extends Mision {
    private int espectrosEliminados;
    private int espectrosNecesarios;

    public MisionEliminarEspectros() {
        super("Elimina a los Espectros del Pantano", "Derrotar a 5 Espectros en el Pantano Oscuro.", "Arco de Luz (+25% ataque)");
        this.espectrosEliminados = 0;
        this.espectrosNecesarios = 5;
    }

    public void incrementarEspectrosEliminados() {
        espectrosEliminados++;
        if (espectrosEliminados >= espectrosNecesarios) {
            completada = true;
        }
    }

    @Override
    public void completar() {
        if (espectrosEliminados >= espectrosNecesarios) {
            completada = true;
            System.out.println("¡Has eliminado a los Espectros del Pantano y has recibido el Arco de Luz!");
        } else {
            System.out.println("Aún no has derrotado suficientes espectros.");
        }
    }

    @Override
    protected void ejecutarRecompensa(Personaje personaje) {
        personaje.mejorar(25, 0); // Aumenta un 25% el ataque
        System.out.println("Tu ataque ha aumentado en un 25% gracias al Arco de Luz.");
    }
}
