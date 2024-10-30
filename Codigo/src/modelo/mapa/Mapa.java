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
                // Inicialmente creamos ubicaciones normales (no neutrales)
                mapa[i][j] = new Ubicacion("Ubicación " + i + "," + j, false);
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
        mapa[fila][columna] = new Ubicacion("Ubicación Neutral " + fila + "," + columna, true);
    }

    public Ubicacion obtenerUbicacion(int fila, int columna) {
        return mapa[fila][columna];
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
}
