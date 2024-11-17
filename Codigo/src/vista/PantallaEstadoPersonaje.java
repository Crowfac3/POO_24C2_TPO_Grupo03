package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import controlador.ControladorJuego;
import modelo.objectViews.PersonajeView;
import java.io.File;
import java.io.IOException;

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
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Intentar cargar la fuente personalizada
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/UncialAntiqua-Regular.ttf")).deriveFont(18f); // Ajusta el tamaño de la fuente
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font = new Font("Serif", Font.PLAIN, 18); // Fuente de reserva si la fuente personalizada falla
        }

        // Layout principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de atributos del personaje (Izquierda)
        JPanel panelAtributos = new JPanel();
        panelAtributos.setLayout(new BoxLayout(panelAtributos, BoxLayout.Y_AXIS));
        TitledBorder tituloAtributos = BorderFactory.createTitledBorder("Atributos del Personaje");
        tituloAtributos.setTitleFont(font); // Aplicar la fuente personalizada al título
        panelAtributos.setBorder(tituloAtributos);

        if (controlador.getPersonaje() == null) {
            JOptionPane.showMessageDialog(this, "El personaje no ha sido inicializado. Por favor, selecciona un personaje primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PersonajeView estadoPersonaje = controlador.obtenerEstadoPersonaje();

        JLabel labelNombre = new JLabel("Nombre: " + estadoPersonaje.getNombre());
        JLabel labelPuntosVida = new JLabel("Puntos de Vida: " + estadoPersonaje.getPuntosVida());
        JLabel labelAtaque = new JLabel("Ataque: " + estadoPersonaje.getNivelAtaque());
        JLabel labelDefensa = new JLabel("Defensa: " + estadoPersonaje.getNivelDefensa());
        
        labelNombre.setFont(font);
        labelPuntosVida.setFont(font);
        labelAtaque.setFont(font);
        labelDefensa.setFont(font);

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
        TitledBorder tituloImagen = BorderFactory.createTitledBorder("Imagen del Personaje");
        tituloImagen.setTitleFont(font); // Aplicar la fuente personalizada al título
        panelImagen.setBorder(tituloImagen);
        
        
        ImageIcon iconoOriginal = new ImageIcon("src/resources/guerrero.png");
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(300, 500, Image.SCALE_SMOOTH); // Cambia el tamaño de la imagen

        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(iconoRedimensionado);
        panelImagen.add(labelImagen);

        // Panel del mapa (Derecha)
        JPanel panelMapa = new JPanel(new BorderLayout());
        TitledBorder tituloMapa = BorderFactory.createTitledBorder("Mapa del Reino");
        tituloMapa.setTitleFont(font);
        panelMapa.setBorder(tituloMapa);
        
        
        JLabel labelUbicacionActual = new JLabel("Ubicación actual: [0, 0]");
        labelUbicacionActual.setFont(font); // Aplicar fuente personalizada
        labelUbicacionActual.setHorizontalAlignment(SwingConstants.CENTER);

        
        // Crear la matriz del mapa
        JPanel matrizMapa = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // Evita que los botones se expandan
        gbc.insets = new Insets(2, 2, 2, 2); // Margen entre botones

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton botonUbicacion = new JButton();
                botonUbicacion.setPreferredSize(new Dimension(30, 30)); // Tamaño deseado del botón
                botonUbicacion.setBackground(Color.LIGHT_GRAY); // Color inicial del botón
                final int fila = i;
                final int columna = j;

                botonUbicacion.addActionListener(e -> {
                    // Llamar al método visitarUbicacion del controlador
                    controlador.visitarUbicacion(fila, columna);

                    // Cambiar el color del botón al ser visitado
                    botonUbicacion.setBackground(Color.GREEN); // Color para ubicaciones visitadas

                    // Actualizar etiqueta de ubicación actual
                    labelUbicacionActual.setText("Ubicación actual: [" + fila + ", " + columna + "]");

                    // Verificar si hay criatura y preguntar si desea combatir
                    if (controlador.hayCriaturaEnUbicacion(fila, columna)) {
                        int respuesta = JOptionPane.showConfirmDialog(this, 
                            "Hay una criatura en esta ubicación. ¿Deseas pelear?", 
                            "Criatura encontrada", 
                            JOptionPane.YES_NO_OPTION);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            controlador.iniciarPelea(fila, columna);
                        }
                    }
                });

                // Posición en el grid
                gbc.gridx = j;
                gbc.gridy = i;
                matrizMapa.add(botonUbicacion, gbc);
            }
        }
        
        
        // Crear un panel contenedor para la matriz y la etiqueta
        JPanel contenedorMapa = new JPanel(new BorderLayout());
        contenedorMapa.add(matrizMapa, BorderLayout.CENTER); // Matriz en el centro
        contenedorMapa.add(labelUbicacionActual, BorderLayout.SOUTH); // Etiqueta en la parte inferior
        
        
        // Añadir el contenedor completo al panel del mapa
        panelMapa.add(contenedorMapa, BorderLayout.CENTER);

        
        // Ajustar dimensiones generales del mapa
        panelMapa.setPreferredSize(new Dimension(350, 350)); // Ajustar ancho y alto del panel completo

        
        // Ajustar el ancho del panel de atributos
        panelAtributos.setPreferredSize(new Dimension(300, 0)); // Ajusta el ancho deseado

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
