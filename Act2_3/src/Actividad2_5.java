
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_5 extends Applet implements ActionListener {
    private HiloContador hilo1, hilo2;
    private Font fuente;
    private Button b1, b2;

    @Override
    public void init() {
        setBackground(Color.yellow);
        add(b1 = new Button("Finalizar Hilo 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Finalizar Hilo 2"));
        b2.addActionListener(this);

        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializamos los hilos con valores iniciales
        hilo1 = new HiloContador(10);
        hilo2 = new HiloContador(20);

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("Hilo1: " + hilo1.getContador(), 80, 100);
        g.drawString("Hilo2: " + hilo2.getContador(), 80, 150);
    }

    // Control de la pulsación del botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            hilo1.detener();
            b1.setLabel("Finalizado Hilo 1");
        } else if (e.getSource() == b2) {
            hilo2.detener();
            b2.setLabel("Finalizado Hilo 2");
        }
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
                    repaint();
                } catch (InterruptedException e) {
                    // Interrupción recibida, salir del bucle
                    enEjecucion = false;
                }
            }
        }
    }
}


