package modelo.misiones;

public class MisionRecuperarAmuleto extends Mision {

    public MisionRecuperarAmuleto() {
        super("Recupera el Amuleto Perdido", "Encontrar el Amuleto Perdido en el Bosque de los Susurros.", "Amuleto de Protección (+15% defensa)");
    }

    @Override
    public void completar() {
        completada = true;
        System.out.println("¡Has encontrado el Amuleto Perdido!");
    }

    @Override
    public void aplicarRecompensa(modelo.personaje.Personaje personaje) {
        if (completada) {
            personaje.mejorar(0, 15); // Aumenta un 15% la defensa
            System.out.println("Tu defensa ha aumentado en un 15% gracias al Amuleto de Protección.");
        }
    }
}
