package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ControladorJuego;

public class PantallaInicio extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorJuego controlador;
    
    public PantallaInicio(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }
    
    private void initUI() {
        setTitle("Reino Encantado - Seleccion de Personaje");
        setSize(650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar el programa al hacer clic en la "X"
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        
        JLabel labelBienvenida = new JLabel("Bienvenido al Reino Encantado. Ingresa tu nombre y selecciona tu personaje:");
        labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        labelBienvenida.setFont(new Font("Serif", Font.BOLD, 16));
        labelBienvenida.setForeground(new Color(60, 60, 60));
        labelBienvenida.setPreferredSize(new Dimension(400, 50));
        
        JTextField campoNombre = new JTextField();
        campoNombre.setFont(new Font("Serif", Font.PLAIN, 14));
        campoNombre.setPreferredSize(new Dimension(250, 25));
        
        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNombre.setBackground(new Color(240, 240, 240));
        panelNombre.add(campoNombre);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        panelBotones.setBackground(new Color(240, 240, 240));
        
        JButton btnGuerrero = new JButton("Guerrero");
        JButton btnMago = new JButton("Mago");
        JButton btnArquero = new JButton("Arquero");
        
        btnGuerrero.setFont(new Font("Serif", Font.BOLD, 14));
        btnGuerrero.setBackground(new Color(200, 200, 255));
        btnGuerrero.setFocusPainted(false);
        
        btnMago.setFont(new Font("Serif", Font.BOLD, 14));
        btnMago.setBackground(new Color(200, 255, 200));
        btnMago.setFocusPainted(false);
        
        btnArquero.setFont(new Font("Serif", Font.BOLD, 14));
        btnArquero.setBackground(new Color(255, 200, 200));
        btnArquero.setFocusPainted(false);
        
        btnGuerrero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(PantallaInicio.this, "Por favor, ingresa tu nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    controlador.crearPersonaje(nombre, "guerrero");
                    abrirPantallaEstadoPersonaje();
                }
            }
        });
        
        btnMago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(PantallaInicio.this, "Por favor, ingresa tu nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    controlador.crearPersonaje(nombre, "mago");
                    abrirPantallaEstadoPersonaje();
                }
            }
        });
        
        btnArquero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(PantallaInicio.this, "Por favor, ingresa tu nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    controlador.crearPersonaje(nombre, "arquero");
                    abrirPantallaEstadoPersonaje();
                }
            }
        });
        
        panelBotones.add(btnGuerrero);
        panelBotones.add(btnMago);
        panelBotones.add(btnArquero);
        
        panel.add(labelBienvenida, BorderLayout.NORTH);
        panel.add(panelNombre, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private void abrirPantallaEstadoPersonaje() {
        PantallaEstadoPersonaje pantallaEstado = new PantallaEstadoPersonaje(controlador);
        pantallaEstado.mostrar();
        dispose(); // Cierra la ventana actual (PantallaInicio)
    }
    
    public void mostrar() {
        setVisible(true);
    }
}