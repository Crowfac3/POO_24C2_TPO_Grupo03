package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaFinJuego extends JFrame {
    private static final long serialVersionUID = 1L;

    public void mostrarFinDelJuego(boolean victoria) {
        setTitle("Fin del Juego");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de mensaje
        JLabel mensaje = new JLabel(
            victoria ? "¡Felicidades! Has encontrado el tesoro y ganado el juego."
                     : "El jugador ha sido derrotado. Fin del juego.",
            SwingConstants.CENTER
        );
        mensaje.setFont(mensaje.getFont().deriveFont(16f));
        panelPrincipal.add(mensaje, BorderLayout.CENTER);

        // Botón "Fin"
        JButton botonFin = new JButton("Fin");
        botonFin.setFont(botonFin.getFont().deriveFont(14f));
        botonFin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar el programa
            }
        });

        // Panel para centrar el botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(botonFin);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        // Agregar el panel principal al marco
        add(panelPrincipal);

        // Hacer la ventana visible
        setVisible(true);
    }
}
