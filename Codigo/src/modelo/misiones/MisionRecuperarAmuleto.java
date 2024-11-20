package modelo.misiones;

import modelo.personaje.Personaje;

public class MisionRecuperarAmuleto extends Mision {

    public MisionRecuperarAmuleto() {
        super("Recupera el Amuleto Perdido", "Encontrar el Amuleto Perdido en el Bosque de los Susurros.", "Amuleto de Protección (+15% defensa)");
    }

    @Override
    public void completar() {
        if (!recompensaReclamada) { // Verifica si la recompensa ya fue reclamada
            completada = true;
            System.out.println("¡Has encontrado el Amuleto Perdido!");
        } else {
            System.out.println("Ya has completado esta misión y reclamado la recompensa.");
        }
    }

    @Override
    protected void ejecutarRecompensa(Personaje personaje) {
        personaje.mejorar(0, 15); // Aumenta un 15% la defensa
        System.out.println("Tu defensa ha aumentado en un 15% gracias al Amuleto de Protección.");
    }
}
