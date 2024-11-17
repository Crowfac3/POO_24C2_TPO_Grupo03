package vista;

import javax.swing.*;
import java.awt.*;
import controlador.ControladorJuego;

public class PantallaCombate extends JFrame {
    private static final long serialVersionUID = 1L;

    private ControladorJuego controlador;
    private JLabel labelVidaPersonaje;
    private JLabel labelVidaCriatura;
    private JTextArea textAreaCombate;

    public PantallaCombate(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
        iniciarCicloCombate();
    }

    private void initUI() {
        setTitle("Combate");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de información
        JPanel panelInfo = new JPanel(new GridLayout(2, 2));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Estado del Combate"));

        // Etiquetas de vida
        labelVidaPersonaje = new JLabel("Vida Personaje: " + controlador.getVidaPersonaje());
        labelVidaCriatura = new JLabel("Vida Criatura: " + controlador.getVidaCriatura());

        panelInfo.add(labelVidaPersonaje);
        panelInfo.add(labelVidaCriatura);

        // Registro de combate
        textAreaCombate = new JTextArea();
        textAreaCombate.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaCombate);

        // Añadir paneles
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void iniciarCicloCombate() {
        new Thread(() -> {
            while (controlador.getVidaPersonaje() > 0 && controlador.getVidaCriatura() > 0) {
                // Ejecutar el turno de combate
                controlador.ejecutarTurnoDeCombate();
                SwingUtilities.invokeLater(() -> actualizarInterfaz());

                // Pausa entre turnos
                esperar(1000);
            }

            // Mostrar resultado final
            SwingUtilities.invokeLater(() -> {
                if (controlador.ganoPersonaje()) {
                    mostrarResultado("Victoria", "Has derrotado a la criatura.");
                } else {
                    mostrarResultado("Derrota", "Has sido derrotado por la criatura.");
                }
            });
        }).start();
    }

    private void actualizarInterfaz() {
        labelVidaPersonaje.setText("Vida Personaje: " + controlador.getVidaPersonaje());
        labelVidaCriatura.setText("Vida Criatura: " + controlador.getVidaCriatura());
        textAreaCombate.append("Turno completado.\n");
    }

    private void mostrarResultado(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Cerrar la pantalla
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
