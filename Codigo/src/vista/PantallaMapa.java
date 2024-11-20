package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import controlador.ControladorJuego;

public class PantallaMapa extends JFrame {
    private static final long serialVersionUID = 1L;
    private ControladorJuego controlador;

    private JPanel matrizMapa; // Panel que contiene la matriz de botones
    private JLabel labelUbicacionActual; // Etiqueta para mostrar la ubicación actual

    public PantallaMapa(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Mapa del Reino");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Intentar cargar la fuente personalizada
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/UncialAntiqua-Regular.ttf"))
                    .deriveFont(18f); // Ajusta el tamaño de la fuente
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font = new Font("Serif", Font.PLAIN, 18); // Fuente de reserva si la fuente personalizada falla
        }

        JPanel panelMapa = new JPanel(new BorderLayout());
        TitledBorder tituloMapa = BorderFactory.createTitledBorder("Mapa del Reino");
        tituloMapa.setTitleFont(font);
        panelMapa.setBorder(tituloMapa);

        // Etiqueta de ubicación actual
        int[] ubicacionActual = controlador.obtenerUbicacionActualJugador();
        labelUbicacionActual = new JLabel("Ubicación actual: [" + ubicacionActual[0] + ", " + ubicacionActual[1] + "]");

        labelUbicacionActual.setFont(font);
        labelUbicacionActual.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear la matriz de botones del mapa
        matrizMapa = new JPanel(new GridBagLayout());
        actualizarMatrizMapa(); // Método que genera y agrega los botones al panel de matriz

        

        // Botón para volver al estado del personaje
        JButton botonVolver = new JButton("Volver a Personaje");
        botonVolver.addActionListener(e -> {
            controlador.volverAPersonaje();
            dispose(); // Cerrar la pantalla actual de mapa
        });

        // Contenedor para la matriz y los botones
        JPanel contenedorMapa = new JPanel(new BorderLayout());
        contenedorMapa.add(matrizMapa, BorderLayout.CENTER);
        contenedorMapa.add(labelUbicacionActual, BorderLayout.SOUTH);

        // Contenedor inferior con los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonVolver);

        // Añadir los paneles al panel principal
        panelMapa.add(contenedorMapa, BorderLayout.CENTER);
        panelMapa.add(panelBotones, BorderLayout.SOUTH);

        add(panelMapa);
    }

    private void actualizarMatrizMapa() {
        matrizMapa.removeAll(); // Eliminar todos los componentes actuales del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(2, 2, 2, 2);
        
        int[] ubicacionActual = controlador.obtenerUbicacionActualJugador(); // Obtener la ubicación actual del jugador

        
        // Cargar los íconos
        ImageIcon iconOriginalCasa = new ImageIcon("src/resources/house.png"); // Ruta al icono de la casa
        Image iconEscaladaCasa = iconOriginalCasa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconCasa = new ImageIcon(iconEscaladaCasa);
        ImageIcon iconOriginalEspada = new ImageIcon("src/resources/sword.png"); // Ruta al icono de la espada
        Image iconEscaladaEspada = iconOriginalEspada.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconEspada = new ImageIcon(iconEscaladaEspada);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton botonUbicacion = new JButton();
                botonUbicacion.setPreferredSize(new Dimension(30, 30));
                
                

                // Cambiar color si es la ubicación inicial
                if (i == ubicacionActual[0] && j == ubicacionActual[1]) {
                    botonUbicacion.setBackground(Color.CYAN); // Celeste claro para ubicación inicial
                } else if (controlador.esUbicacionNeutral(i, j)) {
                    botonUbicacion.setBackground(Color.GREEN); // Verde para ubicaciones neutrales
                    botonUbicacion.setIcon(iconCasa);
                } else if (controlador.hayCriaturaEnUbicacion(i, j)) {
                    botonUbicacion.setBackground(Color.RED); // Rojo para ubicaciones con criaturas
                    botonUbicacion.setIcon(iconEspada); 
                } else {
                    botonUbicacion.setBackground(null); // Sin color predeterminado
                }
                
                // Determinar si la ubicación es adyacente o fue visitada
                boolean esAdyacente = Math.abs(ubicacionActual[0] - i) <= 1 && Math.abs(ubicacionActual[1] - j) <= 1;
                boolean esVisitada = controlador.esUbicacionVisitada(i, j);

                // Habilitar/Deshabilitar el botón según si es adyacente o visitada
                botonUbicacion.setEnabled(esAdyacente || esVisitada);
                
                // Habilitar/Deshabilitar el botón según si es adyacente
                if (esAdyacente || esVisitada) {
                    botonUbicacion.setEnabled(true);
                } else {
                    botonUbicacion.setBackground(Color.DARK_GRAY); // Oscuro para ubicaciones no habilitadas
                    botonUbicacion.setIcon(null); // Eliminar el ícono para ubicaciones deshabilitadas
                    botonUbicacion.setEnabled(false); // Deshabilitar el botón
                }

                final int fila = i;
                final int columna = j;

                botonUbicacion.addActionListener(e -> {
                    controlador.visitarUbicacion(fila, columna);
                    
                    // Actualizar la matriz después del movimiento
                    actualizarMatrizMapa();
                   
                    // Actualizar el color después de visitar
                    if (!controlador.esUbicacionNeutral(fila, columna)) {
                        botonUbicacion.setBackground(Color.LIGHT_GRAY); // Gris claro para ubicaciones no especiales
                    }
                    
                    // Obtener la nueva ubicación actual del jugador
                    int[] nuevaUbicacion = controlador.obtenerUbicacionActualJugador();
                    labelUbicacionActual.setText("Ubicación actual: [" + nuevaUbicacion[0] + ", " + nuevaUbicacion[1] + "]");
                    

                    // Verificar si hay criatura y preguntar si desea combatir
                    if (controlador.hayCriaturaEnUbicacion(fila, columna) && controlador.estaCriaturaVivaEnUbicacion(fila, columna)) {
                        int respuesta = JOptionPane.showConfirmDialog(this,
                                "Hay una criatura en esta ubicación. ¿Deseas pelear?",
                                "Criatura encontrada", JOptionPane.YES_NO_OPTION);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            controlador.iniciarPelea(fila, columna);
                        }
                    }
                });

                // Posicionar el botón en la matriz
                gbc.gridx = j;
                gbc.gridy = i;
                matrizMapa.add(botonUbicacion, gbc);
            }
        }

        matrizMapa.revalidate(); // Actualizar el layout del panel
        matrizMapa.repaint(); // Redibujar el panel
    }

    public void mostrar() {
        setVisible(true);
    }
}
