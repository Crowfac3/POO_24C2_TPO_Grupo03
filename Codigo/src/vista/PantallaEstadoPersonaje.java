package vista;

import javax.swing.*;
import java.awt.*;
import controlador.ControladorJuego;
import modelo.objectViews.PersonajeView;

public class PantallaEstadoPersonaje extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorJuego controlador;

    public PantallaEstadoPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Estado del Personaje");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de atributos del personaje (Izquierda)
        JPanel panelAtributos = new JPanel();
        panelAtributos.setLayout(new BoxLayout(panelAtributos, BoxLayout.Y_AXIS));
        panelAtributos.setBorder(BorderFactory.createTitledBorder("Atributos del Personaje"));

        if (controlador.getPersonaje() == null) {
            JOptionPane.showMessageDialog(this, "El personaje no ha sido inicializado. Por favor, selecciona un personaje primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PersonajeView estadoPersonaje = controlador.obtenerEstadoPersonaje();

        JLabel labelNombre = new JLabel("Nombre: " + estadoPersonaje.getNombre());
        JLabel labelPuntosVida = new JLabel("Puntos de Vida: " + estadoPersonaje.getPuntosVida());
        JLabel labelAtaque = new JLabel("Ataque: " + estadoPersonaje.getNivelAtaque());
        JLabel labelDefensa = new JLabel("Defensa: " + estadoPersonaje.getNivelDefensa());

        panelAtributos.add(labelNombre);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelPuntosVida);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelAtaque);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelDefensa);

        // Botón para misiones (debajo de los atributos)
        JButton botonMisiones = new JButton("Misiones");
        botonMisiones.addActionListener(e -> controlador.mostrarMisiones());
        panelAtributos.add(Box.createVerticalStrut(20));
        panelAtributos.add(botonMisiones);

        // Panel de imagen del personaje (Centro)
        JPanel panelImagen = new JPanel();
        panelImagen.setBorder(BorderFactory.createTitledBorder("Imagen del Personaje"));
        ImageIcon iconoOriginal = new ImageIcon("src/resources/guerrero.png");
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(300, 500, Image.SCALE_SMOOTH); // Cambia el tamaño de la imagen

        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(iconoRedimensionado);
        panelImagen.add(labelImagen);

        // Panel del mapa (Derecha)
        JPanel panelMapa = new JPanel();
        panelMapa.setLayout(new BorderLayout());
        panelMapa.setBorder(BorderFactory.createTitledBorder("Mapa del Reino"));

        JPanel matrizMapa = new JPanel(new GridLayout(8, 8)); // Reducido a 8x8 para hacer el mapa más pequeño
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton botonUbicacion = new JButton(i + "," + j);
                botonUbicacion.setEnabled(false); // Puedes habilitarlo según la lógica de juego
                matrizMapa.add(botonUbicacion);
            }
        }
        panelMapa.add(matrizMapa, BorderLayout.CENTER);

        // Panel de control para viajar a una ubicación específica (abajo del mapa)
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridLayout(2, 3, 10, 10));
        JLabel labelFila = new JLabel("Fila:");
        JTextField campoFila = new JTextField();
        JLabel labelColumna = new JLabel("Columna:");
        JTextField campoColumna = new JTextField();
        JButton botonViajar = new JButton("Viajar");

        botonViajar.addActionListener(e -> {
            try {
                int fila = Integer.parseInt(campoFila.getText());
                int columna = Integer.parseInt(campoColumna.getText());
                controlador.visitarUbicacion(fila, columna);
                JOptionPane.showMessageDialog(this, "Viajaste a la ubicación [" + fila + ", " + columna + "].", "Viaje exitoso", JOptionPane.INFORMATION_MESSAGE);
                // Actualizar la visualización del mapa después del viaje (opcional)
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para la fila y la columna.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelControl.add(labelFila);
        panelControl.add(campoFila);
        panelControl.add(labelColumna);
        panelControl.add(campoColumna);
        panelControl.add(botonViajar);

        panelMapa.add(panelControl, BorderLayout.SOUTH);

        // Agregar paneles al layout principal
        panelPrincipal.add(panelAtributos, BorderLayout.WEST);
        panelPrincipal.add(panelImagen, BorderLayout.CENTER);
        panelPrincipal.add(panelMapa, BorderLayout.EAST);

        add(panelPrincipal);
    }

    public void mostrar() {
        setVisible(true);
    }
}
