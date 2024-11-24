package modelo.mapa;

import modelo.criatura.Criatura;

public class Mapa {
    private Ubicacion[][] mapa;
    private final int FILAS = 10;
    private final int COLUMNAS = 10;

    public Mapa() {
        mapa = new Ubicacion[FILAS][COLUMNAS];
        inicializarMapa();
    }

    private void inicializarMapa() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // Inicialmente creamos ubicaciones normales (no neutrales) con coordenadas
                mapa[i][j] = new Ubicacion("Ubicación " + i + "," + j, false, i, j);
            }
        }
    }

    public void establecerUbicacionConTesoro(int fila, int columna) {
        mapa[fila][columna].setTesoro(true);
    }

    public void establecerUbicacionConCriatura(int fila, int columna, Criatura criatura) {
        mapa[fila][columna].setCriatura(criatura);
    }

    public void establecerUbicacionNeutral(int fila, int columna) {
        Ubicacion ubicacion = mapa[fila][columna];

        if (ubicacion == null) {
            // Si la ubicación aún no existe, creamos una nueva ubicación neutral
            mapa[fila][columna] = new Ubicacion("Ubicación Neutral " + fila + "," + columna, true, fila, columna);
        } else {
            // Si ya existe, simplemente la marcamos como neutral
            ubicacion.setNeutral(true); // Asegúrate de tener un método setNeutral en Ubicacion
        }
    }
    
    public void establecerUbicacionConNombre(int fila, int columna, String nombre) {
        Ubicacion ubicacion = mapa[fila][columna];
        if (ubicacion != null) {
            ubicacion.setNombre(nombre); // Cambia el nombre de la ubicación
        }
    }

    public Ubicacion obtenerUbicacion(int fila, int columna) {
        return mapa[fila][columna];
    }

    public String obtenerRepresentacionTexto() {
        StringBuilder mapaTexto = new StringBuilder("Mapa del Reino Encantado (10x10):\n");
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                mapaTexto.append(mapa[i][j].getNombre()).append(" ");
            }
            mapaTexto.append("\n");
        }
        return mapaTexto.toString();
    }
    
    public void mostrarMapa() {
        System.out.println("Mapa del Reino Encantado (10x10):");
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println(); // Salto de línea para cada fila
        }
    }
    
    public boolean esUbicacionConTesoro(int fila, int columna) {
        return mapa[fila][columna].tieneTesoro();
    }
    
    public boolean esUbicacionVisitada(int fila, int columna) {
        return mapa[fila][columna].esVisitada();
    }
    
}
