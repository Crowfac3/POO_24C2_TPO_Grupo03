package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ControladorJuego;

public class PantallaEstadoPersonaje extends JFrame {
    private ControladorJuego controlador;

    public PantallaEstadoPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Estado del Personaje");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        if (controlador.getPersonaje() == null) {
            JOptionPane.showMessageDialog(this, "El personaje no ha sido inicializado. Por favor, selecciona un personaje primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ObjectView estadoPersonaje = controlador.obtenerEstadoPersonaje();

        JLabel labelNombre = new JLabel("Nombre: " + estadoPersonaje.get("Nombre"));
        JLabel labelPuntosVida = new JLabel("Puntos de Vida: " + estadoPersonaje.get("Puntos de Vida"));
        JLabel labelAtaque = new JLabel("Ataque: " + estadoPersonaje.get("Ataque"));
        JLabel labelDefensa = new JLabel("Defensa: " + estadoPersonaje.get("Defensa"));

        panel.add(labelNombre);
        panel.add(labelPuntosVida);
        panel.add(labelAtaque);
        panel.add(labelDefensa);

        // Botón para ir al mapa
        JButton botonMapa = new JButton("Ir al Mapa");
        botonMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirPantallaMapa();  // Llamar al controlador para abrir la pantalla del mapa
                dispose();  // Cerrar la ventana actual
            }
        });
        panel.add(botonMapa);

        add(panel);
    }

    public void mostrar() {
        setVisible(true);
    }
}