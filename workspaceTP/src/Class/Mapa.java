package Class;

public class Mapa {
	
	private Ubicacion[][] ubicaciones;
	
	public Mapa() {
		
		for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ubicaciones[i][j] = new Ubicacion();
                // Aca vamos a establecer valores específicos para cada ubicación
                // ubicaciones[i][j].setTieneCriatura(true);
                // ubicaciones[i][j].setExplorado(false);
            }
        }
	}
	
	public void mostrarMapa() {
		
	}
	
	public void viajar(Ubicacion ubicacion) {
		
	}
	
}
