package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import controlador.ControladorJuego;
import modelo.objectViews.MisionView;

public class PantallaMisiones extends JFrame {
    private static final long serialVersionUID = 1L;
    private ControladorJuego controlador;

    public PantallaMisiones(ControladorJuego controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Misiones");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Listado de Misiones", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Panel central para mostrar las misiones
        JPanel panelMisiones = new JPanel();
        panelMisiones.setLayout(new BoxLayout(panelMisiones, BoxLayout.Y_AXIS));

        // Obtener las misiones del controlador
        List<MisionView> misiones = controlador.obtenerMisionesJugador();

        if (misiones.isEmpty()) {
            JLabel sinMisiones = new JLabel("No tienes misiones activas.");
            sinMisiones.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelMisiones.add(sinMisiones);
        } else {
            for (MisionView mision : misiones) {
                JPanel misionPanel = new JPanel(new BorderLayout());
                misionPanel.setBorder(BorderFactory.createTitledBorder(mision.getNombre()));
                
                JTextArea descripcion = new JTextArea(mision.getDescripcion());
                descripcion.setLineWrap(true);
                descripcion.setWrapStyleWord(true);
                descripcion.setEditable(false);
                misionPanel.add(descripcion, BorderLayout.CENTER);
                
                if (mision.estaCompletada()) {
                    JLabel estado = new JLabel("Estado: Completada");
                    estado.setForeground(Color.GREEN);
                    misionPanel.add(estado, BorderLayout.SOUTH);
                } else {
                    JLabel estado = new JLabel("Estado: En progreso");
                    estado.setForeground(Color.RED);
                    misionPanel.add(estado, BorderLayout.SOUTH);
                }

                panelMisiones.add(misionPanel);
                panelMisiones.add(Box.createVerticalStrut(10)); // Espaciado entre misiones
            }
        }

        JScrollPane scrollPane = new JScrollPane(panelMisiones);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con los botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Botón Reclamar Recompensa
        JButton botonReclamarRecompensa = new JButton("Reclamar Recompensa");
        botonReclamarRecompensa.addActionListener(e -> {
            if (controlador.reclamarRecompensa()) {
                JOptionPane.showMessageDialog(this, "¡Recompensa reclamada con éxito!", "Recompensa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay recompensas disponibles o ya han sido reclamadas.", "Sin Recompensa", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelInferior.add(botonReclamarRecompensa);

        // Botón para cerrar
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> dispose());
        panelInferior.add(botonCerrar);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    public void mostrar() {
        setVisible(true);
    }
}
