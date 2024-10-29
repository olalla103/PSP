import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_5 extends JFrame implements ActionListener {
    private HiloContador hilo1, hilo2;
    private Font fuente;
    private JButton b1, b2;
    private JPanel panel;

    public Actividad2_5() {
        // Configuración del JFrame
        setTitle("Actividad 2.5");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración del panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.yellow);
        botonesPanel.add(b1 = new JButton("Finalizar Hilo 1"));
        b1.addActionListener(this);
        botonesPanel.add(b2 = new JButton("Finalizar Hilo 2"));
        b2.addActionListener(this);

        // Configuración del panel de dibujo
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.clearRect(0, 0, getWidth(), getHeight());
                g.setFont(fuente);
                g.drawString("Hilo1: " + hilo1.getContador(), 80, 100);
                g.drawString("Hilo2: " + hilo2.getContador(), 80, 150);
            }
        };
        panel.setBackground(Color.yellow);

        add(panel, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);

        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializamos los hilos con valores iniciales
        hilo1 = new HiloContador(10);
        hilo2 = new HiloContador(20);

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
    }

    // Control de la pulsación del botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            hilo1.detener();
            b1.setText("Finalizado Hilo 1");
        } else if (e.getSource() == b2) {
            hilo2.detener();
            b2.setText("Finalizado Hilo 2");
        }
        panel.repaint();
    }

    class HiloContador extends Thread {
        private long contador;
        private boolean enEjecucion = true;

        public HiloContador(long contadorInicial) {
            this.contador = contadorInicial;
        }

        public long getContador() {
            return contador;
        }

        public void detener() {
            enEjecucion = false;
            this.interrupt(); // Interrumpimos el hilo para salir del sleep
        }

        @Override
        public void run() {
            while (enEjecucion) {
                try {
                    Thread.sleep(300);
                    contador++;
                    panel.repaint(); // Reemplazamos repaint() para actualizar el contador en el panel
                } catch (InterruptedException e) {
                    // Interrupción recibida, salir del bucle
                    enEjecucion = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Actividad2_5 frame = new Actividad2_5();
            frame.setVisible(true);
        });
    }
}
