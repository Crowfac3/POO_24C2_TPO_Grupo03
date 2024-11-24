package vista;

import javax.swing.*;
import java.awt.*;

import controlador.ControladorJuego;
import modelo.objectViews.PersonajeView;

public class PantallaMejoraPersonaje extends JFrame {
    private static final long serialVersionUID = 1L;
    private ControladorJuego controlador;
    private PantallaEstadoPersonaje pantallaEstadoPersonaje;

    public PantallaMejoraPersonaje(ControladorJuego controlador, PantallaEstadoPersonaje pantallaEstadoPersonaje) {
        this.controlador = controlador;
        this.pantallaEstadoPersonaje = pantallaEstadoPersonaje;
        initUI();
    }

    private void initUI() {
        setTitle("Mejora de Personaje");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Mejorar habilidades del personaje", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Panel central con opciones de mejora
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));

        PersonajeView personaje = controlador.obtenerEstadoPersonaje();
        JLabel labelExperiencia = new JLabel("Experiencia disponible: " + personaje.getExperiencia());
        labelExperiencia.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelOpciones.add(labelExperiencia);

        JButton botonMejorarAtaque = new JButton("Mejorar Ataque (+10) - 50 Exp");
        botonMejorarAtaque.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMejorarAtaque.addActionListener(e -> {
            if (controlador.mejorarAtaque()) {
            	pantallaEstadoPersonaje.actualizar();
                JOptionPane.showMessageDialog(this, "¡Ataque mejorado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                labelExperiencia.setText("Experiencia disponible: " + controlador.obtenerEstadoPersonaje().getExperiencia());
            } else {
                JOptionPane.showMessageDialog(this, "No tienes suficiente experiencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton botonMejorarDefensa = new JButton("Mejorar Defensa (+5) - 30 Exp");
        botonMejorarDefensa.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMejorarDefensa.addActionListener(e -> {
            if (controlador.mejorarDefensa()) {
            	pantallaEstadoPersonaje.actualizar();
                JOptionPane.showMessageDialog(this, "¡Defensa mejorada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                labelExperiencia.setText("Experiencia disponible: " + controlador.obtenerEstadoPersonaje().getExperiencia());
            } else {
                JOptionPane.showMessageDialog(this, "No tienes suficiente experiencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton botonDescansar = new JButton("Descansar (Restaura vida al máximo)");
        botonDescansar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonDescansar.addActionListener(e -> {
            controlador.descansarPersonaje();
            JOptionPane.showMessageDialog(this, "¡Has descansado y restaurado tu vida al máximo!", "Descanso", JOptionPane.INFORMATION_MESSAGE);
            pantallaEstadoPersonaje.actualizar(); // Actualiza la pantalla del estado del personaje
        });
        
        

        panelOpciones.add(Box.createVerticalStrut(10));
        panelOpciones.add(botonMejorarAtaque);
        panelOpciones.add(Box.createVerticalStrut(10));
        panelOpciones.add(botonMejorarDefensa);
        panelOpciones.add(Box.createVerticalStrut(10));
        panelOpciones.add(botonDescansar);
        panelOpciones.add(Box.createVerticalStrut(10));


        // Botón de cierre
        JButton botonCerrar = new JButton("Volver");
        botonCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCerrar.addActionListener(e -> {
            pantallaEstadoPersonaje.actualizar(); // Actualiza la pantalla de estado del personaje
            pantallaEstadoPersonaje.mostrar();   // Asegura que se vuelva a mostrar la pantalla de estado
            dispose();                           // Cierra la pantalla de mejoras
        });

        panelOpciones.add(Box.createVerticalStrut(20));
        panelOpciones.add(botonCerrar);

        panelPrincipal.add(panelOpciones, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    public void mostrar() {
        setVisible(true);
    }
}
