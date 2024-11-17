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
        setTitle("Reino Encantado - Selección de Personaje");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        
        JLabel labelBienvenida = new JLabel("Bienvenido al Reino Encantado. Selecciona tu personaje:");
        labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnGuerrero = new JButton("Guerrero");
        JButton btnMago = new JButton("Mago");
        JButton btnArquero = new JButton("Arquero");
        
        btnGuerrero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPersonaje("Guerrero");
                verificarYMostrarPantallaEstadoPersonaje();
            }
        });
        
        btnMago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPersonaje("Mago");
                verificarYMostrarPantallaEstadoPersonaje();
            }
        });
        
        btnArquero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPersonaje("Arquero");
                verificarYMostrarPantallaEstadoPersonaje();
            }
        });
        
        panel.add(labelBienvenida);
        panel.add(btnGuerrero);
        panel.add(btnMago);
        panel.add(btnArquero);
        
        add(panel);
    }
    
    private void verificarYMostrarPantallaEstadoPersonaje() {
        if (controlador.getPersonaje() != null) {
            PantallaEstadoPersonaje pantallaEstado = new PantallaEstadoPersonaje(controlador);
            pantallaEstado.setVisible(true);  // Cambiado a setVisible(true) en lugar de mostrar()
            dispose(); // Cierra la ventana actual (PantallaInicio)
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el personaje. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrar() {
        setVisible(true);
    }
}
