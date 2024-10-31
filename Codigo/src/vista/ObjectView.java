package vista;

import java.util.HashMap;
import java.util.Map;

public class ObjectView {
    private Map<String, Object> data;

    public ObjectView() {
        data = new HashMap<>();
    }

    // Método para agregar datos al ObjectView
    public void add(String key, Object value) {
        data.put(key, value);
    }

    // Método para obtener datos del ObjectView
    public Object get(String key) {
        return data.get(key);
    }

    // Método para obtener todos los datos (opcional)
    public Map<String, Object> getAllData() {
        return data;
    }
}
