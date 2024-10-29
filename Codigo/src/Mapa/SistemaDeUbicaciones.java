package Mapa;

import java.util.HashMap;
import java.util.Map;

public class SistemaDeUbicaciones {
    private static Map<String, Ubicacion> ubicaciones = new HashMap<>();

    // Método para registrar una ubicación en el sistema
    public static void registrarUbicacion(Ubicacion ubicacion) {
        ubicaciones.put(ubicacion.getNombre(), ubicacion);
    }

    // Método para obtener una ubicación por su nombre
    public static Ubicacion getUbicacion(String nombre) {
        return ubicaciones.get(nombre);
    }
}
