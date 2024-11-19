package vista;

import javax.swing.*;
import java.awt.*;

import controlador.ControladorJuego;

public class PantallaCombate extends JFrame {
    private static final long serialVersionUID = 1L;

    private ControladorJuego controlador;
    private JLabel labelVidaPersonaje;
    private JLabel labelDefensaPersonaje;
    private JLabel labelVidaCriatura;
    private JLabel labelDefensaCriatura;
    private JTextArea textAreaCombate;

    public PantallaCombate(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
        iniciarCicloCombate();
    }

    private void initUI() {
        setTitle("Combate");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel izquierdo: imagen y vida del personaje
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Personaje"));

        ImageIcon iconoPersonaje = new ImageIcon("src/resources/personaje.png");
        Image imagenPersonaje = iconoPersonaje.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        JLabel labelImagenPersonaje = new JLabel(new ImageIcon(imagenPersonaje));
        panelIzquierdo.add(labelImagenPersonaje, BorderLayout.CENTER);

        JPanel panelInfoPersonaje = new JPanel(new GridLayout(2, 1));
        labelVidaPersonaje = new JLabel("Vida Personaje: " + controlador.getVidaPersonaje());
        labelVidaPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelDefensaPersonaje = new JLabel("Defensa Personaje: " + controlador.getDefensaPersonaje());
        labelDefensaPersonaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfoPersonaje.add(labelVidaPersonaje);
        panelInfoPersonaje.add(labelDefensaPersonaje);
        panelIzquierdo.add(panelInfoPersonaje, BorderLayout.SOUTH);

        // Panel derecho: imagen y vida de la criatura
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Criatura"));

        ImageIcon iconoCriatura = new ImageIcon("src/resources/criatura.png");
        Image imagenCriatura = iconoCriatura.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        JLabel labelImagenCriatura = new JLabel(new ImageIcon(imagenCriatura));
        panelDerecho.add(labelImagenCriatura, BorderLayout.CENTER);

        JPanel panelInfoCriatura = new JPanel(new GridLayout(2, 1));
        labelVidaCriatura = new JLabel("Vida Criatura: " + controlador.getVidaCriatura());
        labelVidaCriatura.setHorizontalAlignment(SwingConstants.CENTER);
        labelDefensaCriatura = new JLabel("Defensa Criatura: " + controlador.getDefensaCriatura());
        labelDefensaCriatura.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfoCriatura.add(labelVidaCriatura);
        panelInfoCriatura.add(labelDefensaCriatura);
        panelDerecho.add(panelInfoCriatura, BorderLayout.SOUTH);

        // Panel central: diálogo de la pelea
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Diálogo de la pelea"));

        textAreaCombate = new JTextArea();
        textAreaCombate.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaCombate);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        add(panelPrincipal);
    }

    private void iniciarCicloCombate() {
        new Thread(() -> {
            while (controlador.getVidaPersonaje() > 0 && controlador.getVidaCriatura() > 0) {
                // Ejecutar un turno de combate
                controlador.ejecutarTurnoDeCombate();

                // Procesar eventos registrados en la pelea
                for (String evento : controlador.obtenerEventosPelea()) {
                    SwingUtilities.invokeLater(() -> {
                        textAreaCombate.append(evento + "\n");
                        textAreaCombate.setCaretPosition(textAreaCombate.getDocument().getLength()); // Scroll automático
                    });

                    // Pausa entre eventos
                    esperar(500);
 
                }
                // Añadir línea separadora después del turno
                SwingUtilities.invokeLater(() -> {
                    textAreaCombate.append("---------\n");
                    textAreaCombate.setCaretPosition(textAreaCombate.getDocument().getLength()); // Scroll automático
                });
                
                
                actualizarInterfaz();

                // Limpiar eventos después de procesarlos
                controlador.limpiarEventosPelea();
                
            }

            // Al finalizar el combate, mostrar el resultado
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
        labelDefensaPersonaje.setText("Defensa Personaje: " + controlador.getDefensaPersonaje());
        labelVidaCriatura.setText("Vida Criatura: " + controlador.getVidaCriatura());
        labelDefensaCriatura.setText("Defensa Criatura: " + controlador.getDefensaCriatura());
    }
   

    private void mostrarResultado(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        dispose();
        controlador.actualizarPantallaEstadoPersonaje();

        if ("Derrota".equals(titulo)) {
            PantallaFinJuego pantallaFin = new PantallaFinJuego();
            pantallaFin.mostrarFinDelJuego(false);
        } else if (controlador.esVictoriaFinal()) {
            PantallaFinJuego pantallaFin = new PantallaFinJuego();
            pantallaFin.mostrarFinDelJuego(true);
        } else {
            JOptionPane.showMessageDialog(this, "Has derrotado a la criatura, pero el juego continúa.", "Victoria parcial", JOptionPane.INFORMATION_MESSAGE);
        }
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
