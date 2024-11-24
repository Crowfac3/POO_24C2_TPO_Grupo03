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
	
	// Declarar las etiquetas como atributos de la clase
    private JLabel labelNombre;
    private JLabel labelPuntosVida;
    private JLabel labelAtaque;
    private JLabel labelDefensa;
    private JLabel labelExperiencia;
    private JButton botonMejoras;

    public PantallaEstadoPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Estado del Personaje");
        setSize(800, 600);
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

        labelNombre = new JLabel("Nombre: " + estadoPersonaje.getNombre());
        labelPuntosVida = new JLabel("Puntos de Vida: " + estadoPersonaje.getPuntosVida());
        labelAtaque = new JLabel("Ataque: " + estadoPersonaje.getNivelAtaque());
        labelDefensa = new JLabel("Defensa: " + estadoPersonaje.getNivelDefensa());
        labelExperiencia = new JLabel("Experiencia: " + controlador.getPersonaje().getExperiencia()); 

        
        labelNombre.setFont(font);
        labelPuntosVida.setFont(font);
        labelAtaque.setFont(font);
        labelDefensa.setFont(font);
        labelExperiencia.setFont(font);

        panelAtributos.add(labelNombre);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelPuntosVida);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelAtaque);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelDefensa);
        panelAtributos.add(Box.createVerticalStrut(10));
        panelAtributos.add(labelExperiencia);

        // Botón para misiones (debajo de los atributos)
        JButton botonMisiones = new JButton("Misiones");
        botonMisiones.addActionListener(e -> controlador.mostrarMisiones());
        panelAtributos.add(Box.createVerticalStrut(20));
        panelAtributos.add(botonMisiones);
        
        
        // Botón para abrir el mapa
        JButton botonMapa = new JButton("Mapa");
        botonMapa.addActionListener(e -> abrirPantallaMapa());
        panelAtributos.add(Box.createVerticalStrut(20));
        panelAtributos.add(botonMapa);
        
        // Botón para abrir mejoras
        botonMejoras = new JButton("Campamento");
        botonMejoras.setEnabled(false); // Inicialmente deshabilitado
        botonMejoras.addActionListener(e -> {
        	PantallaMejoraPersonaje pantallaMejora = new PantallaMejoraPersonaje(controlador, this);
        	pantallaMejora.mostrar();
        });
        
        panelAtributos.add(Box.createVerticalStrut(20));
        panelAtributos.add(botonMejoras);
        
        

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

        
        
        // Ajustar el ancho del panel de atributos
        panelAtributos.setPreferredSize(new Dimension(300, 0)); // Ajusta el ancho deseado

        // Agregar paneles al layout principal
        panelPrincipal.add(panelAtributos, BorderLayout.WEST);
        panelPrincipal.add(panelImagen, BorderLayout.CENTER);
       
        add(panelPrincipal);
        
        
    }
    
    
    private void abrirPantallaMapa() {
        controlador.abrirPantallaMapa();
        dispose();
    }

    public void mostrar() {
        setVisible(true);
    }
    
    public void actualizar() {
        // Recuperar el estado del personaje desde el controlador
        PersonajeView estadoPersonaje = controlador.obtenerEstadoPersonaje();

        // Actualizar las etiquetas con los nuevos valores
        labelNombre.setText("Nombre: " + estadoPersonaje.getNombre());
        labelPuntosVida.setText("Puntos de Vida: " + estadoPersonaje.getPuntosVida());
        labelAtaque.setText("Ataque: " + estadoPersonaje.getNivelAtaque());
        labelDefensa.setText("Defensa: " + estadoPersonaje.getNivelDefensa());
        labelExperiencia.setText("Experiencia: " + controlador.getPersonaje().getExperiencia());
        
        
        // Habilitar o deshabilitar el botón de mejoras según la ubicación
        boolean enUbicacionNeutral = controlador.estaEnUbicacionNeutral();
        botonMejoras.setEnabled(enUbicacionNeutral);


        // Forzar la actualización visual
        revalidate();
        repaint();
    }
    
}
