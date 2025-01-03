package modelo.misiones;

import modelo.personaje.Personaje;

public class MisionLimpiaAldeaTrolls extends Mision {
    private int trollsEliminados;
    private int trollsNecesarios;

    public MisionLimpiaAldeaTrolls() {
        super("Limpia la Aldea de los Trolls", "Derrotar a 3 Trolls en la Aldea de los Sirith.", "Escudo de Titanio (+30 puntos defensa)");
        this.trollsEliminados = 0;
        this.trollsNecesarios = 3;
    }

    public void incrementarTrollsEliminados() {
        trollsEliminados++;
        if (trollsEliminados >= trollsNecesarios) {
        	completada = true;
        }
    }

    @Override
    public void completar() {
        if (trollsEliminados >= trollsNecesarios) {
            completada = true;
            System.out.println("¡Has limpiado la Aldea de los Trolls y has recibido el Escudo de Titanio!");
        } else {
            System.out.println("Aún no has derrotado suficientes trolls.");
        }
    }

    @Override
    protected void ejecutarRecompensa(Personaje personaje) {
        personaje.mejorar(0, 30); // Aumenta 30 puntos de defensa
        System.out.println("Tu defensa ha aumentado en 30 puntos gracias al Escudo de Titanio.");
    }
}
