package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ControladorJuego;

public class PantallaMapa extends JFrame {
    private ControladorJuego controlador;
    private JTextArea areaMapa;

    public PantallaMapa(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Mapa del Reino");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Crear área de texto para mostrar el mapa
        areaMapa = new JTextArea();
        areaMapa.setEditable(false);  // El usuario no debe editar el contenido del mapa
        areaMapa.setFont(new Font("Monospaced", Font.PLAIN, 12));  // Fuente monoespaciada para alinear mejor el texto
        actualizarMapa();  // Mostrar el mapa en el área de texto

        JScrollPane scrollPane = new JScrollPane(areaMapa);  // Scroll por si el mapa es grande
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel de control para viajar a una ubicación específica
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridLayout(3, 2, 10, 10));
        JLabel labelFila = new JLabel("Fila:");
        JTextField campoFila = new JTextField();
        JLabel labelColumna = new JLabel("Columna:");
        JTextField campoColumna = new JTextField();
        JButton botonViajar = new JButton("Viajar");

        // Añadir acción al botón "Viajar"
        botonViajar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fila = Integer.parseInt(campoFila.getText());
                    int columna = Integer.parseInt(campoColumna.getText());
                    controlador.visitarUbicacion(fila, columna);
                    JOptionPane.showMessageDialog(PantallaMapa.this, "Viajaste a la ubicación [" + fila + ", " + columna + "].", "Viaje exitoso", JOptionPane.INFORMATION_MESSAGE);
                    actualizarMapa();  // Actualizar la visualización del mapa después del viaje
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PantallaMapa.this, "Por favor, ingrese valores numéricos válidos para la fila y la columna.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelControl.add(labelFila);
        panelControl.add(campoFila);
        panelControl.add(labelColumna);
        panelControl.add(campoColumna);
        panelControl.add(botonViajar);

        panelPrincipal.add(panelControl, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void actualizarMapa() {
        // Utilizar el controlador para obtener la representación del mapa y mostrarla en el área de texto
        String mapaTexto = controlador.obtenerRepresentacionMapa();
        areaMapa.setText(mapaTexto);
    }

    public void mostrar() {
        setVisible(true);
    }
}
