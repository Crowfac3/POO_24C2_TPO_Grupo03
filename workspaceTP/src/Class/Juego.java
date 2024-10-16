package Class;


public class Juego {
 
	private String estado; // en progreso, victoria o derrota
	private Jugador jugador;
	private Mapa mapa;
	
	// Inicializar el mapa y ubicaciones
	
	public Juego() {
		Mapa mapa = new Mapa();
	}
     
	public void inciarJuego(String nombre, TipoDeClase TipoDeClase){
		String estado = "En progreso";
		Jugador jugador = new Jugador(nombre);
	}
	
	public void realizarAccion(String accion){
		
	}
	
	public Boolean verificarVictoria(){
		
	}
	
	public Boolean verificarDerrota() {
		
	}
	
	public void SiguienteTurno() {
		
	}
	
}
